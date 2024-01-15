package com.elec5619.g20.controller;

import com.elec5619.g20.pojo.Collect;
import com.elec5619.g20.pojo.Products;
import com.elec5619.g20.pojo.User;
import com.elec5619.g20.service.CollectService;
import com.elec5619.g20.service.UserService;
import com.elec5619.g20.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CollectService collectService;

    @PostMapping("/regist")
    public JsonResult<User> regist(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public JsonResult<User> login(@RequestBody User user) {
        return userService.login(user);
    }

    @PostMapping("/update")
    public JsonResult<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/collect")
    public JsonResult<List<Products>> queryCollectProduct(@RequestBody User user) {
        return collectService.queryCollectByUserId(user);
    }

    @DeleteMapping("/cancelCollect")
    public JsonResult<Collect> deleteCollect(@RequestBody Collect collect) {
        return collectService.deleteCollect(collect);
    }

    @GetMapping("/detail/{id}")
    public JsonResult<User> selectUserById(@PathVariable("id") int id) {
        return userService.selectUserById(id);
    }

    @GetMapping("/changePass/{id}/{oldPassword}/{newPassword}")
    public JsonResult<User> changePass(@PathVariable("id") int id,
                                       @PathVariable("oldPassword") String oldPassword,
                                       @PathVariable("newPassword") String newPassword) {
        return userService.changePassword(id, oldPassword, newPassword);
    }
}
