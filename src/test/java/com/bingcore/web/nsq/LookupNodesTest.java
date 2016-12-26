package com.bingcore.web.nsq;

import com.ishansong.nsq.ConnectionAddress;
import com.ishansong.nsq.NSQProducer;
import com.ishansong.nsq.factory.NSQProducerFactory;
import com.ishansong.nsq.factory.lookup.LookupNodes;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 */
public class LookupNodesTest {
    public static void main(String[] args) throws Exception {
        final AtomicLong count = new AtomicLong();
        final AtomicLong last = new AtomicLong();
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long ss = count.get();
                System.out.println(" count " + (ss));
                last.set(ss);
            }
        }, 0, 1, TimeUnit.SECONDS);

        ArrayList<ConnectionAddress> list = LookupNodes.lookup("127.0.0.1", 4161);
        for (ConnectionAddress conn : list) {
            System.out.println(conn.getHost() + ":" + conn.getPort());
        }

        final NSQProducer factory = NSQProducerFactory.newProducerForLookup("127.0.0.1", 4161, 20);
        factory.start();
        final ExecutorService executor = new ThreadPoolExecutor(50, 80, 1, TimeUnit.DAYS,
                new ArrayBlockingQueue<Runnable>(20000), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 1000 * 1; i++) {
            final int vvv = i;
            executor.submit(new Runnable() {
                public void run() {
                    count.incrementAndGet();
                    try {
                        String msg = "test_winston_" + vvv;
                        factory.produce("test_winston", (msg).getBytes("utf8"));
                    } catch (Exception e) {
                    }
                }
            });
        }
    }
}
