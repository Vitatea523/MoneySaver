package com.elec5619.g20.pojo;

public class Collect {
    private Integer userId;

    private Integer productId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Collect() {
    }

    public Collect(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", productId=").append(productId);
        sb.append("]");
        return sb.toString();
    }
}