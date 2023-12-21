package com.BikkadIt.ElectronicStoreNew.service.serviceI;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;
import com.BikkadIt.ElectronicStoreNew.payload.ProductResponse;

public interface ProductServiceI {

     ProductDto createProduct(ProductDto productDto);

     ProductResponse getAllProduct(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

     ProductDto getSingleProduct(String productId);

     ProductDto updateProduct(ProductDto productDto,String productId);

     void  deleteProduct(String productId);


}
