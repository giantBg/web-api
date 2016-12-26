package com.bingcore.web.util;

import com.ishansong.common.config.Config;
import com.ishansong.common.config.ConfigCenter;

/**
 * Created by xubing on 16/03/26.
 *
 * @Author xubing
 * @DateTime 2016-03-26
 * @Function 系统配置获取工具类
 */
public class SysConfigUtil {


    private static Config config = null;

    static {
        try {
            //读取配置文件配置
            config = ConfigCenter.getConfig("app.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取mongo 配置
     *
     * @return
     */
    public static String getMongoAddress() {
        return config.getString("mongo.address");
    }

    /**
     * 获取域名
     *
     * @return
     */
    public static String getDomain() {
        return config.getString("cookie.domain");
    }

    /**
     * 获取session过期时间
     *
     * @return
     */
    public static int getSessionExpire() {
        return config.getInt("session.timeout");
    }

    /**
     * redis主机
     *
     * @return
     */
    public static String getRedisHost() {
        return config.getString("redis.host");
    }

    /**
     * redis端口
     *
     * @return
     */
    public static int getRedisPort() {
        return config.getInt("redis.port");
    }

}
