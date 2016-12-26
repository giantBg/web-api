package com.bingcore.web.util.nsq;


import com.bingcore.web.config.NsqConfigService;
import com.bingcore.web.constant.SysConst;
import com.ishansong.nsq.NSQLookup;
import com.ishansong.nsq.factory.NSQConsumerFactory;
import com.ishansong.nsq.lookup.NSQLookupDynMapImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * nsq消费服务
 * Created by winston on 15/11/30.
 *
 * @author winston
 */
@Service
public class NsqConsumer implements InitializingBean {
    @Autowired
    private NsqConfigService configService;

    @Override
    public void afterPropertiesSet() throws Exception {

//        NSQLookup lookup = new NSQLookupDynMapImpl();
//        lookup.addAddr(configService.getHost(), configService.getPort());
//        NSQConsumerFactory.newConsumer(lookup, SysConst.WEB_TOPIC, "test_channel", 300, new NsqConsumerCallBack());

    }
}
