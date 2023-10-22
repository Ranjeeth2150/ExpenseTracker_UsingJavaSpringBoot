package com.geekster.ExpenseTracker.service;

import com.geekster.ExpenseTracker.model.Product;
import com.geekster.ExpenseTracker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository iProductRepo;

    public String addAProduct(Product product) {

        iProductRepo.save(product);
        return "Product added";
    }
    public String addListOfProducts(List<Product> products) {
        iProductRepo.saveAll(products);

        return "products added";
    }



    public Product getProductById(Integer productId) {
        return iProductRepo.findById(productId).orElse(null);
    }

    public List<Product> getAllProducts() {
        return iProductRepo.findAll();
    }

    public Product getproduct(Integer productId) {

        Product product = iProductRepo.findById(productId).orElse(null);
        if(product!=null)
        {
            return product;
        }
        throw new IllegalStateException("Not a Valid Product");
    }
}