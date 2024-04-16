package com.ptit.productmanagement.enums;

public enum Category {
    ELECTRONIC("Đồ điện tử"),
    HOUSE_WARE("Đồ gia dụng"),
    CLOTHES("Quần áo");

    private final String vnName;

    Category(String vnName) {
        this.vnName = vnName;
    }

    public String getVnName() {
        return vnName;
    }
}
