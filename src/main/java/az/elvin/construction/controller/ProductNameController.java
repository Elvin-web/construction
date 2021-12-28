package az.elvin.construction.controller;

import az.elvin.construction.service.CategoryService;
import az.elvin.construction.service.ProductNameService;
import az.elvin.construction.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductNameController {

    private final ProductNameService productNameService;

    private final CategoryService categoryService;

    public ProductNameController(ProductNameService productNameService, CategoryService categoryService) {
        this.productNameService = productNameService;
        this.categoryService = categoryService;

    }

    @GetMapping("/products/")
    public String productNameByPage(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam("page") Long page, Model model) {
        model.addAttribute("categories", categoryService.getCategoryList().getData());
        model.addAttribute("products", productNameService.productInfoByNameAndPage(name,page).getData());
        return "product-name";
    }
}
