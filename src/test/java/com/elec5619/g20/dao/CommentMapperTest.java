package com.elec5619.g20.dao;

import com.elec5619.g20.pojo.Comment;
import com.elec5619.g20.pojo.Products;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentMapperTest {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    public ProductsMapper productsMapper;

    private Comment comment1;

    private Comment comment2;

    @Before
    public void setUp() {
        List<Products> productsList = this.productsMapper.selectAllOrderByDiscount();

        int productId1 = productsList.get(0).getProductId();
        int productId2 = productsList.get(1).getProductId();

        this.comment1 = new Comment();
        this.comment2 = new Comment();
        comment1.setProductId(productId1);
        comment2.setProductId(productId2);
        comment1.setContent("I like it.");
        comment2.setContent("Very Good");

        this.commentMapper.addComment(comment1);
        this.commentMapper.addComment(comment2);
    }

    @After
    public void tearDown() {
        this.commentMapper.delete();
    }

    @Test
    public void queryCommentByProduct() {
        List<Products> productsList = this.productsMapper.selectAllOrderByDiscount();

        int productId1 = productsList.get(0).getProductId();
        int productId2 = productsList.get(1).getProductId();

        List<Comment> commentList1 = this.commentMapper.queryCommentByProduct(productId1);
        assertEquals(commentList1.size(), 1);
        assertEquals(commentList1.get(0).getContent(), "I like it.");

        List<Comment> commentList2 = this.commentMapper.queryCommentByProduct(productId2);
        assertEquals(commentList2.size(), 1);
        assertEquals(commentList2.get(0).getContent(), "Very Good");
    }

    @Test
    public void deleteCommentById() {
        List<Products> productsList = this.productsMapper.selectAllOrderByDiscount();

        int productId1 = productsList.get(0).getProductId();
        int productId2 = productsList.get(1).getProductId();

        List<Comment> commentList1 = this.commentMapper.queryCommentByProduct(productId1);
        List<Comment> commentList2 = this.commentMapper.queryCommentByProduct(productId2);

        this.commentMapper.deleteCommentById(commentList1.get(0));
        this.commentMapper.deleteCommentById(commentList2.get(0));

        List<Comment> commentList3 = this.commentMapper.queryCommentByProduct(productId1);
        List<Comment> commentList4 = this.commentMapper.queryCommentByProduct(productId2);

        assertEquals(commentList3.size(), 0);
        assertEquals(commentList4.size(), 0);
    }

}