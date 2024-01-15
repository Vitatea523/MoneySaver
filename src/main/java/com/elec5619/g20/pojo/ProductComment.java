package com.elec5619.g20.pojo;

import java.util.List;

public class ProductComment {

    private Products products;

    private List<Comment> commentList;

    private Boolean isCollect;

    private Integer likeNumber;

    private Boolean isLike;

    public ProductComment() {
    }

    public ProductComment(Products products, List<Comment> commentList) {
        this.products = products;
        this.commentList = commentList;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Boolean getCollect() {
        return isCollect;
    }

    public void setCollect(Boolean collect) {
        isCollect = collect;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    @Override
    public String toString() {
        return "ProductComment{" +
                "products=" + products +
                ", commentList=" + commentList +
                ", isCollect=" + isCollect +
                ", likeNumber=" + likeNumber +
                ", isLike=" + isLike +
                '}';
    }
}
