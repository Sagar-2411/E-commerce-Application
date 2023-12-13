package com.BikkadIt.ElectronicStoreNew.controller;


import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceI productService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct(ProductDto productDto){
        ProductDto Product = productService. createProduct(productDto);
        return new ResponseEntity<>(Product, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ProductDto> getAllProduct(){
        ProductDto allProduct = productService.getAllProduct();
        return new ResponseEntity<>(allProduct,HttpStatus.FOUND);
    }

@GetMapping("/ProductId")
    public ResponseEntity<ProductDto> getProductByID(@PathVariable String ProductId){
        ProductDto singleProduct = productService.getSingleProduct(ProductId);
        return new ResponseEntity<>(singleProduct,HttpStatus.FOUND);

    }



}
