package com.maddresses.model;


import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street1;
    private String street2;
    private String number;
    private String poBox;
    private String language;
    private String zipCode;
    private String city;
    private String country;
    private String phone;
    private String email;
    private String originData;


    public Address() {
    }

    public Address(String street1, String street2, String number, String poBox, String language, String zipCode, String city, String country, String phone, String email, String originData) {
        this.street1 = street1;
        this.street2 = street2;
        this.number = number;
        this.poBox = poBox;
        this.language = language;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.originData = originData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOriginData() {
        return originData;
    }

    public void setOriginData(String originData) {
        this.originData = originData;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street1='" + street1 + '\'' +
                ", street2='" + street2 + '\'' +
                ", number='" + number + '\'' +
                ", poBox='" + poBox + '\'' +
                ", language='" + language + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", originData='" + originData + '\'' +
                '}';
    }
}