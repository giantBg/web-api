package com.bingcore.web.util.nsq;


import com.bingcore.web.config.NsqConfigService;
import com.ishansong.nsq.NSQProducer;
import com.ishansong.nsq.factory.NSQProducerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * Created by bing.xu on 16/7/14.
 */
@Service
public class NSQProducerService {
    private static final int coreSize = Runtime.getRuntime().availableProcessors() / 2 + 1;
    private static final int maxSize = coreSize * 2;
    private static final int keepAliveTime = 60;
    private static final int queueSize = 10240;
    private static ExecutorService executor = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, TimeUnit.DAYS,
            new ArrayBlockingQueue<Runnable>(queueSize), Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());
    private static NSQProducer factory;

    @Autowired
    private NsqConfigService nsqConfigService;

    public void send(final String topic, final String message) {
        executor.submit(new Runnable() {
            public void run() {
                try {
                    getProducerFactory().produce(topic, message.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private NSQProducer getProducerFactory() {
        if (factory == null) {
            synchronized (NSQProducerService.class) {
                if (factory == null) {
                    factory = NSQProducerFactory.newProducerForLookup(nsqConfigService.getHost(), nsqConfigService.getPort(), 10);
                    factory.start();
                }
            }
        }
        return factory;
    }
}
