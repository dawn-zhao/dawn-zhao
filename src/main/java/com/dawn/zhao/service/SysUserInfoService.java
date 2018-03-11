package com.dawn.zhao.service;

import com.dawn.zhao.dao.SysUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service("sysUserInfoService")
public class SysUserInfoService {

    @Autowired
    private SysUserInfoMapper sysUserInfoDao;

    public int addUser(Map<String,Object> user) throws SQLException{
        int result = sysUserInfoDao.insertUser(user);
        if(result>0){
            result = 1/0;
        }
        return result;
    }


    public int addUserSession(Map<String,Object> userSession){
        return sysUserInfoDao.insertUserSession(userSession);
    }

}
