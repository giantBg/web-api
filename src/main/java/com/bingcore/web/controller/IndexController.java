package com.bingcore.web.controller;

import com.bingcore.web.config.SysConfigService;
import com.bingcore.web.constant.SysConst;
import com.bingcore.web.model.chart.ChartOfMap;
import com.bingcore.web.service.DemoServer;
import com.bingcore.web.service.MapChartService;
import com.bingcore.web.util.RedisKeys;
import com.bingcore.web.util.RedisUtil;
import com.bingcore.web.util.WebUtils;
import com.bingcore.web.util.nsq.NSQProducerService;
import com.bingcore.web.util.nsq.msg.NsqMessage;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.ishansong.common.date.Dates;
import com.ishansong.common.json.Jsoner;
import com.ishansong.common.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xubing on 16/5/10.
 * <p/>
 * 主页控制类
 */
@Controller
public class IndexController {


    private final static Logger logger = LoggerFactory.getLogger("web");

    @Autowired
    MapChartService mapChartService;

    @Autowired
    DemoServer demoServer;

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    NSQProducerService nsqProducerService;

    @RequestMapping(value = "/web/index", method = RequestMethod.GET)
    public ModelAndView webIndex() {
        ModelAndView view = new ModelAndView("index");
        logger.info("get config name: {}", sysConfigService.getLoginName());
        view.addObject("tipMsg", demoServer.sayHello(sysConfigService.getLoginName()));
        return view;
    }


    @RequestMapping(value = "/web/sale", method = RequestMethod.GET)
    public ModelAndView webSale() {
        ModelAndView view = new ModelAndView("sale");
        //nsq message test
        String nsqMsg = "Message: " + Dates.now("yyyy-MM-dd HH:mm:ss");
        NsqMessage nsqMessage = new NsqMessage();
        nsqMessage.setMessage(nsqMsg);
        nsqProducerService.send(SysConst.WEB_TOPIC, Jsoner.DEFAULT.toJson(nsqMessage));
        logger.info("start to send msg:{},topic:{}", Jsoner.DEFAULT.toJson(nsqMsg), SysConst.WEB_TOPIC);

        return view;
    }

    @ResponseBody
    @RequestMapping(value = "/web/chart/map")
    public ChartOfMap convertRateArea(HttpServletRequest request) {


        //缓存设置
        ChartOfMap mapData = RedisUtil.DEFAULT.getJson(RedisKeys.buildMapDataKey(), ChartOfMap.class);
        if (mapData == null) {
            //search result
            Response<ChartOfMap> mapResp = mapChartService.getMapData();
            if (!mapResp.isSuccess()) {
                logger.error("failed to query map data , cause: {}", mapResp.getErr());
                throw new IllegalArgumentException(mapResp.getErr());
            }

            mapData = mapResp.getData();
            RedisUtil.DEFAULT.setJson(RedisKeys.buildMapDataKey(), SysConst.MAP_DATA_CACHE_TIME, mapData);
        }

        String userIp = WebUtils.getIpAddress(request);
        if (!Strings.isNullOrEmpty(userIp)) {
            String realIp = Splitter.on(',').omitEmptyStrings().splitToList(userIp).get(0);
            logger.info("user request ip:{}", realIp);
        }

        String platform = WebUtils.getAccessChannel(request);
        if (!Strings.isNullOrEmpty(platform)) {
            logger.info("access channel: {}", platform);
        }

        return mapData;
    }
}
