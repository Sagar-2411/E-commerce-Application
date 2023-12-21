package com.BikkadIt.ElectronicStoreNew.Services;

import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;
import com.BikkadIt.ElectronicStoreNew.entity.Product;
import com.BikkadIt.ElectronicStoreNew.payload.ProductResponse;
import com.BikkadIt.ElectronicStoreNew.repository.ProductRepo;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.ProductServiceI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@SpringBootTest

public class ProductServiceTest {

    @Autowired
    private ProductServiceI productServiceI;


    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private ProductRepo productRepo;


    Product product;

    @BeforeEach
    public void init() {
        product = Product.builder()
                .title("tv")
                .description("this is tv")
                .stock(true)
                .live(true)
                .addDate(new Date())
                .quantity(10)
                .build();
    }

    @Test
    public void CreateProductTest() {

        Mockito.when(productRepo.save(Mockito.any())).thenReturn(product);

        ProductDto product1 = productServiceI.createProduct(modelMapper.map(product, ProductDto.class));

        System.out.println(product1.getTitle());
        Assertions.assertNotNull(product1);
        Assertions.assertEquals("tv", product1.getTitle());

    }

    @Test
    public void getSingleProduct(){

        String productId="123";

        Mockito.when(productRepo.findById(Mockito.anyString())).thenReturn(Optional.of(product));

        ProductDto productDto = productServiceI.getSingleProduct(productId);

        Assertions.assertEquals("tv",productDto.getTitle());
        Assertions.assertNotNull(productDto);


    }

    @Test
    public void getAllProduct(){

       Product product1 = Product.builder()
                .title("mobile")
                .description("this is tv")
                .stock(true)
                .live(true)
                .addDate(new Date())
                .quantity(10)
                .build();

       Product product2 = Product.builder()
                .title("motor")
                .description("this is tv")
                .stock(true)
                .live(true)
                .addDate(new Date())
                .quantity(10)
                .build();

        List<Product> products = Arrays.asList(product, product1, product2);
        Page page=new PageImpl(products);

        Mockito.when(productRepo.findAll((Pageable) Mockito.any())).thenReturn(page);

        ProductResponse allProduct = productServiceI.getAllProduct(1, 2, "title", "ase");
        Assertions.assertEquals(3,allProduct.getTotalElements());

    }

    @Test
    public void UpdateProductTest(){
        Product product2 = Product.builder()
                .title("motor")
                .description("this is tv")
                .stock(true)
                .live(true)
                .addDate(new Date())
                .quantity(10)
                .build();

        String productId="123";
        Mockito.when(productRepo.findById(Mockito.anyString())).thenReturn(Optional.of(product));

        Mockito.when(productRepo.save(Mockito.any())).thenReturn(product);
        ProductDto productDto = productServiceI.updateProduct(modelMapper.map(product2, ProductDto.class), productId);

        Assertions.assertEquals("motor",productDto.getTitle());
        Assertions.assertEquals(product2.getTitle(),product.getTitle());
    }
    @Test
    public void deleteProductTest(){
        String productId="";
        Mockito.when(productRepo.findById(Mockito.anyString())).thenReturn(Optional.of(product));
        productServiceI.deleteProduct(productId);

        Mockito.verify(productRepo,Mockito.times(1)).delete(product);


    }




}

