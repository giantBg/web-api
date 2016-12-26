package com.bingcore.web.config;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.ishansong.config.client.common.annotations.DisconfItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by xubing on 16/03/24.
 *
 * @Author xubing
 * @DateTime 2016-03-25
 * @Function 系统参数获取服务类
 */
@Service
public class SysConfigService {

    /*测试*/
    @Value(value = "nobody")
    private String loginName;

    @DisconfItem(key = "login.name")
    public String getLoginName() {
        return loginName;
    }


    /**
     * String to HashSet
     *
     * @param value
     */
    public Set<String> getValueSet(String value) {

        if (Strings.isNullOrEmpty(value)) {

            return Sets.newHashSet();
        }

        Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split(value);

        return Sets.newHashSet(split);
    }

}
