package com.dbc.minifb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Knox
 * @Date: 2018/12/1 11:11 PM
 * @Description: You Know
 * @Version 1.0
 */
@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String username;
    @JsonIgnore
    @Property
    private String password;
    @Property
    private Integer sex;
    @JsonIgnore
    @Property
    private String phoneNumber;
    @Property
    private Integer age;
    @Property
    private Date registerTime;
    @Property
    private String headIconUrl;

    @JsonIgnore
    @Relationship(type = "SUBSCRIBE")
    private List<User> subscribes = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, Integer sex, String phoneNumber, Integer age, Date registerTime) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.registerTime = registerTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getHeadIconUrl() {
        return headIconUrl;
    }

    public void setHeadIconUrl(String headIconUrl) {
        this.headIconUrl = headIconUrl;
    }

    public List<User> getSubscribes() {
        return subscribes;
    }

    public void setSubscribes(List<User> subscribes) {
        this.subscribes = subscribes;
    }
}
