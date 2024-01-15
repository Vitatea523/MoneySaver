package com.elec5619.g20.dao;

import com.elec5619.g20.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private User user1;

    private User user2;

    @Before
    public void setUp() {
        this.user1 = new User();
        this.user1.setUsername("Tony");
        this.user1.setPassword("TonyAccount");
        this.user1.seteMail("Tony@gmail.com");
        this.user1.setAddress("NSW");

        this.user2 = new User();
        this.user2.setUsername("Mary");
        this.user2.setPassword("MaryAccount");
        this.user2.seteMail("Mary@gmail.com");
        this.user2.setAddress("VIC");

        this.userMapper.register(user1);
        this.userMapper.register(user2);
    }

    @After
    public void tearDown() {
        this.userMapper.deleteByUsername("Tony");
        this.userMapper.deleteByUsername("Mary");
    }


    @Test
    public void registerAndFindUserByNameTest() {
        User res1 = userMapper.findUserByName(user1.getUsername());
        assertEquals(res1.getUsername(), this.user1.getUsername());
        assertEquals(res1.getPassword(), this.user1.getPassword());

        User res2 = userMapper.findUserByName(user2.getUsername());
        assertEquals(res2.getUsername(), this.user2.getUsername());
        assertEquals(res2.getPassword(), this.user2.getPassword());
    }

    @Test
    public void loginTest() {
        User res1 = userMapper.login(this.user1);
        assertEquals(res1.getUsername(), this.user1.getUsername());
        assertEquals(res1.getPassword(), this.user1.getPassword());
        assertEquals(res1.getAddress(), this.user1.getAddress());
        assertEquals(res1.geteMail(), this.user1.geteMail());


        User res2 = userMapper.login(this.user2);
        assertEquals(res2.getUsername(), this.user2.getUsername());
        assertEquals(res2.getPassword(), this.user2.getPassword());
        assertEquals(res2.getAddress(), this.user2.getAddress());
        assertEquals(res2.geteMail(), this.user2.geteMail());
    }

    @Test
    public void updateTest() {
        int id = this.userMapper.login(this.user1).getUserId();
        this.user1.setPassword("JerryAccount");
        this.user1.seteMail("Jerry@gmail.com");
        this.user1.setAddress("Australia");
        this.user1.setUserId(id);
        this.userMapper.updateUser(this.user1);

        User res1 = userMapper.login(this.user1);
        assertEquals(res1.getUsername(), this.user1.getUsername());
        assertEquals(res1.getPassword(), this.user1.getPassword());
        assertEquals(res1.getAddress(), this.user1.getAddress());
        assertEquals(res1.geteMail(), this.user1.geteMail());
    }

    @Test
    public void selectUserByIdTest() {
        int id = this.userMapper.login(this.user1).getUserId();
        User res1 = userMapper.selectUserById(id);
        assertEquals(res1.getUsername(), this.user1.getUsername());
        assertEquals(res1.getPassword(), this.user1.getPassword());
        assertEquals(res1.getAddress(), this.user1.getAddress());
        assertEquals(res1.geteMail(), this.user1.geteMail());
    }
}
