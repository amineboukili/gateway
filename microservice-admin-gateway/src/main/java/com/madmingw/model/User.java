package com.madmingw.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String userLogin;
    private String userEmail;
    private String userFirstname;
    private String userLastname;
    private String userEnable;
    @Column(nullable = true)
    private Long dblogAddUserId;
    private Date dblogAddDate;
    @Column(nullable = true)
    private Long dblogUpdUserId;
    private Date dblogUpdDate;
    private String userLanguage;
    @Column(nullable = true)
    private Long identityUserId;


    public User() {
    }

    public User(String userLogin, String userEmail, String userFirstname, String userLastname, String userEnable, long dblogAddUserId, Date dblogAddDate, long dblogUpdUserId, Date dblogUpdDate, String userLanguage, long identityUserId) {
        this.userLogin = userLogin;
        this.userEmail = userEmail;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.userEnable = userEnable;
        this.dblogAddUserId = dblogAddUserId;
        this.dblogAddDate = dblogAddDate;
        this.dblogUpdUserId = dblogUpdUserId;
        this.dblogUpdDate = dblogUpdDate;
        this.userLanguage = userLanguage;
        this.identityUserId = identityUserId;
    }

    public long getId() {
        return id;
    }

    public void setid(long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserEnable() {
        return userEnable;
    }

    public void setUserEnable(String userEnable) {
        this.userEnable = userEnable;
    }

    public Long getDblogAddUserId() {
        return dblogAddUserId;
    }

    public void setDblogAddUserId(Long dblogAddUserId) {
        this.dblogAddUserId = dblogAddUserId;
    }

    public Date getDblogAddDate() {
        return dblogAddDate;
    }

    public void setDblogAddDate(Date dblogAddDate) {
        this.dblogAddDate = dblogAddDate;
    }

    public Long getDblogUpdUserId() {
        return dblogUpdUserId;
    }

    public void setDblogUpdUserId(Long dblogUpdUserId) {
        this.dblogUpdUserId = dblogUpdUserId;
    }

    public Date getDblogUpdDate() {
        return dblogUpdDate;
    }

    public void setDblogUpdDate(Date dblogUpdDate) {
        this.dblogUpdDate = dblogUpdDate;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public Long getIdentityUserId() {
        return identityUserId;
    }

    public void setIdentityUserId(Long identityUserId) {
        this.identityUserId = identityUserId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userLogin='" + userLogin + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userFirstname='" + userFirstname + '\'' +
                ", userLastname='" + userLastname + '\'' +
                ", userEnable='" + userEnable + '\'' +
                ", dblogAddUserId=" + dblogAddUserId +
                ", dblogAddDate=" + dblogAddDate +
                ", dblogUpdUserId=" + dblogUpdUserId +
                ", dblogUpdDate=" + dblogUpdDate +
                ", userLanguage='" + userLanguage + '\'' +
                ", identityUserId=" + identityUserId +
                '}';
    }
}
