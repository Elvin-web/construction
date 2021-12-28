package az.elvin.construction.controller;

import az.elvin.construction.service.CategoryService;
import az.elvin.construction.service.ProductCategoryService;
import az.elvin.construction.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    private final CategoryService categoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService, CategoryService categoryService) {
        this.productCategoryService = productCategoryService;
        this.categoryService = categoryService;

    }

    @GetMapping("/products/category")
    public String productCategoryByPageAndAmount(@RequestParam("name") String name,
                                                 @RequestParam("page") Long page,
                                                 @RequestParam(value = "min", required = false) Double min,
                                                 @RequestParam(value = "max", required = false) Double max,
                                                 Model model) {
        model.addAttribute("categories", categoryService.getCategoryList().getData());
        model.addAttribute("products",
                productCategoryService.productInfoByCategoryNameAndPageAndAmount(name,page,min,max).getData());
              return "product-category";
    }

}
