package com.elec5619.g20.service.impl;

import com.elec5619.g20.api.Makeup;
import com.elec5619.g20.dao.CollectMapper;
import com.elec5619.g20.dao.CommentMapper;
import com.elec5619.g20.pojo.Collect;
import com.elec5619.g20.pojo.ProductComment;
import com.elec5619.g20.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.elec5619.g20.pojo.Products;
import com.elec5619.g20.dao.ProductsMapper;
import com.elec5619.g20.service.ProductsService;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductsServiceImpl implements ProductsService{

    @Autowired
    private Makeup makeupAPI;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CollectMapper collectMapper;

    private Jedis jedis;

    public ProductsServiceImpl() {
        jedis = new Jedis();
        Map<String, String> products = new HashMap<>();
        products.put("0", "0");
        jedis.hmset("product", products);
    }

    @Override
    public JsonResult<List<Products>> getAllProductResultFromAPI() {
        try {
            List<Products> result = makeupAPI.getAllProductResult();
            productsMapper.insert(result);
            return JsonResult.build(200, "Success", result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return JsonResult.build(400,"fail",null);
        }

    }

    @Override
    public JsonResult<Products> selectByPrimaryKey(Integer productId) {
        try {
            Products result = productsMapper.selectByPrimaryKey(productId);
            return JsonResult.build(200, "Success", result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return JsonResult.build(400,"fail",null);
        }
    }

    @Override
    public JsonResult<List<Products>> selectAllOrderByDiscount() {
        try {
            List<Products> result = productsMapper.selectAllOrderByDiscount();
            return JsonResult.build(200, "Success", result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return JsonResult.build(400,"fail",null);
        }
    }

    @Override
    public JsonResult<List<Products>> selectByBrand(String brand) {
        try {
            List<Products> result = productsMapper.selectByBrand(brand);
            return JsonResult.build(200, "Success", result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return JsonResult.build(400,"fail",null);
        }
    }

    @Override
    public JsonResult<List<String>> selectBrand() {
        try {
            List<String> result = productsMapper.selectBrand();
            return JsonResult.build(200, "Success", result);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return JsonResult.build(400,"fail",null);
        }
    }

    @Override
    public JsonResult<Integer>  delete() {
        Integer result = productsMapper.delete();
        if(result != 0) {
            return JsonResult.build(200, "Success", null);
        } else {
            return JsonResult.build(400, "Fail", null);
        }
    }

    @Override
    public JsonResult<ProductComment> queryProductCommentById(int userId, int productId) {
        ProductComment productComment = new ProductComment();
        try {
            productComment.setProducts(productsMapper.selectByPrimaryKey(productId));
            productComment.setCommentList(commentMapper.queryCommentByProduct(productId));
            Collect result = collectMapper.queryByUserProduct(userId, productId);
            productComment.setCollect(result != null);
            if (jedis.hget("product", String.valueOf(productId)) == null) {
                jedis.hset("product", String.valueOf(productId), "0");
            }
            productComment.setLikeNumber(Integer.parseInt(jedis.hget("product", String.valueOf(productId))));
            productComment.setLike(jedis.sismember(String.valueOf(userId), String.valueOf(productId)));
            return JsonResult.build(200, "Success", productComment);
        }catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), null);
        }
    }

    @Override
    public JsonResult<ProductComment> like(int userId, int productId) {
        if (jedis.hget("product", String.valueOf(productId)) == null) {
            jedis.hset("product", String.valueOf(productId), "0");
        }
        int num = Integer.parseInt(jedis.hget("product", String.valueOf(productId)));
        if (jedis.sismember(String.valueOf(userId), String.valueOf(productId))) {
            jedis.srem(String.valueOf(userId), String.valueOf(productId));
            jedis.hset("product", String.valueOf(productId), String.valueOf(num - 1));
        }else {
            jedis.hset("product", String.valueOf(productId), String.valueOf(num + 1));
            jedis.sadd(String.valueOf(userId), String.valueOf(productId));
        }
        ProductComment productComment = new ProductComment();
        try {
            productComment.setCommentList(commentMapper.queryCommentByProduct(productId));
            productComment.setProducts(productsMapper.selectByPrimaryKey(productId));
            Collect result = collectMapper.queryByUserProduct(userId, productId);
            productComment.setCollect(result != null);
            productComment.setLikeNumber(Integer.parseInt(jedis.hget("product", String.valueOf(productId))));
            productComment.setLike(jedis.sismember(String.valueOf(userId), String.valueOf(productId)));
            return JsonResult.build(200, "Success", productComment);
        }catch (Exception e) {
            return JsonResult.build(400, e.getMessage(), null);
        }
    }

}
