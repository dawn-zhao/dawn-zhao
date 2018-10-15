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
        map.put("longitude","116.466124");
        map.put("latitude","39.998695");
        map.put("userId","70934");
        map.put("dataTime","0");
        map.put("login_uid","70934");
        map.put("login_token","49bd229230944c33314d22ecaf077268");
        map.put("time","1539335356");
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);

        String linkString = "";

        for (String key : keys) {
            if (!Constants.TIME.equals(key) && !Constants.SIGN.equals(key)) {
                //linkString += key + "=" + request.getParameter(key) + "&";
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
