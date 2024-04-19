package com.ptit.productmanagement.controller;

import com.ptit.productmanagement.dto.request.AddProductRequest;
import com.ptit.productmanagement.dto.request.EditProductRequest;
import com.ptit.productmanagement.dto.request.SearchProductRequest;
import com.ptit.productmanagement.dto.response.PageResponse;
import com.ptit.productmanagement.dto.response.ToastResponse;
import com.ptit.productmanagement.enums.PageSizeOpt;
import com.ptit.productmanagement.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.ptit.productmanagement.constants.Constants.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    private static final String PRODUCT_MANAGER_FRM = "productManagerFrm";

    private static final String ADD_PRODUCT_FRM = "addProductFrm";

    private static final String EDIT_PRODUCT_FRM = "editProductFrm";

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getProductTablePage(Model model) {
        SearchProductRequest searchProductRequest = new SearchProductRequest(null, PageRequest.of(0, 10));

        PageResponse res = productService.searchProduct(searchProductRequest);

        int totalPage = (int) (res.getTotalElements() / 10);
        totalPage = res.getTotalElements() % 10 != 0 ? totalPage + 1 : totalPage;
        totalPage = totalPage == 0 ? 1 : totalPage;

        List<Integer> pageList = new ArrayList<>();
        for (int i = 0; i < totalPage; i++) {
            pageList.add(i);
        }

        model.addAttribute("products", res.getData());
        model.addAttribute("searchProductRequest", searchProductRequest);
        model.addAttribute(START_ORDER, 1);
        model.addAttribute(TL_PAGE_LIST, pageList);
        model.addAttribute(TL_TOTAL_PAGE, totalPage);
        model.addAttribute(TL_PAGE_SIZE_OPT_LIST, PageSizeOpt.sequenceFrom(10));

        return PRODUCT_MANAGER_FRM;
    }

    @PostMapping("/search")
    public String searchProduct(SearchProductRequest searchProductRequest,
                                @RequestParam(defaultValue = "10") int pageSize,
                                @RequestParam(defaultValue = "0") int pageNumber,
                                Model model) {
        searchProductRequest.setPageable(PageRequest.of(pageNumber, pageSize));

        var res = productService.searchProduct(searchProductRequest);

        int totalPage = (int) (res.getTotalElements() / pageSize);
        totalPage = res.getTotalElements() % pageSize != 0 ? totalPage + 1 : totalPage;
        totalPage = totalPage == 0 ? 1 : totalPage;
        int prevPageNumber = pageNumber >= totalPage ? 0 : pageNumber;

        List<Integer> pageList = new ArrayList<>();
        pageList.add(prevPageNumber);
        for (int i = 0; i < totalPage; i++) {
            if (i != prevPageNumber) {
                pageList.add(i);
            }
        }

        model.addAttribute("products", res.getData());
        model.addAttribute("searchProductRequest", searchProductRequest);
        model.addAttribute(START_ORDER, prevPageNumber * pageSize  + 1);
        model.addAttribute(TL_PAGE_LIST, pageList);
        model.addAttribute(TL_TOTAL_PAGE, totalPage);
        model.addAttribute(TL_PAGE_SIZE_OPT_LIST, PageSizeOpt.sequenceFrom(pageSize));

        return PRODUCT_MANAGER_FRM;
    }

    @GetMapping("/add")
    public String getAddProductPage(Model model) {
        model.addAttribute("addProductRequest", new AddProductRequest());

        return ADD_PRODUCT_FRM;
    }

    @PostMapping("/add")
    public String addProduct(@Valid AddProductRequest addProductRequest, BindingResult bindingResult, Model model) {
        model.addAttribute("addProductRequest", addProductRequest);

        if (bindingResult.hasErrors()) {
            return ADD_PRODUCT_FRM;
        }

        boolean added = productService.addProduct(addProductRequest);
        if (added) {
            return "redirect:/products";
        }

        model.addAttribute("addedCheck", false);

        return ADD_PRODUCT_FRM;
    }

    @GetMapping("/{id}/edit")
    public String getEditProductPage(@PathVariable Integer id,
                                     @RequestParam(defaultValue = "") String name,
                                     @RequestParam(defaultValue = "") String price,
                                     @RequestParam(defaultValue = "") String description,
                                     @RequestParam(defaultValue = "") Integer quantity,
                                     @RequestParam(defaultValue = "") String productImage,
                                     Model model) {
        model.addAttribute("id", id);
        model.addAttribute("editProductRequest", new EditProductRequest(name, price, description, quantity, productImage));

        return EDIT_PRODUCT_FRM;
    }

    @PostMapping("/{id}/edit")
    public String editProduct(@Valid EditProductRequest editProductRequest, BindingResult bindingResult, Model model,
                              @PathVariable Integer id) {
        model.addAttribute("id", id);
        model.addAttribute("editProductRequest", new EditProductRequest());

        if (bindingResult.hasErrors()) {
            return EDIT_PRODUCT_FRM;
        }

        boolean added = productService.editProductById(id, editProductRequest);
        if (added) {
            return "redirect:/products";
        }

        model.addAttribute("addedCheck", false);

        return EDIT_PRODUCT_FRM;
    }

    @PostMapping("/{id}/delete")
    public String deleteProductById(@PathVariable Integer id, Model model) {
        boolean deleted = productService.deleteProductById(id);

        ToastResponse toastResponse = new ToastResponse(deleted, deleted ?
                "Xóa sản phẩm thành công" : "Xóa sản phẩm không thành công");
        model.addAttribute(TL_TOAST_RESPONSE, toastResponse);

        return this.getProductTablePage(model);
    }
}
