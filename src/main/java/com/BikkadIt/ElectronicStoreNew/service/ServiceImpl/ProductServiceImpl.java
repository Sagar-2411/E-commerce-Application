package com.BikkadIt.ElectronicStoreNew.service.ServiceImpl;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;
import com.BikkadIt.ElectronicStoreNew.Dto.UserDto;
import com.BikkadIt.ElectronicStoreNew.constant.AppConstant;
import com.BikkadIt.ElectronicStoreNew.entity.Product;
import com.BikkadIt.ElectronicStoreNew.entity.User;
import com.BikkadIt.ElectronicStoreNew.repository.ProductRepo;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.ProductServiceI;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductServiceI {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;



   @Override
    public ProductDto createProduct(ProductDto productDto) {
        String str = UUID.randomUUID().toString();
        productDto.setProductId(str);
        Product product = this.modelMapper.map(productDto, Product.class);
        return this.modelMapper.map(product, ProductDto.class);


    }

    @Override
    public ProductDto getAllProduct() {

        List<Product> productList = productRepo.findAll();
        return this.modelMapper.map(productList,ProductDto.class);
    }

    @Override
    public ProductDto getSingleProduct(String ProductId) {
        Product product = productRepo.findById(ProductId).orElseThrow(() -> new ResolutionException(AppConstant.NOT_FOUND));

        return this.modelMapper.map(product,ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(String productId) {
        return null;
    }

    @Override
    public void deleteProduct(String productId) {

    }
}
