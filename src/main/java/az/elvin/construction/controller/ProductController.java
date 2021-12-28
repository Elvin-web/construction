package az.elvin.construction.controller;

import az.elvin.construction.service.CategoryService;
import az.elvin.construction.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;

    }

    @GetMapping("/products")
    public String productList(@RequestParam("page") Long page, Model model) {
        model.addAttribute("categories", categoryService.getCategoryList().getData());
        model.addAttribute("products", productService.getProductList(page).getData());
        return "products";
    }

}
