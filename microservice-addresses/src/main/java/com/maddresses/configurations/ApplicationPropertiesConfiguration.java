package com.maddresses.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {

    private int limitOfAddresses;

    public int getLimitOfAddresses() {
        return limitOfAddresses;
    }

    public void setLimitOfAddresses(int limitOfAddresses) {
        this.limitOfAddresses = limitOfAddresses;
    }
}
