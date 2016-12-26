package com.bingcore.web.controller;

import com.bingcore.web.config.SysConfigService;
import com.bingcore.web.constant.SysConst;
import com.bingcore.web.enums.MapType;
import com.bingcore.web.model.db.Goods;
import com.bingcore.web.model.other.GoodsInfoMessage;
import com.bingcore.web.model.other.GoodsMessage;
import com.bingcore.web.model.search.WebOrder;
import com.bingcore.web.service.GoodsService;
import com.bingcore.web.service.SearchService;
import com.bingcore.web.util.RedisKeys;
import com.bingcore.web.util.RedisUtil;
import com.bingcore.web.util.nsq.NSQProducerService;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.primitives.Ints;
import com.ishansong.activemq.MessageSender;
import com.ishansong.common.date.Dates;
import com.ishansong.common.json.Jsoner;
import com.ishansong.common.model.Paging;
import com.ishansong.common.model.Response;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by xubing on 16/7/17.
 * <p/>
 * 商品处理控制类
 */
@Controller
@RequestMapping(value = "/web/goods")
public class GoodsController {


    private final static Logger logger = LoggerFactory.getLogger("web");

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    NSQProducerService nsqProducerService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    SearchService searchService;

    @Resource
    private MessageSender goodsMessageSender;

    @RequestMapping(value = "/list")
    public ModelAndView goodsList() {
        ModelAndView view = new ModelAndView("list");

        Response<List<Goods>> goodsResp = goodsService.queryAllGoodsInfo();
        if (!goodsResp.isSuccess()) {
            logger.error("failed to query goods data , cause: {}", goodsResp.getErr());
            throw new IllegalArgumentException(goodsResp.getErr());
        }
        view.addObject("goodsList", goodsResp.getData());

        return view;
    }

    @RequestMapping(value = "/mytab")
    public ModelAndView myTable() {
        ModelAndView view = new ModelAndView("myTabs");

        return view;
    }

    /**
     * 购买商品
     *
     * @param goodId 商品Id
     * @return
     */
    @RequestMapping(value = "/purchase")
    @ResponseBody
    public ModelAndView purchaseGoods(ModelAndView model, @RequestParam(value = "id", defaultValue = "0") Integer goodId) {

        //query goods info
        Response<Goods> goodsResp = goodsService.queryOneGoodsById(goodId);
        if (!goodsResp.isSuccess()) {
            logger.error("failed to query goods info data , cause: {}", goodsResp.getErr());
            throw new IllegalArgumentException(goodsResp.getErr());
        }
        model.addObject("goods", goodsResp.getData());

        model.setViewName("purchase");

        return model;
    }

    /**
     * 提交订单
     *
     * @return
     */
    @RequestMapping(value = "/commit/{id}")
    @ResponseBody
    public ModelAndView commitOrder(ModelAndView model, @PathVariable Integer id,
                                    @RequestParam(value = "count", defaultValue = "0") Integer count,
                                    @RequestParam(value = "ddlProvince") String city) {


        model.setViewName("finish");

        //query goods info
        Response<Goods> goodsResp = goodsService.queryOneGoodsById(id);
        if (!goodsResp.isSuccess()) {
            logger.error("failed to query goods info data , cause: {}", goodsResp.getErr());
            throw new IllegalArgumentException(goodsResp.getErr());
        }
        Goods goodsInfo = goodsResp.getData();
        Random random = new Random();
        Integer orderSeq = random.nextInt(20000 + 1) + 10000;
        BigDecimal money = new BigDecimal(count).multiply(goodsInfo.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
        model.addObject("orderNumber", "BC" + orderSeq);
        model.addObject("money", money);
        model.addObject("buyCount", count);

        //precondition check
        if (goodsInfo.getCount() < count) {
            logger.error("goods leftCount are less than expected ! realCount:{}", goodsInfo.getCount());

            String errMsg = "商品库存不足，请稍后购买! 实际库存:" + goodsInfo.getCount() + ",购买量:" + count;
            model.addObject("success", false);
            model.addObject("errMsg", errMsg);
            return model;
        }

        if (count > 0) {
            //send place order msg
            logger.info("start to send place msg: {}", goodsInfo);
            GoodsMessage goodsMessage = new GoodsMessage(city, goodsInfo, count);
            nsqProducerService.send(SysConst.WEB_TOPIC, Jsoner.DEFAULT.toJson(goodsMessage));

            //clean redis cache
            String key = RedisKeys.buildMapDataKey();
            if (RedisUtil.DEFAULT.exists(key)) {
                RedisUtil.DEFAULT.delete(key);
            }
        }

        model.addObject("success", true);
        model.addObject("firstLogin", true);

        return model;
    }


    /**
     * 购买记录查询
     *
     * @param pageNo      页号
     * @param pageSize    分页大小
     * @param st          起始时间
     * @param et          结束时间
     * @param searchName  搜索字段名
     * @param searchValue 搜索字段值
     */
    @RequestMapping(value = "/purchase/query")
    public String purchaseQuery(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "startDate", defaultValue = "") String st,
            @RequestParam(value = "endDate", defaultValue = "") String et,
            @RequestParam(value = "searchName", defaultValue = "") String searchName,
            @RequestParam(value = "searchField", defaultValue = "") String searchValue,
            Model model) {


        Date startDate = null;
        Date endDate = null;
        String viewStr = "purchaseList";
        String today = DateTimeFormat.forPattern("yyyy-MM-dd").print(new DateTime());
        if (Dates.isValidDate(st)) {
            startDate = DateTime.parse(st).toDate();
        } else {
            // 默认今天
            startDate = DateTime.parse(today).toDate();
        }
        model.addAttribute("startDate", DateTimeFormat.forPattern("yyyy-MM-dd").print(new DateTime(startDate)));

        if (Dates.isValidDate(et)) {
            endDate = DateTime.parse(et).plusDays(1).toDate();
            model.addAttribute("endDate", et);
        } else {
            endDate = DateTime.parse(today).plusDays(1).toDate();
            model.addAttribute("endDate", today);
        }

        // 搜所关键字处理
        String wrapperValue = searchValue;
        if (!Strings.isNullOrEmpty(searchName) && !Strings.isNullOrEmpty(searchValue)) {
            wrapperValue = StringUtils.trim(searchValue);
            if (Objects.equal(searchName, "goodsId")) {
                wrapperValue = String.valueOf(MapType.getByName(searchValue).getType());
            }
        }

     /*   //mongo 查询
        Response<Paging<GoodsPurchaseInfo>> mongoResp =
                goodsService.paging(pageNo, pageSize, startDate, endDate, searchName, wrapperValue);
        if (mongoResp.isSuccess()) {
            model.addAttribute("pageData", mongoResp.getData());
        }*/

        //es 查询
        Response<Paging<WebOrder>> searchResp =
                searchService.search(pageNo, pageSize, st, et, searchName, searchValue);
        if (searchResp.isSuccess()) {
            viewStr = "purchaseList_search";
            model.addAttribute("pageData", searchResp.getData());
        }


        model.addAttribute("searchName", searchName);
        model.addAttribute("searchField", searchValue);

        return viewStr;

    }

    @RequestMapping(value = "/manage/list")
    public ModelAndView manageList() {
        ModelAndView view = new ModelAndView("goodsList");

        Response<List<Goods>> goodsResp = goodsService.queryAllGoodsInfo();
        if (!goodsResp.isSuccess()) {
            logger.error("failed to query goods data , cause: {}", goodsResp.getErr());
            throw new IllegalArgumentException(goodsResp.getErr());
        }


        view.addObject("goodsList", goodsResp.getData());

        return view;
    }

    /**
     * 商品信息修改
     *
     * @param id       商品序号
     * @param soldFlag 下架标识
     * @param stock    库存
     * @param amt      商品标价
     */
    @RequestMapping(value = "/manage/modify/{id}")
    public String modifySubsidy(RedirectAttributes redirectAttributes, @PathVariable Integer id,
                                @RequestParam(value = "oskFlag", defaultValue = "") String soldFlag,
                                @RequestParam(value = "goodsCount", defaultValue = "") String stock,
                                @RequestParam(value = "goodsMoney", defaultValue = "") String amt) {

        String backUrl = "redirect:/web/goods/manage/list";
        try {
            Preconditions.checkNotNull(id, "goods Id is null!");

            Goods goods = new Goods();
            goods.setId(id);
            goods.setPrice(new BigDecimal(amt));
            goods.setCount(Ints.tryParse(stock));
            goods.setOsk(Ints.tryParse(soldFlag));

            GoodsInfoMessage goodsInfoMessage = new GoodsInfoMessage();
            goodsInfoMessage.setGoods(goods);

            goodsMessageSender.send(goodsInfoMessage);
            logger.info("modify info,flag:{},count:{},money:{}", soldFlag, stock, amt);

            //缓存100ms后显示结果
            Thread.sleep(100);
            redirectAttributes.addFlashAttribute("message", "商品信息修改成功。");
        } catch (Exception e) {
            logger.error("failed to modify goodsInfo(id={}), cause: {}", id, Throwables.getStackTraceAsString(e));
            redirectAttributes.addFlashAttribute("error", "更新商品信息失败！");
            return backUrl;
        }

        return backUrl;
    }

}
