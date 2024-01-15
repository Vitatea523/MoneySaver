package com.elec5619.g20.service;

import com.elec5619.g20.pojo.Coupon;
import com.elec5619.g20.vo.JsonResult;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface CouponService {

    JsonResult<List<Coupon>> listAllCoupon();

    JsonResult<List<Coupon>> selectCouponByStore(String storeName);

    JsonResult<List<String>> selectAllStore();

    JsonResult<String> updateCouponDatabase() throws IOException, ParseException;
}
