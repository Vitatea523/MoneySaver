package com.elec5619.g20.pojo;

public class Market {
    private Integer marketId;

    private String marketName;

    private String address;

    private Integer phoneNumber;

    public Market() {
    }

    public Market(Integer marketId, String marketName, String address, Integer phoneNumber) {
        this.marketId = marketId;
        this.marketName = marketName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", marketId=").append(marketId);
        sb.append(", marketName=").append(marketName);
        sb.append(", address=").append(address);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append("]");
        return sb.toString();
    }
}