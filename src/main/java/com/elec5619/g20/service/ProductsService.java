package com.elec5619.g20.service;

import com.elec5619.g20.pojo.ProductComment;
import com.elec5619.g20.pojo.Products;
import com.elec5619.g20.pojo.User;
import com.elec5619.g20.vo.JsonResult;
import org.apache.ibatis.annotations.Param;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface ProductsService{

    JsonResult<List<Products>> getAllProductResultFromAPI();

    JsonResult<Products> selectByPrimaryKey(@Param("productId")Integer productId);

    JsonResult<List<Products>> selectByBrand(@Param("brand") String brand);

    JsonResult<List<Products>> selectAllOrderByDiscount();

    JsonResult<List<String>> selectBrand();

    JsonResult<Integer> delete();

    JsonResult<ProductComment> queryProductCommentById(int userId, int productId);

    JsonResult<ProductComment> like(int userId, int productId);
}
