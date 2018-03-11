package com.dawn.zhao.bean;

import java.io.Serializable;

public class Group implements Serializable {

    private Integer id;
    private String groupName;

    public Group() {
    }

    public Group(Integer id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        String str = "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
        System.out.println(str);
        return str;
    }

    public void init(){
        System.out.println("I'm Group init method");
    }
}
