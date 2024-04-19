package com.ptit.productmanagement.service.impl;

import com.ptit.productmanagement.dto.request.AddProductRequest;
import com.ptit.productmanagement.dto.request.EditProductRequest;
import com.ptit.productmanagement.dto.request.SearchProductRequest;
import com.ptit.productmanagement.dto.response.PageResponse;
import com.ptit.productmanagement.entity.Product;
import com.ptit.productmanagement.repository.ProductRepository;
import com.ptit.productmanagement.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean addProduct(AddProductRequest addProductRequest) {
        try {
            if (productRepository.existsByName(addProductRequest.getName())) {
                return false;
            }

            Product product = new Product();
            product.setName(addProductRequest.getName());
            product.setDescription(addProductRequest.getDescription());
            product.setPrice(Float.parseFloat(addProductRequest.getPrice()));
            product.setCategory(addProductRequest.getCategory());
            product.setQuantity(addProductRequest.getQuantity());
            product.setProductImage(addProductRequest.getProductImage());

            productRepository.save(product);

            return true;
        } catch (Exception e) {
            log.info("Add product failed: {}", e.getMessage());
        }

        return false;
    }

    @Override
    public boolean editProductById(Integer id, EditProductRequest editProductRequest) {
        try {
            if (!productRepository.existsById(id)) {
                return false;
            }

            Product product = productRepository.getReferenceById(id);
            product.setDescription(editProductRequest.getDescription());
            product.setPrice(Float.parseFloat(editProductRequest.getPrice()));
            product.setCategory(editProductRequest.getCategory());
            product.setQuantity(editProductRequest.getQuantity());
            product.setProductImage(editProductRequest.getProductImage());

        } catch (Exception e) {
            log.info("Edit product failed: {}", e.getMessage());
        }

        return false;
    }

    @Override
    public PageResponse searchProduct(SearchProductRequest searchProductRequest) {
        Pageable pageable = searchProductRequest.getPageable();
        String name = searchProductRequest.getName();

        if (name != null && !name.isBlank()) {
            return new PageResponse(productRepository.findAllByName(name, pageable), productRepository.countByName(name));
        }

        return new PageResponse(productRepository.findAll(pageable).getContent(), productRepository.count());
    }

    @Override
    public boolean deleteProductById(Integer id) {
        try {
            if (!productRepository.existsById(id)) {
                return false;
            }

            productRepository.deleteById(id);

            return true;
        } catch (Exception e) {
            log.info("Delete product failed: {}", e.getMessage());
        }

        return false;
    }
}
