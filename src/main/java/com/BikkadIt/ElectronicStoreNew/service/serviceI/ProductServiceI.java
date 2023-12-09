package com.BikkadIt.ElectronicStoreNew.service.serviceI;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;

public interface ProductServiceI {

     ProductDto createProduct(ProductDto productDto);

     ProductDto getAllProduct();

     ProductDto getSingleProduct(String productId);

     ProductDto updateProduct(String productId, CategoryDto categoryDto);

     void  deleteProduct(String productId);


}
