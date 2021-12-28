package az.elvin.construction.controller;

import az.elvin.construction.service.CategoryService;
import az.elvin.construction.service.ProductAmountService;
import az.elvin.construction.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductAmountController {

    private final ProductAmountService productAmountService;

    private final CategoryService categoryService;

    public ProductAmountController(ProductAmountService productAmountService, CategoryService categoryService) {
        this.productAmountService = productAmountService;
        this.categoryService = categoryService;

    }


    @GetMapping("/products/amount")
    public String productAmount(@RequestParam(value = "min", required = false) Double min,
                                @RequestParam(value = "max", required = false) Double max,
                                @RequestParam("page") Long page,Model model) {
        model.addAttribute("categories", categoryService.getCategoryList().getData());
        model.addAttribute("products", productAmountService.productInfoByAmountAndPage(min, max,page).getData());
        return "product-amount";
    }
}
