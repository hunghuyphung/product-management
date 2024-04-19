package com.ptit.productmanagement.repository;

import com.ptit.productmanagement.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer>, JpaRepository<Product, Integer> {
    List<Product> findAllByName(String name, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    long countByName(String name);

    long count();

    boolean existsByName(String name);
}
