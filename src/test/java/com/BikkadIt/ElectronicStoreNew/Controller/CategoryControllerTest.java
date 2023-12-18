package com.BikkadIt.ElectronicStoreNew.Controller;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.entity.Category;
import com.BikkadIt.ElectronicStoreNew.helper.ApiResponse;
import com.BikkadIt.ElectronicStoreNew.payload.CategoryResponse;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.CategoryServiceI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @MockBean
    private CategoryServiceI categoryServiceI;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;

    Category category;

    @BeforeEach
    public void init() {
        category = Category.builder()
                .title("TV")
                .coverImage("abc.png")
                .description("it is a good product")
                .build();
    }

    @Test
    public void CreateCategory() throws Exception {

        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        Mockito.when(categoryServiceI.create(Mockito.any())).thenReturn(categoryDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/category/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convetObjectToJsonString(category))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").exists());  //we get json data in that data we check name is containt
    }

    public String convetObjectToJsonString(Object category) throws JsonProcessingException {
        try {
            return new ObjectMapper().writeValueAsString(category);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Test
    public void updateCategoryTest() throws Exception {

        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        String categoryId = "123";
        CategoryDto categoryDto1 = CategoryDto.builder()
                .description("This is mobile")
                .coverImage("abc.png")
                .title("mobile")
                .build();


        Mockito.when(categoryServiceI.upadate(categoryDto1, categoryId)).thenReturn(categoryDto);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/category/" + categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convetObjectToJsonString(categoryDto1))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        //.andExpect(jsonPath("$title").exists());

    }


    @Test
    public void getAllCategory() throws Exception {

        CategoryDto categoryDto1 = CategoryDto.builder()
                .description("This is mobile")
                .coverImage("abc.png")
                .title("mobile")
                .build();
        CategoryDto categoryDto2 = CategoryDto.builder()
                .description("This is mobile")
                .coverImage("abc.png")
                .title("mobile")
                .build();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContain(Arrays.asList(categoryDto1,categoryDto2));
        categoryResponse.setPageSize(10);
        categoryResponse.setPageNumber(2);
        categoryResponse.setTotalElements(30);
        categoryResponse.setIsLast(false);

        Mockito.when(categoryServiceI.getAllCategory(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyString())).thenReturn(categoryResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    public void getSingleCategory() throws Exception {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        String categoryId="123";
        Mockito.when(categoryServiceI.getSingleCategory(Mockito.anyString())).thenReturn(categoryDto);

       this.mockMvc.perform(MockMvcRequestBuilders.get("/api/category/"+categoryId)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void deleteCategory() throws Exception {
        String categoryId = "123";

//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("delete succefully");
//        apiResponse.setSuccess(true);
//        apiResponse.setStatus(HttpStatus.OK);


        Mockito.doNothing().when(categoryServiceI).deleteCategory(categoryId);
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/category/" +categoryId))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
