package mc.com.serverSite.api.controller.page;

import mc.com.serverSite.api.dto.ProductDto;
import mc.com.serverSite.entity.Product;
import mc.com.serverSite.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductPage {

    private final ProductService productService;

    public ProductPage(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProduct(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        if (product == null) return "not_found";
        model.addAttribute("product", ProductDto.toDto(product));
        return "product";
    }

}
