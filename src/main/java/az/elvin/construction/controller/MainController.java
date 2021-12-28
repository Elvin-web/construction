package az.elvin.construction.controller;


import az.elvin.construction.repo.ProductRepository;
import az.elvin.construction.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private final ProductService productService;

    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/")
    public String indexRedirect() {
        return "redirect:/index";
    }


    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("products", productService.getDefaultProductList().getData());
        return "index";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }

    @GetMapping(value = "/contact")
    public String contact() {
        return "contact";
    }
}
