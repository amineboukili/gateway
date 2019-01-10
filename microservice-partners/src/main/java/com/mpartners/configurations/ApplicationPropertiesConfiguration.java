package com.mpartners.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {

    private int limitOfPartners;

    public int getLimitOfPartners() {
        return limitOfPartners;
    }

    public void setLimitOfPartners(int limitOfPartners) {
        this.limitOfPartners = limitOfPartners;
    }
}
