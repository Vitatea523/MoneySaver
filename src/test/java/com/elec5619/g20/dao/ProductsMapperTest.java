package com.elec5619.g20.dao;

import com.elec5619.g20.api.Makeup;
import com.elec5619.g20.pojo.Products;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsMapperTest {
    @Autowired
    private ProductsMapper ProductsMapper;
    @Autowired
    private CommentMapper CommentMapper;
    @Autowired
    private CollectMapper CollectMapper;
    private Products product1;
    private Products product2;

    @Test
    public void testInsert() throws FileNotFoundException {
        List<Products> products= new ArrayList<>();
        BigDecimal original = new BigDecimal(5);
        BigDecimal original2 = new BigDecimal(43);
        product1= new Products("myTestBrand1","Lippie Pencil","lip_liner",original,"https://cdn.shopify.com/s/files/1/1338/0845/collections/lippie-pencil_grande.jpg?v=1512588769","This is a Lippie Pencil");
        product2= new Products("myTestBrand2","Eye Reviver","eyeshadow",original2,"https://www.dior.com/beauty/version-5.1432748111912/resize-image/ep/0/390/100/0/packshots%252FPDG_Y0929540.jpg","This is a Eye Reviver");
        products.add(product1);
        products.add(product2);
        ProductsMapper.insert(products);
        List<String> brands=ProductsMapper.selectBrand();
        assertTrue(brands.contains("myTestBrand1"));
        assertTrue(brands.contains("myTestBrand2"));
    }

    @Test
    public void testDelete() throws FileNotFoundException {
        CommentMapper.delete();
        CollectMapper.delete();
        ProductsMapper.delete();
        int count = ProductsMapper.selectAllOrderByDiscount().size();
        assertEquals(0,count);
    }
    @Test
    public void testMakeUpAPI() throws IOException, ParseException {
        Makeup productService=new Makeup();
        List<Products> products= productService.getAllProductResult();
        ProductsMapper.insert(products);
    }

    @Test
    public void  testSelectAllOrderByDiscount(){
        List<Products> products= ProductsMapper.selectAllOrderByDiscount();
        if(products.size()!=0){
            assertTrue(products.size()>0);
            BigDecimal discount1=products.get(0).getCurrentPrice().divide(products.get(0).getOrginalPrice(),4, RoundingMode.HALF_UP);
            BigDecimal discount2=products.get(1).getCurrentPrice().divide(products.get(1).getOrginalPrice(),4,RoundingMode.HALF_UP);
            assertTrue(discount1.compareTo(discount2) < 1);
        }
    }

    @Test
    public void  testSelectBrand(){
        List<String> brands=ProductsMapper.selectBrand();
        //When products not clear
        if(brands.size()!=0){
            assertTrue(brands.contains("nyx"));
            assertTrue(brands.contains("colourpop"));
            assertTrue(brands.contains("dior"));
        }
    }

    @Test
    public void  testSelectByBrand(){
        List<Products> products=ProductsMapper.selectByBrand("dior");
        assertTrue(products.size()>0);
    }

}
