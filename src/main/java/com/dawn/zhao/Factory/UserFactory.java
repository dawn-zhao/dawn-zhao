package com.dawn.zhao.Factory;

import com.dawn.zhao.bean.User;

public interface UserFactory <T extends User> {
    T create(Integer userId, String userName, String phone);
}
