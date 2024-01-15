package com.elec5619.g20.service.impl;

import com.elec5619.g20.api.CouponApi;
import com.elec5619.g20.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elec5619.g20.pojo.Coupon;
import com.elec5619.g20.dao.CouponMapper;
import com.elec5619.g20.service.CouponService;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponApi couponService;

    @Override
    public JsonResult<List<Coupon>> listAllCoupon() {
        try {
            List<Coupon> coupons = couponMapper.listAllCoupon();
            return JsonResult.build(200, "success", coupons);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return JsonResult.build(400, "fail", null);
        }
    }

    @Override
    public JsonResult<List<String>> selectAllStore() {
        try {
            List<String> stores = couponMapper.selectAllStore();
            return JsonResult.build(200, "success", stores);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return JsonResult.build(400, "fail", null);
        }
    }

    @Override
    public JsonResult<List<Coupon>> selectCouponByStore(String storeName) {
        try {
            List<Coupon> coupons = couponMapper.selectCouponByStore(storeName);
            return JsonResult.build(200, "success", coupons);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return JsonResult.build(400, "fail", null);
        }
    }

    @Override
    public JsonResult<String> updateCouponDatabase() {

        try{
            couponMapper.deleteAllCoupon();
            List<Coupon> coupons= couponService.getAllCoupons();
            couponMapper.insertCoupon(coupons);
            return JsonResult.build(200, "success", "update successfully");
        }catch (Exception e){
            return JsonResult.build(400, "fail", e.getMessage());
        }
    }


}
