package com.geekster.ExpenseTracker.repository;

import com.geekster.ExpenseTracker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
