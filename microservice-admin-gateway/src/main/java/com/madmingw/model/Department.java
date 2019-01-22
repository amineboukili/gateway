package com.madmingw.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Department {

    @Id
    @GeneratedValue
    private long id;
    private String departmentName;
    private String departmentLdapName;
    @Column(nullable = true)
    private Long parentDepartmentId;
    @Column(nullable = true)
    private Long departmentEnable;
    @Column(nullable = true)
    private Long managerUserId;
    @Column(nullable = true)
    private Long dblogAddUserId;
    private Date dblogAddDate;
    @Column(nullable = true)
    private Long dblogUpdUserId;
    private Date dblogUpdDate;
    private String departmentEmail;


    public Department() {
    }

    public Department(String departmentName, String departmentLdapName, Long parentDepartmentId, Long departmentEnable, Long managerUserId, Long dblogAddUserId, Date dblogAddDate, Long dblogUpdUserId, Date dblogUpdDate, String departmentEmail) {
        this.departmentName = departmentName;
        this.departmentLdapName = departmentLdapName;
        this.parentDepartmentId = parentDepartmentId;
        this.departmentEnable = departmentEnable;
        this.managerUserId = managerUserId;
        this.dblogAddUserId = dblogAddUserId;
        this.dblogAddDate = dblogAddDate;
        this.dblogUpdUserId = dblogUpdUserId;
        this.dblogUpdDate = dblogUpdDate;
        this.departmentEmail = departmentEmail;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentLdapName() {
        return departmentLdapName;
    }

    public void setDepartmentLdapName(String departmentLdapName) {
        this.departmentLdapName = departmentLdapName;
    }

    public Long getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(Long parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public Long getDepartmentEnable() {
        return departmentEnable;
    }

    public void setDepartmentEnable(Long departmentEnable) {
        this.departmentEnable = departmentEnable;
    }

    public Long getManagerUserId() {
        return managerUserId;
    }

    public void setManagerUserId(Long managerUserId) {
        this.managerUserId = managerUserId;
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

    public String getDepartmentEmail() {
        return departmentEmail;
    }

    public void setDepartmentEmail(String departmentEmail) {
        this.departmentEmail = departmentEmail;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", departmentLdapName='" + departmentLdapName + '\'' +
                ", parentDepartmentId=" + parentDepartmentId +
                ", departmentEnable=" + departmentEnable +
                ", managerUserId=" + managerUserId +
                ", dblogAddUserId=" + dblogAddUserId +
                ", dblogAddDate=" + dblogAddDate +
                ", dblogUpdUserId=" + dblogUpdUserId +
                ", dblogUpdDate=" + dblogUpdDate +
                ", departmentEmail='" + departmentEmail + '\'' +
                '}';
    }
}
