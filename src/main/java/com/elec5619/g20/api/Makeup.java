package com.elec5619.g20.api;

import com.elec5619.g20.pojo.Products;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Makeup {
    List<Products> productsList=new ArrayList<>();

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

    public  List<Products> getAllProductResult() throws IOException, ParseException {
        String reqUrl="http://makeup-api.herokuapp.com/api/v1/products.json";
        Response response =this.API(reqUrl);
        JSONArray jsonArray  = (JSONArray) (new JSONParser().parse(response.body().string()));
        JsonArrayFormat(jsonArray);
        return productsList;
    }



    private String JsonArrayFormat(JSONArray jsonArray) {
        StringBuilder stringBuilder = new StringBuilder();
        if (jsonArray == null) return null;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject product = (JSONObject) jsonArray.get(i);
            String brand= (String)product.get("brand");
            if(brand==null) continue;
            String productName =((String)product.get("name")).strip();
            String category =(String)product.get("product_type");
            String orginalPrice =(String)product.get("price");
            if(orginalPrice==null||Double.parseDouble(orginalPrice)==0) continue;
            String picture =(String)product.get("image_link");
            if(orginalPrice==null) continue;
            String description=(String)product.get("description");
            if(description==null||description.length()==0) continue;
            description=description.strip();
            Products products=new Products( brand,productName,category,new BigDecimal(orginalPrice),picture,description);
            productsList.add(products);
            System.out.println(products);
        }
        return stringBuilder.toString();
    }
}
