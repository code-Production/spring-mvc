package com.geekbrains;

import com.geekbrains.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/add")
    public String getProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProductForm";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productRepository.addProduct(product);
        return "redirect:/products/all";
    }

    @GetMapping("/all")
    public String showProducts(Model model) {
        model.addAttribute("products", productRepository.getProductList());
        return "showProducts";
    }


}
