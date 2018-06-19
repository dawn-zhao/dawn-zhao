package com.dawn.zhao.bean;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.dawn.zhao._interface.Action;

import java.io.*;

public class User extends Account implements Serializable, Cloneable, Action {

    private static String country;

    private Integer userId;

    @Protobuf(fieldType = FieldType.STRING)
    private String userName;

    private String phone;

    private transient String address;

    private Group group;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString(byte[] a) {
        return "userId:"+userId+",userName:"+userName+",phone:"+phone;
    }

    public User(Integer userId, String userName, String phone) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
    }

    public User(Integer userId, String userName, String phone, String country, String address, String account) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.setAccount(account);
        this.country = country;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", account='" + getAccount() + '\'' +
                '}';
    }

    public User() {
    }

    public void getThread(String uuid){
        System.out.println(Thread.currentThread().getName()+"---"+uuid);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        User.country = country;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Object deepClone() throws IOException, ClassNotFoundException {
        //序列化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(this);

        //反序列化
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }

    @Override
    public Object clone() {
        User user = null;
        try{
            user = (User) super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void eat() {
        System.out.println(" 吃饭，吃东西，吃零食 ");
    }

    @Override
    public void sleep() {
        System.out.println(" 睡觉，晚安 ");
    }
}
