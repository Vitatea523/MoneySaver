package com.elec5619.g20.dao;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.elec5619.g20.pojo.Products;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductsMapper {

    int insert(List<Products> record);//add product from external api

    //return one product
    Products selectByPrimaryKey(@Param("productId") Integer productId);

    List<Products> selectByCategory(@Param("category")String category);

    List<Products> selectByBrand(@Param("brand")String brand);//filter the product by the brand

    int delete();// delete all product

    List<Products> selectAllOrderByDiscount();//order by the discount

    //return all distinct category
    List<String> selectBrand();



}
