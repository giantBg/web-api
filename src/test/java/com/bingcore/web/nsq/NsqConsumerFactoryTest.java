package com.bingcore.web.nsq;


import com.ishansong.nsq.NSQLookup;
import com.ishansong.nsq.NSQMessage;
import com.ishansong.nsq.NSQMessageCallback;
import com.ishansong.nsq.factory.NSQConsumerFactory;
import com.ishansong.nsq.lookup.NSQLookupDynMapImpl;

/**
 * Created by shaojieyue
 * on 2014-12-26 16:42
 */
public class NsqConsumerFactoryTest {
    public static void main(String[] args) {
        NSQLookup lookup = new NSQLookupDynMapImpl();
        lookup.addAddr("127.0.0.1", 4161);
        NSQConsumerFactory.newConsumer(lookup, "test_winston", "test_winston_channel", 300, new NSQMessageCallback() {
            @Override
            public void message(final NSQMessage message) {
                try {
                    message.finished();
                    //now mark the message as finished.
                    //Thread.sleep(600*1000);
                    //message.finished();
                    System.out.println(new String(message.getMessage()));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(Exception x) {
                x.printStackTrace();
            }
        });

    }
}
