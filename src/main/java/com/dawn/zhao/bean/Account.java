package com.dawn.zhao.bean;

import java.util.Date;

public class Account {

    private Integer id;
    private String account;
    private Date brithDay;

    public Account() {
    }

    public Account(Integer id, String account) {
        this.id = id;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
}

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getBrithDay() {
        return brithDay;
    }

    public void setBrithDay(Date brithDay) {
        this.brithDay = brithDay;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", brithDay=" + brithDay +
                '}';
    }

}
