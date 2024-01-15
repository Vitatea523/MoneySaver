package com.elec5619.g20.pojo;

public class Coupon {


    private Integer couponId;

    private String productCategory;

    private String title;

    private String code;

    private String store;

    private String link;

    private String startDate;

    private String endDate;

    private String description;

    private String picture;

    public Coupon() {
    }

    public Coupon(String productCategory,String title, String code, String store, String link, String startDate, String endDate
            , String description, String picture) {
//        this.couponId = couponId;
        this.title = title;
        this.productCategory = productCategory;
        this.code = code;
        this.store = store;
        this.link = link;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.picture = picture;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponId=" + couponId +
                ", productCategory='" + productCategory + '\'' +
                ", title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", store=" + store +
                ", link='" + link + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description=" + description +
                ", picture='" + picture + '\'' +
                '}';
    }
}