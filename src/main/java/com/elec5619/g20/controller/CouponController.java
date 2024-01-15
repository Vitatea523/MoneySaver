package com.elec5619.g20.controller;

import com.elec5619.g20.pojo.Coupon;
import com.elec5619.g20.service.CouponService;
import com.elec5619.g20.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("/listCoupon")
    public JsonResult<List<Coupon>> listCoupon() {
        return  couponService.listAllCoupon();
    }

    @GetMapping("/filterCouponByStore")
    public JsonResult<List<Coupon>> filterByStore(String storeName) {
        return couponService.selectCouponByStore(storeName);
    }

    @GetMapping("/getAllStore")
    public JsonResult<List<String>> getAllStore() {
        return couponService.selectAllStore();
    }

    @PostMapping("/updateCouponDatabase")
    public JsonResult<String> updateCouponDatabase() {
        try {
            return couponService.updateCouponDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

