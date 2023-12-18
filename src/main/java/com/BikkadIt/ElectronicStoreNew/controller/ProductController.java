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
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto Product = productService.createProduct(productDto);
        System.out.println(Product);
        return new ResponseEntity<>(Product, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ProductDto> getAllProduct(){
        ProductDto allProduct = productService.getAllProduct();
        return new ResponseEntity<>(allProduct,HttpStatus.FOUND);
    }

@GetMapping("/productId")
    public ResponseEntity<ProductDto> getProductByID(@PathVariable String productId){
        ProductDto singleProduct = productService.getSingleProduct(productId);
        return new ResponseEntity<>(singleProduct,HttpStatus.FOUND);

    }

    @PutMapping("/productId")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto,@PathVariable String productId){
        ProductDto productDto1 = productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(productDto1,HttpStatus.OK);
    }



}
