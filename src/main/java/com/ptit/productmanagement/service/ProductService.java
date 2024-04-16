package com.ptit.productmanagement.service;

import com.ptit.productmanagement.dto.request.AddProductRequest;
import com.ptit.productmanagement.dto.request.EditProductRequest;
import com.ptit.productmanagement.dto.request.SearchProductRequest;
import com.ptit.productmanagement.dto.response.PageResponse;

public interface ProductService {
    boolean addProduct(AddProductRequest addProductRequest);

    boolean editProductById(Integer id, EditProductRequest editProductRequest);

    PageResponse searchProduct(SearchProductRequest searchProductRequest);

    boolean deleteProductById(Integer id);
}
