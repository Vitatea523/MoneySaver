package com.elec5619.g20.pojo;

import java.math.BigDecimal;

public class Products {
    private Integer productId;

    private String brand;

    private String productName;

    private String category;

    private BigDecimal orginalPrice;

    private BigDecimal currentPrice;

    private String description;
    private String picture;

    public Products() {
    }

    public Products(Integer productId, String brand, String productName, String category, BigDecimal orginalPrice, BigDecimal currentPrice, String description, String picture) {
        this.productId = productId;
        this.brand = brand;
        this.productName = productName;
        this.category = category;
        this.orginalPrice = orginalPrice;
        this.currentPrice = currentPrice;
        this.description = description;
        this.picture = picture;
    }

    public Products(String brand, String productName,
                    String category, BigDecimal orginalPrice, String picture, String description) {
        this.brand = brand;
        this.productName = productName;
        this.category = category;
        this.orginalPrice = orginalPrice;
        this.picture = picture;
        this.description=description;

        //auto fill
        BigDecimal discount = new BigDecimal(1 - ((int) (5 + Math.random() * (45 - 5 + 1)) * 0.01));
        this.currentPrice = orginalPrice.multiply(discount);
        this.currentPrice = this.currentPrice.setScale(2, BigDecimal.ROUND_HALF_UP);

//        TODO categoryId
        //add category to the database

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getOrginalPrice() {
        return orginalPrice;
    }

    public void setOrginalPrice(BigDecimal orginalPrice) {
        this.orginalPrice = orginalPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", brand='" + brand + '\'' +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                ", orginalPrice=" + orginalPrice +
                ", currentPrice=" + currentPrice +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
