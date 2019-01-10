package com.mpartners.model;


import javax.persistence.*;

@Entity
@Table(name = "PARTNER")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String name2;
    private String vatNumber;
    private String accountGroup;
    private String customerGroup;
    private String comment;
    private String originData;


    public Partner() {
    }

    public Partner(String name, String name2, String vatNumber, String accountGroup, String customerGroup, String comment, String originData) {
        this.name = name;
        this.name2 = name2;
        this.vatNumber = vatNumber;
        this.accountGroup = accountGroup;
        this.customerGroup = customerGroup;
        this.comment = comment;
        this.originData = originData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(String accountGroup) {
        this.accountGroup = accountGroup;
    }

    public String getCustomerGroup() {
        return customerGroup;
    }

    public void setCustomerGroup(String customerGroup) {
        this.customerGroup = customerGroup;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOriginData() {
        return originData;
    }

    public void setOriginData(String originData) {
        this.originData = originData;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", name2='" + name2 + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", accountGroup='" + accountGroup + '\'' +
                ", customerGroup='" + customerGroup + '\'' +
                ", comment='" + comment + '\'' +
                ", originData='" + originData + '\'' +
                '}';
    }
}