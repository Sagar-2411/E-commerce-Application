package com.BikkadIt.ElectronicStoreNew.repository;

import com.BikkadIt.ElectronicStoreNew.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, String> {
}
