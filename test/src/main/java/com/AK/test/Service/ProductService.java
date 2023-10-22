package com.AK.test.Service;

import com.AK.test.Model.Product;
import com.AK.test.Repository.IProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    IProductRepo productRepo;

    public String addProducts(Product product) {
        productRepo.save(product);
        return "product added";
    }

    public Product getProductById(Integer productId) {
        return productRepo.findById(productId).orElse(null);
    }
}
