package com.elec5619.g20.dao;

import com.elec5619.g20.pojo.Coupon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponMapperTest {

    @Autowired
    private CouponMapper couponMapper;

    private Coupon coupon1;

    private Coupon coupon2;

    private int count;

    @Before
    public void setUp() {

        this.coupon1 = new Coupon();
        this.coupon1.setTitle("test 1");
        this.coupon1.setCode("60QR59AX");
        this.coupon1.setStore("JustForTest111");
        this.coupon1.setLink("https://www.amazon.com/");
        this.coupon1.setProductCategory("Clothing & Accessories");
        this.coupon1.setStartDate("2021-10-12");
        this.coupon1.setEndDate("2021-10-13");
        this.coupon1.setDescription("Valid on select products from ZYCZFC, through 10/12 while supplies last.");
        this.coupon1.setPicture("no pic");

        this.coupon2 = new Coupon();
        this.coupon2.setTitle("test 2");
        this.coupon2.setCode("80UWZV2D");
        this.coupon2.setStore("ItsForTest111");
        this.coupon2.setLink("https://www.nike.com/");
        this.coupon2.setProductCategory("Kitchen & Dining");
        this.coupon2.setStartDate("2021-10-12");
        this.coupon2.setEndDate("2021-10-19");
        this.coupon2.setDescription("Valid on select products from BLEBRDME, through 10/18 while supplies last.");
        this.coupon2.setPicture("no pic");

        count = couponMapper.listAllCoupon().size();
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon1);
        coupons.add(coupon2);

        this.couponMapper.insertCoupon(coupons);
    }

    @After
    public void deleteTestData() {
        this.couponMapper.deleteByCouponTitle("test 1");
        this.couponMapper.deleteByCouponTitle("test 2");
    }


    @Test
    public void insertAndUpdateCouponTest() {
        Coupon res1 = couponMapper.findCouponByTitle(coupon1.getTitle());
        assertEquals(res1.getTitle(), this.coupon1.getTitle());
        assertEquals(res1.getCode(), this.coupon1.getCode());
        assertEquals(res1.getStore(), this.coupon1.getStore());
        assertEquals(res1.getLink(), this.coupon1.getLink());
        assertEquals(res1.getProductCategory(), this.coupon1.getProductCategory());
        assertEquals(res1.getStartDate(), this.coupon1.getStartDate());
        assertEquals(res1.getEndDate(), this.coupon1.getEndDate());
        assertEquals(res1.getDescription(), this.coupon1.getDescription());
        assertEquals(res1.getPicture(), this.coupon1.getPicture());

        Coupon res2 = couponMapper.findCouponByTitle(coupon2.getTitle());
        assertEquals(res2.getTitle(), this.coupon2.getTitle());
        assertEquals(res2.getCode(), this.coupon2.getCode());
        assertEquals(res2.getStore(), this.coupon2.getStore());
        assertEquals(res2.getLink(), this.coupon2.getLink());
        assertEquals(res2.getProductCategory(), this.coupon2.getProductCategory());
        assertEquals(res2.getStartDate(), this.coupon2.getStartDate());
        assertEquals(res2.getEndDate(), this.coupon2.getEndDate());
        assertEquals(res2.getDescription(), this.coupon2.getDescription());
        assertEquals(res2.getPicture(), this.coupon2.getPicture());

    }

    @Test
    public void ListAllCouponTest() {
        int countNow = couponMapper.listAllCoupon().size();
        assertEquals(count,countNow-2);
    }

    @Test
    public void selectCouponByStoreTest() {
        Coupon res1 = couponMapper.selectCouponByStore(coupon1.getStore()).get(0);
        assertEquals(res1.getTitle(), this.coupon1.getTitle());
        assertEquals(res1.getCode(), this.coupon1.getCode());
        assertEquals(res1.getStore(), this.coupon1.getStore());
        assertEquals(res1.getLink(), this.coupon1.getLink());
        assertEquals(res1.getProductCategory(), this.coupon1.getProductCategory());
        assertEquals(res1.getStartDate(), this.coupon1.getStartDate());
        assertEquals(res1.getEndDate(), this.coupon1.getEndDate());
        assertEquals(res1.getDescription(), this.coupon1.getDescription());
        assertEquals(res1.getPicture(), this.coupon1.getPicture());

        Coupon res2 = couponMapper.selectCouponByStore(coupon2.getStore()).get(0);
        assertEquals(res2.getTitle(), this.coupon2.getTitle());
        assertEquals(res2.getCode(), this.coupon2.getCode());
        assertEquals(res2.getStore(), this.coupon2.getStore());
        assertEquals(res2.getLink(), this.coupon2.getLink());
        assertEquals(res2.getProductCategory(), this.coupon2.getProductCategory());
        assertEquals(res2.getStartDate(), this.coupon2.getStartDate());
        assertEquals(res2.getEndDate(), this.coupon2.getEndDate());
        assertEquals(res2.getDescription(), this.coupon2.getDescription());
        assertEquals(res2.getPicture(), this.coupon2.getPicture());
    }

}
