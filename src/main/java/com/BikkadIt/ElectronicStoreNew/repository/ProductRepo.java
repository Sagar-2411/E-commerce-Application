package com.BikkadIt.ElectronicStoreNew.repository;

import com.BikkadIt.ElectronicStoreNew.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, String> {



}
