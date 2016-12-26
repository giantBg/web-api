package com.bingcore.web.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;
import com.ishansong.common.json.Jsoner;
import com.ishansong.log.Loggers;
import org.apache.commons.collections.MapUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by xubing on 16/7/24.
 *
 * @author xubing
 * @function:openApi日志记录切点类
 * @recordStyle:Spring Aop切面编程
 * @time 2016-07-24
 */
@Service
@Aspect
public class LogAspect {

    private final Logger logger = Loggers.get("web");
    private final Logger statLog = Loggers.get("stat");

    private String requestPath = null; // 请求地址
    private Map<?, ?> inputParamMap = null; // 传入参数
    private Map<String, Object> outputParamMap = null; // 存放输出结果
    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    @Pointcut("execution(* com.bingcore.web.controller.GoodsController.commit*(..)))")
    public void webAspect() {
    }

    /**
     * @param joinPoint
     * @Title：doBeforeInServiceLayer
     * @Description: 方法调用前触发
     * 记录开始时间
     */
    @Before("webAspect()")
    public void doBeforeInServiceLayer(JoinPoint joinPoint) {
        startTimeMillis = System.currentTimeMillis(); // 记录方法开始执行的时间
    }

    /**
     * @param joinPoint
     * @Title：doAfterInServiceLayer
     * @Description: 方法调用后触发
     * 记录结束时间
     */
    @After("webAspect()")
    public void doAfterInServiceLayer(JoinPoint joinPoint) {
        endTimeMillis = System.currentTimeMillis(); // 记录方法执行完成的时间
        this.printOptLog();
    }

    /**
     * @param pjp
     * @return
     * @throws Throwable
     * @Title：doAround
     * @Description: 环绕触发
     */
    @Around("webAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        // 获取输入参数
        inputParamMap = request.getParameterMap();
        // 获取请求地址
        requestPath = request.getRequestURI();

        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        outputParamMap = Maps.newHashMap();
        Object result = pjp.proceed();// result的值就是被拦截方法的返回值
        outputParamMap.put("result", result);

        return result;
    }

    /**
     * @Title：printOptLog
     * @Description: 输出日志
     */
    private void printOptLog() {
        Long spendTime = endTimeMillis - startTimeMillis;

        //obtain params
        ModelAndView outputParams = (ModelAndView) MapUtils.getObject(outputParamMap, "result");
        Map modelParam = outputParams.getModelMap();
        String orderNumber = MapUtils.getString(modelParam, "orderNumber");
        Double money = MapUtils.getDouble(modelParam, "money");
        Integer count = Ints.tryParse(modelParam.get("buyCount").toString());
        Boolean result = MapUtils.getBoolean(modelParam, "success");
        String[] cities = (String[]) inputParamMap.get("ddlProvince");
        String city = cities[0];
        List<String> requestItem = Splitter.on("/").omitEmptyStrings().trimResults().splitToList(requestPath);
        String goodsId = requestItem.get(requestItem.size() - 1);

        statLog.info("statLog,logType:placeOrder,orderNumber:{},placeTime:{},city:{},goodsId:{},count:{},amt:{},result:{}", orderNumber, System.currentTimeMillis(), city, goodsId, count, money, result);

        logger.info("bing.xu->tradeRecord: requestUrl:{},requestParams:{},response:{},totalSpend:{} ms ",
                requestPath, Jsoner.DEFAULT.toJson(inputParamMap), Jsoner.DEFAULT.toJson(outputParamMap), spendTime);
    }
}
