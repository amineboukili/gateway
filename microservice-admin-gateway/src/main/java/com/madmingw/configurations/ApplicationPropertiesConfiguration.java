package com.madmingw.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {

    private int limitUsers;

    private int limitDepartments;

    private int limitRoles;

    public int getLimitUsers() {
        return limitUsers;
    }

    public void setLimitUsers(int limitUsers) {
        this.limitUsers = limitUsers;
    }

    public int getLimitDepartments() {
        return limitDepartments;
    }

    public void setLimitDepartments(int limitDepartments) {
        this.limitDepartments = limitDepartments;
    }

    public int getLimitRoles() {
        return limitRoles;
    }

    public void setLimitRoles(int limitRoles) {
        this.limitRoles = limitRoles;
    }
}
