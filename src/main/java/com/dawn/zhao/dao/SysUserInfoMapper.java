package com.dawn.zhao.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SysUserInfoMapper {

    @Insert("insert into sys_user_info(user_name, email) values(#{userName}, #{email})")
    public int insertUser(Map<String,Object> userMap);

    @Insert("insert into sys_user_session(login_account, password) values(#{loginAccount}, #{password})")
    public int insertUserSession(Map<String,Object> userSessionMap);
}
