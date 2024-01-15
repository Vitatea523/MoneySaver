package com.elec5619.g20.dao;

import com.elec5619.g20.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface UserMapper {

    @Transactional
    User findUserByName(String name);

    @Transactional
    int register(User user);

    @Transactional
    User login(User user);

    @Transactional
    int updateUser(User user);

    @Transactional
    User selectUserById(int id);

    @Transactional
    int deleteByUsername(String name);
}
