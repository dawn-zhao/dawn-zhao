package com.dawn.zhao.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mvc.xml")
public class SysUserInfoServiceTest {

    @Autowired
    private SysUserInfoService sysUserInfoService;

    @Test
    public void  addUser() throws SQLException {
        Map<String,Object> user = new HashMap<String, Object>();
        user.put("userName","dawn.zhao");
        user.put("email","dawmZhao1996@gmail.com");
        int result = sysUserInfoService.addUser(user);

        Map<String,Object> userSession = new HashMap<String, Object>();
        user.put("loginAccount","dawmZhao1996@gmail.com");
        user.put("password","password");
        result = sysUserInfoService.addUserSession(userSession);

        System.out.println(result);
    }
}
