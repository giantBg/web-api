package com.bingcore.web.util;


import com.ishansong.redis.core.RedisClient;

/**
 * Created by xubing on 16/5/14.
 *
 * @Author xubing
 * @DateTime 2016-05-14 16:14
 * @Function 统计服务jsonApi
 */

public class RedisUtil {

    public static RedisClient DEFAULT;

    static {

        String host = SysConfigUtil.getRedisHost();
        int port = SysConfigUtil.getRedisPort();
        DEFAULT = new RedisClient(host, port);

    }
}
