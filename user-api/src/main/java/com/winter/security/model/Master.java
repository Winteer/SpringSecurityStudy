package com.winter.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName : Master  //类名
 * @Description : 雇主  //描述
 * @Author : Winter  //作者
 * @Date: 2020-08-24 18:26  //时间
 */
@Entity
@Table(name = "master")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_id")
    private Integer maId;

    @Column(name = "ma_name")
    private String maName;

    @Column(name = "ma_age")
    private Integer maAge;


    //    @JsonIgnore
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(ignoreUnknown = true, value = "master")
    public List<Employee> employees;


    public Integer getMaId() {
        return maId;
    }

    public void setMaId(Integer maId) {
        this.maId = maId;
    }

    public String getMaName() {
        return maName;
    }

    public void setMaName(String maName) {
        this.maName = maName;
    }

    public Integer getMaAge() {
        return maAge;
    }

    public void setMaAge(Integer maAge) {
        this.maAge = maAge;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
