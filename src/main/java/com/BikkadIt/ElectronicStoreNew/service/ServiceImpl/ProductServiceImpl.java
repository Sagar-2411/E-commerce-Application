package com.BikkadIt.ElectronicStoreNew.service.ServiceImpl;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;
import com.BikkadIt.ElectronicStoreNew.Dto.UserDto;
import com.BikkadIt.ElectronicStoreNew.constant.AppConstant;
import com.BikkadIt.ElectronicStoreNew.entity.Product;
import com.BikkadIt.ElectronicStoreNew.entity.User;
import com.BikkadIt.ElectronicStoreNew.exception.ResourseNotFoundException;
import com.BikkadIt.ElectronicStoreNew.repository.ProductRepo;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.ProductServiceI;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductServiceI {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;



   @Override
    public ProductDto createProduct(ProductDto productDto) {
       log.info("Entering dao call for create product");
        String str = UUID.randomUUID().toString();
        productDto.setProductId(str);
        productDto.setAddDate(new Date());
//       SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
//       String format = simpleDateFormat.format(new Date());
//       productDto.setAddDate(format);
       Product product = this.modelMapper.map(productDto, Product.class);
       Product product1 = productRepo.save(product);
       log.info("complete dao call for create product");
       return this.modelMapper.map(product1, ProductDto.class);


    }

    @Override
    public ProductDto getAllProduct() {

       log.info("Entering dao call for getAllProduct");
        List<Product> productList = productRepo.findAll();
        log.info("complete dao call for getAllProduct");
        return this.modelMapper.map(productList,ProductDto.class);
    }

    @Override
    public ProductDto getSingleProduct(String productId) {
        log.info("Entering dao call for get product data {}" ,productId);
        Product product = productRepo.findById(productId).orElseThrow(() -> new ResourseNotFoundException(AppConstant.NOT_FOUND));
        log.info("complete dao call for get product data {}" ,productId);
        return this.modelMapper.map(product,ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String productId) {
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourseNotFoundException("this ID is not found"));
        product.setTitle(productDto.getTitle());
        product.setAddDate(productDto.getAddDate());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        product.setLive(product.isLive());
        product.setStock(product.isStock());

        Product product1 = productRepo.save(product);
        return modelMapper.map(product1,ProductDto.class);
    }

    @Override
    public void deleteProduct(String productId) {

    }
}
