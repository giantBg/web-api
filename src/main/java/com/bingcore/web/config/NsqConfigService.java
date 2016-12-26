package com.bingcore.web.config;

import com.ishansong.config.client.common.annotations.DisconfFile;
import com.ishansong.config.client.common.annotations.DisconfFileItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by xubing on 16/03/24.
 *
 * @Author xubing
 * @DateTime 2016-03-25
 * @Function nsq配置获取类
 */
@Service
@Scope("singleton")
@DisconfFile(filename = "nsq.properties")
public class NsqConfigService {

    /**
     * 主机
     */
    private String host;

    @DisconfFileItem(name = "host", associateField = "host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 端口
     */
    private Integer port;

    @DisconfFileItem(name = "port", associateField = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
