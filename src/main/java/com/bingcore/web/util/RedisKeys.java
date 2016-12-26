package com.bingcore.web.util;

/**
 * <pre>
 * 功能描述：系统用到的redis键值
 *
 * </pre>
 * Author: xubing
 * DateTime: 2016-05-14
 */
public class RedisKeys {

    //order
    public static String buildMapDataKey() {
        return String.format("map_data_key_v1");
    }

}
