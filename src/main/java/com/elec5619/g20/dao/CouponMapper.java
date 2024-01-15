package com.elec5619.g20.dao;
import java.util.List;

import com.elec5619.g20.pojo.Coupon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {

    int insertCoupon(List<Coupon> record);

    void deleteAllCoupon();

    List<Coupon> listAllCoupon();

    List<Coupon> selectCouponByStore(String storeName);

    List<String> selectAllStore();

    int deleteByCouponTitle(String title);

    Coupon findCouponByTitle(String title);




}
