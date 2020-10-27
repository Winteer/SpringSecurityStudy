package com.winter.security.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @ClassName : Employee  //类名
 * @Description : 雇员  //描述
 * @Author : Winter  //作者
 * @Date: 2020-08-19 14:59  //时间
 */


@Entity
@Table(name = "employee")
@ApiModel(value = "Employee", description = "雇员Model")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "年龄")
    @Column(name = "age")
    private Integer age;

    @ApiModelProperty(value = "雇主对象")
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "ma_id", foreignKey = @ForeignKey(name = "null", value = ConstraintMode.NO_CONSTRAINT))
    @JsonIgnoreProperties(ignoreUnknown = true, value = "employees")
    private Master master;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }
}
