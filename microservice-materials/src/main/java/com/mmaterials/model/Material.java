package com.mmaterials.model;


import javax.persistence.*;

@Entity
@Table(name = "MATERIAL")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String typeMat;
    private String groupMat;

    public Material() {
    }

    public Material(String typeMat, String groupMat) {
        this.typeMat = typeMat;
        this.groupMat = groupMat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeMat() {
        return typeMat;
    }

    public void setTypeMat(String typeMat) {
        this.typeMat = typeMat;
    }

    public String getGroupMat() {
        return groupMat;
    }

    public void setGroupMat(String groupMat) {
        this.groupMat = groupMat;
    }


    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", typeMat='" + typeMat + '\'' +
                ", groupMat='" + groupMat + '\'' +
                '}';
    }
}
