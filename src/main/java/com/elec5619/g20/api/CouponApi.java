package com.elec5619.g20.api;

import com.elec5619.g20.pojo.Coupon;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CouponApi {

    List<Coupon> coupons = new ArrayList<>();

    private Response API(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .header("User-Agent", "OkHttp Headers.java")
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    public List<Coupon> getAllCoupons() throws IOException, ParseException {
        String reqUrl = "https://couponapi.org/api/getFeed/?API_KEY=be58da8f52ea55d3cba71606753db46d&format=json";
        Response response = this.API(reqUrl);
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(response.body().string());
        JSONArray jsonArray = (JSONArray) jsonObject.get("offers");

        JsonArrayFormat(jsonArray);
        return coupons;

    }

    private String JsonArrayFormat(JSONArray jsonArray) {
        StringBuilder stringBuilder = new StringBuilder();
        if (jsonArray == null) return null;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject coupon = (JSONObject) jsonArray.get(i);

            String productCategory = (String) coupon.get("categories");
            String title = (String) coupon.get("title");
            String code = (String) coupon.get("code");
            String store = (String) coupon.get("source");
            String link = (String) coupon.get("merchant_home_page");
            String startDate = (String) coupon.get("start_date");
            String endDate = (String) coupon.get("end_date");
            String description = (String) coupon.get("description");
            String picture = (String) coupon.get("brand_logo");

            Coupon couponTemp = new Coupon(productCategory,title, code, store, link, startDate, endDate, description, picture);
            coupons.add(couponTemp);
        }
        return stringBuilder.toString();
    }
}