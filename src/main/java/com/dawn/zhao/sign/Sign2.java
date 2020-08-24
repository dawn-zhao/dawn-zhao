package com.dawn.zhao.sign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dawn.zhao.Constants;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;

public class Sign2 {

    //验签、 生成签名
    public static void main(String[] args) {

        String requestSign = "";
        TreeMap<String,String> map = new TreeMap<>();
        map.put("anchorUserId","114429");
        map.put("topic","打豆豆的直播间");
        map.put("roomTypeId","1");
        map.put("provinceId","110000");
        map.put("cityId","110100");
        map.put("longitude","116.466317");
        map.put("latitude","39.998011");
        map.put("login_uid","114429");
        map.put("login_token","b757bbce7d5c55dc96a9d9f746353532");
        map.put("time","1541556385");
        map.put("coverUrl","https://nos.netease.com/nim/NDMzNTIzNA==/bmltYV8xODk3Nzk0NzA0XzE1Mjc2MDM0OTQyOTFfMjU4Y2YzZGMtMTBhYS00ZjVkLWJhZGEtMmNkMWQ5YmMzOGFi");
//        map.put("userId", "70934");
//        map.put("icon", "https://nos.netease.com/nim/NDMzNTIzNA==/bmltYV8xODk3Nzk0NzA0XzE1Mjc2MDM0OTQyOTFfMjU4Y2YzZGMtMTBhYS00ZjVkLWJhZGEtMmNkMWQ5YmMzOGFi");
//        map.put("nickName", "灯火阑珊处");
//        map.put("birthday", "1996-06-06");
//        map.put("gender", "2");
//        map.put("time", "1");
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        String linkString = "";

        for (String key : keys) {
            if (!Constants.TIME.equals(key) && !Constants.SIGN.equals(key)) {
                linkString += map.get(key);
            }
        }

        String time = map.get(Constants.TIME);
        String key = Constants.KEY;//自己修改
        String sign = DigestUtils.md5Hex(linkString + time + key);
        boolean equals = sign.equals(requestSign);
        System.out.println(sign);


    }

}
