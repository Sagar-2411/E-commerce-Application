package com.BikkadIt.ElectronicStoreNew.Controller;


import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;
import com.BikkadIt.ElectronicStoreNew.controller.ProductController;
import com.BikkadIt.ElectronicStoreNew.entity.Product;
import com.BikkadIt.ElectronicStoreNew.payload.ProductResponse;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.ProductServiceI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @MockBean
    private ProductServiceI productServiceI;

    @Autowired
    private ProductController productController;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;

    Product product;

    @BeforeEach
    public void inti() {

        product = Product.builder()
                .title("TV")
                .quantity(10)
                .live(true)
                .stock(false)
                .description("this is tv")
                .addDate(new Date())
                .build();
    }


    @Test
    public void createProductTest() throws Exception {

        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        Mockito.when(productServiceI.createProduct(Mockito.any())).thenReturn(productDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/product/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convetObectTojsonString(product))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").exists());  //we get json data in that data we check name is containt


    }

    public String convetObectTojsonString(Object product) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(product);

    }

    @Test
    public void updataProductTest() throws Exception {

        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        ProductDto productDto1 = ProductDto.builder()
                .title("Mobile")
                .description("this is mobile ")
                .live(true)
                .build();


        String productId = "123";
        Mockito.when(productServiceI.updateProduct(productDto1, productId)).thenReturn(productDto);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/product/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convetObectTojsonString(productDto1))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }


    @Test
    public void getSingleProductTest() throws Exception {

        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        String productId = "123";
        Mockito.when(productServiceI.getSingleProduct(productId)).thenReturn(productDto);


        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound());

    }

    @Test
    public void deleteProductTest() throws Exception {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        String productId = "123";
        Mockito.doNothing().when(productServiceI).deleteProduct(productId);

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/product/" + productId)
                )
                .andDo(print())
                .andExpect(status().isOk());
        Mockito.verify(productServiceI, Mockito.times(1)).deleteProduct(productId);

    }

    @Test
    public void getAllProduct() throws Exception {


        ProductDto productDto = ProductDto.builder()
                .title("TV")
                .quantity(10)
                .live(true)
                .stock(false)
                .description("this is tv")
                .addDate(new Date())
                .build();

        ProductDto productDto1 = ProductDto.builder()
                .title("mobile")
                .quantity(10)
                .live(true)
                .stock(false)
                .description("this is tv")
                .addDate(new Date())
                .build();

        ProductResponse productResponse=new ProductResponse<>();
        productResponse.setContain(Arrays.asList(product,productDto1));
        productResponse.setPageSize(2);
        productResponse.setPageNumber(1);
        productResponse.setIsLast(true);
        productResponse.setTotalElements(1L);

        Mockito.when(productServiceI.getAllProduct(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyString(),Mockito.anyString())).thenReturn(productResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/"))
                .andDo(print())
                .andExpect(status().isFound());




    }




}

