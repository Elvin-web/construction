package az.elvin.construction.controller;


import az.elvin.construction.service.CategoryService;
import az.elvin.construction.service.ProductDetailsService;
import az.elvin.construction.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductDetailsController {

    private final ProductDetailsService productDetailsService;
    private final CategoryService categoryService;

    public ProductDetailsController(ProductDetailsService productDetailsService, CategoryService categoryService) {
        this.productDetailsService = productDetailsService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products/detail")
    public String productDetails(@RequestParam("name") String name, Model model) {
        model.addAttribute("product", productDetailsService.productInfoByName(name).getData());
        return "product-details";
    }
}
