package com.elec5619.g20.pojo;

import java.math.BigInteger;
import java.security.MessageDigest;

public class User {
    private Integer userId;

    private String username;

    private String password;

    private String eMail;

    private String address;

    public User() {
    }

    public User(Integer userId, String username, String password, String eMail, String address) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.eMail = eMail;
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String md5(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", eMail=").append(eMail);
        sb.append(", address=").append(address);
        sb.append("]");
        return sb.toString();
    }
}