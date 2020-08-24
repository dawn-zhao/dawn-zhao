package com.dawn.zhao.demo;

import com.alibaba.fastjson.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo {
    public static void main(String args[]){
//        B b = new B();
//        b.setName("asdsa");
//        b.setId("13123");
//
//        A a = new A();
//        a.setAa("aaaaaaaaaa");
//        a.setAb(b);
//
//        System.out.println(JSONObject.toJSONString(a));

        System.out.println(camel4underline("associatedChannelId,enableDate,theWeight,code,upWareHouses,latitude,shopSettleCustomers,shopSettleMethods,remark,returnQuota,shopProperty,shopToGroups,modifyTime,disableDate,wareHouseId,enableBy,shopId,channelId,longitude,fromDB,modifyBy,isPreRetreat,managedType,platformId,preRetreatDate,createChannelId,currencyTypeId,createBy,areaId,shopSortId,createTime,name,disableBy,status"));

    }
    public static String camel4underline(String param){
        Pattern p=Pattern.compile("[A-Z]");
        if(param==null ||param.equals("")){
            return "";
        }
        StringBuilder builder=new StringBuilder(param);
        Matcher mc=p.matcher(param);
        int i=0;
        while(mc.find()){
            builder.replace(mc.start()+i, mc.end()+i, "_"+mc.group().toLowerCase());
            i++;
        }

        if('_' == builder.charAt(0)){
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }
}
class A {
    private String aa;
    private B ab;

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public B getAb() {
        return ab;
    }

    public void setAb(B ab) {
        this.ab = ab;
    }
}

class B {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
