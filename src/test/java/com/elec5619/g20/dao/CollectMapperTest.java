package com.elec5619.g20.dao;

import com.elec5619.g20.pojo.Collect;
import com.elec5619.g20.pojo.Products;
import com.elec5619.g20.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectMapperTest {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    public ProductsMapper productsMapper;

    private Collect collect1;

    private Collect collect2;

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

        int id1 = this.userMapper.login(this.user1).getUserId();
        int id2 = this.userMapper.login(this.user2).getUserId();

        List<Products> productsList = this.productsMapper.selectAllOrderByDiscount();

        int productId1 = productsList.get(0).getProductId();
        int productId2 = productsList.get(1).getProductId();

        this.collect1 = new Collect(id1, productId1);
        this.collect2 = new Collect(id2, productId2);

        this.collectMapper.addCollect(collect1);
        this.collectMapper.addCollect(collect2);
    }

    @After
    public void tearDown() {
        this.collectMapper.deleteCollect(collect1);
        this.collectMapper.deleteCollect(collect2);

        this.userMapper.deleteByUsername("Tony");
        this.userMapper.deleteByUsername("Mary");
    }

    @Test
    public void deleteTest() {
        int count = this.collectMapper.delete();
        assertEquals(2, count);
    }

    @Test
    public void queryCollectByUserId() {
        List<Products> productsList1 = this.collectMapper.queryCollectByUserId(this.user1);

        assertEquals(productsList1.size(), 1);
        assertNotNull(productsList1.get(0));

        List<Products> productsList2 = this.collectMapper.queryCollectByUserId(this.user2);

        assertEquals(productsList2.size(), 1);
        assertNotNull(productsList2.get(0));
    }

    @Test
    public void queryByUserProduct() {
        int id1 = this.userMapper.login(this.user1).getUserId();
        int id2 = this.userMapper.login(this.user2).getUserId();

        List<Products> productsList = this.productsMapper.selectAllOrderByDiscount();

        int productId1 = productsList.get(0).getProductId();
        int productId2 = productsList.get(1).getProductId();
        Collect res1 = collectMapper.queryByUserProduct(id1, productId1);
        Collect res2 = collectMapper.queryByUserProduct(id2, productId2);

        assertEquals((int) res1.getProductId(), productId1);
        assertEquals((int) res2.getProductId(), productId2);
    }
}