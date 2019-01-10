package com.mmaterials.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {

    private int limitOfMaterials;

    public int getLimitOfMaterials() {
        return limitOfMaterials;
    }

    public void setLimitOfMaterials(int limitOfMaterials) {
        this.limitOfMaterials = limitOfMaterials;
    }
}
