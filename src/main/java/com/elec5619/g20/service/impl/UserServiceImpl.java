package com.elec5619.g20.service.impl;

import com.elec5619.g20.dao.UserMapper;
import com.elec5619.g20.pojo.User;
import com.elec5619.g20.service.UserService;
import com.elec5619.g20.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public JsonResult<User> register(User user) {
        try {
            User isExitUser = userMapper.findUserByName(user.getUsername());
            if (isExitUser != null) {
                return JsonResult.build(400, "Username already exists", null);
            }else {
                user.setPassword(user.md5(user.getPassword()));
                userMapper.register(user);
                return JsonResult.build(200, "Success", user);
            }
        } catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), null);
        }
    }

    @Override
    public JsonResult<User> login(User user) {
        try {
            user.setPassword(user.md5(user.getPassword()));
        } catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), null);
        }
        try {
            User tempUser = userMapper.login(user);
            if(tempUser == null){
                return JsonResult.build(400, "The password is wrong", null);
            }else{
                return JsonResult.build(200, "Success", tempUser);
            }
        } catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), null);
        }
    }

    @Override
    public JsonResult<User> updateUser(User user) {
        try {
            if (user.getPassword() != null) {
                user.setPassword(user.md5(user.getPassword()));
            }
            int userId = userMapper.updateUser(user);
            if(userId == 0){
                return JsonResult.build(400,"Update failed!", null);
            }else {
                return JsonResult.build(200,"Success", user);
            }
        } catch (Exception e) {
            return JsonResult.build(400,e.getMessage(), null);
        }
    }

    @Override
    public JsonResult<User> selectUserById(int id) {
        try {
            User user = userMapper.selectUserById(id);
            if(user == null){
                return JsonResult.build(400,"No such user!", null);
            }else {
                return JsonResult.build(200,"Success", user);
            }
        } catch (Exception e) {
            return JsonResult.build(400,e.getMessage(), null);
        }
    }

    @Override
    public JsonResult<User> changePassword(int id, String oldPassword, String newPassword) {
        try {
            User user = userMapper.selectUserById(id);
            String com = user.md5(oldPassword);
            if(!user.getPassword().equals(com)){
                return JsonResult.build(400,"Old password is not correct", null);
            }else {
                user.setPassword(user.md5(newPassword));
                user.setUserId(id);
                userMapper.updateUser(user);
                return JsonResult.build(200,"Success", user);
            }
        } catch (Exception e) {
            return JsonResult.build(400,e.getMessage(), null);
        }
    }
}
