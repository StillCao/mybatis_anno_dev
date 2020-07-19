package com.sean.domain;

import java.io.Serializable;

/**
 * Fun:
 * Created by CW on 2020/7/18 4:31 下午
 */
public class Account implements Serializable {
    private Integer id;
    private Integer uid;
    private Double  money;
    //多对以映射，（mybatis中称之为一对一映射：一个账户只能属于一个用户）
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                ", user=" + user +
                '}';
    }
}
