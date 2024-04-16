package com.ptit.productmanagement.dto.response;

import com.ptit.productmanagement.entity.Product;

import java.util.List;

public class PageResponse {
    private List<Product> data;

    private Long totalElements;

    public PageResponse(List<Product> data, Long totalElements) {
        this.data = data;
        this.totalElements = totalElements;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
