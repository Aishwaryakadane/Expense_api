package com.AK.test.Controller;

import com.AK.test.Model.Product;
import com.AK.test.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("products")
    public String addProduct(@RequestBody Product product){
        return productService.addProducts(product);
    }

}
