package com.elec5619.g20.service;

import com.elec5619.g20.pojo.User;
import com.elec5619.g20.vo.JsonResult;

public interface UserService {

    JsonResult<User> register(User user);

    JsonResult<User> login(User user);

    JsonResult<User> updateUser(User user);

    JsonResult<User> selectUserById(int id);

    JsonResult<User> changePassword(int userId, String oldPassword, String newPassword);
}
