package com.ptit.productmanagement.dto.request;

import com.ptit.productmanagement.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddProductRequest {
    @NotBlank(message = "Tên sản phẩm không được để trống. Vui lòng kiểm tra lại")
    private String name;

    @NotNull(message = "Giá không được để trống. Vui lòng kiểm tra lại")
    private String price;

    private Category category;

    @NotBlank(message = "Mô tả không được để trống. Vui lòng kiểm tra lại")
    private String description;

    @NotNull(message = "Số lượng không được để trống. Vui lòng kiểm tra lại")
    private Integer quantity;

    @NotBlank(message = "Ảnh không được để trống. Vui lòng kiểm tra lại")
    private String productImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
