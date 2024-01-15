package com.elec5619.g20.controller;

import com.elec5619.g20.pojo.*;
import com.elec5619.g20.service.CollectService;
import com.elec5619.g20.service.CommentService;
import com.elec5619.g20.service.ProductsService;
import com.elec5619.g20.vo.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CollectService collectService;

    @GetMapping("/{productId}")
    public JsonResult<Products> selectByPrimaryKey(@PathVariable Integer productId) {
        return productsService.selectByPrimaryKey(productId);
    }

    @GetMapping("/api")
    public JsonResult<List<Products>> getAllProductResultFromAPI() {
        return productsService.getAllProductResultFromAPI();
    }

    @GetMapping("/sorted")
    public JsonResult<List<Products>> selectAllOrderByDiscount() {
        return productsService.selectAllOrderByDiscount();
    }

    @GetMapping("/brands")
    public JsonResult<List<String>> selectBrand() {
        return productsService.selectBrand();
    }

    @GetMapping("/brand/{brand}")
    public JsonResult<List<Products>> selectByBrand(@PathVariable String brand) {
        return productsService.selectByBrand(brand);
    }

    @DeleteMapping("/delete")
    public JsonResult<Integer> delete() {
        collectService.delete();
        commentService.delete();
        return productsService.delete();
    }

    @GetMapping("/{userId}/{productId}")
    public JsonResult<ProductComment> queryProductById(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId) {
        return productsService.queryProductCommentById(userId, productId);
    }

    @PostMapping("/addComment")
    public JsonResult<Comment> addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @DeleteMapping("/deleteComment")
    public JsonResult<Comment> deleteComment(@RequestBody Comment comment) {
        return commentService.deleteCommentById(comment);
    }

    @PostMapping("/collect")
    public JsonResult<Collect> addCollect(@RequestBody Collect collect) {
        return collectService.addCollect(collect);
    }

    @DeleteMapping("/cancelCollect")
    public JsonResult<Collect> deleteCollect(@RequestBody Collect collect) {
        return collectService.deleteCollect(collect);
    }

    @GetMapping("/like/{userId}/{productId}")
    public JsonResult<ProductComment> like(@PathVariable("userId") int userId, @PathVariable("productId") int productId) {
        return productsService.like(userId, productId);
    }
}
