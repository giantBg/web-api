package com.bingcore.web.util.nsq;

import com.google.common.base.Throwables;
import com.ishansong.log.Loggers;
import com.ishansong.nsq.NSQMessage;
import com.ishansong.nsq.NSQMessageCallback;
import org.slf4j.Logger;

/**
 * Created by winston on 15/3/13.
 *
 * @author winston
 */

public class NsqConsumerCallBack implements NSQMessageCallback {

    private Logger logger = Loggers.get("web");

    @Override
    public void message(NSQMessage message) {
        try {
            message.finished();
            String msg = new String(message.getMessage(), "UTF-8");
            logger.info("receive message: {}", msg);
        } catch (Throwable e) {
            logger.error("failed messagedeal ,case {}", Throwables.getStackTraceAsString(e));
        }
    }

    @Override
    public void error(Exception e) {
        logger.error("failed messagedeal ,case {}", Throwables.getStackTraceAsString(e));
    }
}
