package com.elec5619.g20.pojo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Comment {
    private Integer commentId;

    private Integer productId;

    private String content;

    private String time;

    public Comment() {
    }

    public Comment(Integer commentId, Integer productId, String content) {
        this.commentId = commentId;
        this.productId = productId;
        this.content = content;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        this.time = sdf.format(timestamp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(commentId);
        sb.append(", productId=").append(productId);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}