package com.BikkadIt.ElectronicStoreNew.controller;


import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;
import com.BikkadIt.ElectronicStoreNew.constant.AppConstant;
import com.BikkadIt.ElectronicStoreNew.payload.ProductResponse;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.ProductServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductServiceI productService;

    @PostMapping("/product")

    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("Entering request for Create product data in controller layer");
        ProductDto Product = productService.createProduct(productDto);
        System.out.println(Product);
        log.info("complete request for create product data controller layer");
        return new ResponseEntity<>(Product, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ProductResponse> getAllProduct(
            @RequestParam (value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam (value = "pageSize"  , defaultValue = AppConstant.PAGE_SIZE,required   = false)Integer pageSize,
            @RequestParam (value = "sortBy"    , defaultValue = AppConstant.SORT_BY_PRODUCT,required     = false) String sortBy,
            @RequestParam (value = "sortDir"  , defaultValue = AppConstant.SORT_DIR ,required = false) String sortDir

    ) {
        log.info("Entering request for getAll product data in controller layer");
        ProductResponse allProduct = productService.getAllProduct(pageNumber, pageSize, sortBy, sortDir);
        log.info("complete request for getAll product data controller layer");
        return new ResponseEntity<>(allProduct, HttpStatus.FOUND);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductByID(@PathVariable String productId) {
        log.info("Entering request for gate product data in controller layer");
        ProductDto singleProduct = productService.getSingleProduct(productId);
        log.info("complete request for gate product data in controller layer");
        return new ResponseEntity<>(singleProduct, HttpStatus.FOUND);

    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable String productId) {
        log.info("Entering request for update product data in controller layer");
        ProductDto productDto1 = productService.updateProduct(productDto, productId);
        log.info("complete request for update product data controller layer");
        return new ResponseEntity<>(productDto1, HttpStatus.OK);
    }


}
