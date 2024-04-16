package com.ptit.productmanagement.dto.request;

import org.springframework.data.domain.Pageable;

public class SearchProductRequest {
    private String name;

    private Pageable pageable;

    public SearchProductRequest(String name, Pageable pageable) {
        this.name = name;
        this.pageable = pageable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
