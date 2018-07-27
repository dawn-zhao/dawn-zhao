package com.dawn.zhao.service;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

public class SysUserInfoServiceTest {

//    @Autowired
//    private SysUserInfoService sysUserInfoService;
//
//    @Test
//    public void  addUser() throws SQLException {
//        Map<String,Object> user = new HashMap<String, Object>();
//        user.put("userName","dawn.zhao");
//        user.put("email","dawmZhao1996@gmail.com");
//        int result = sysUserInfoService.addUser(user);
//
//        Map<String,Object> userSession = new HashMap<String, Object>();
//        user.put("loginAccount","dawmZhao1996@gmail.com");
//        user.put("password","password");
//        result = sysUserInfoService.addUserSession(userSession);
//
//        System.out.println(result);
//    }

    @Resource(name="jedisPool")
    private JedisPool jedisPool;

    private String messageKey = "im:message";

    @Test
    public void lpushMessage() throws InterruptedException {
        try(Jedis jedis = jedisPool.getResource()){
            while (true){
                JSONObject message = new JSONObject();
                message.put("type", 1);
                message.put("roomId", 19074980);
                jedis.lpush(messageKey, message.toJSONString());
                Thread.sleep(5000);
            }
        }
    }
}
