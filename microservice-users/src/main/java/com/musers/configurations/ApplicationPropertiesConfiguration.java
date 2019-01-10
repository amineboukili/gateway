package com.musers.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {

    private int limitUsers;

    public int getLimitUsers() {
        return limitUsers;
    }

    public void setLimitUsers(int limitUsers) {
        this.limitUsers = limitUsers;
    }
}
