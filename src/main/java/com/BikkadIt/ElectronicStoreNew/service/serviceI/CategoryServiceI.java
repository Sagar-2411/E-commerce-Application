package com.BikkadIt.ElectronicStoreNew.service.serviceI;


import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.payload.CategoryResponse;

public interface CategoryServiceI {


    CategoryDto create(CategoryDto categoryDto);

    CategoryDto upadate(CategoryDto categoryDto,String categoryId);

    CategoryDto getSingleCategory(String categoryId);

    void deleteCategory(String categoryId);

    CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
