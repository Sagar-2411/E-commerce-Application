package com.BikkadIt.ElectronicStoreNew.service.serviceI;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;

public interface ProductServiceI {

     ProductDto createProduct(ProductDto productDto);

     ProductDto getAllProduct();

     ProductDto getSingleProduct(String productId);

     ProductDto updateProduct(ProductDto productDto,String ProductId);

     void  deleteProduct(String productId);


}
