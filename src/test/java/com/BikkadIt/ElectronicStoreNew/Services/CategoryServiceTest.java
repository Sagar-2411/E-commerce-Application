package com.BikkadIt.ElectronicStoreNew.Services;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.Dto.UserDto;
import com.BikkadIt.ElectronicStoreNew.entity.Category;
import com.BikkadIt.ElectronicStoreNew.payload.CategoryResponse;
import com.BikkadIt.ElectronicStoreNew.repository.CategoryRepo;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.CategoryServiceI;
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
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoryServiceTest {


    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryServiceI categoryService;

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
    public void createCateTest() {
        Mockito.when(categoryRepo.save(Mockito.any())).thenReturn(category);
        CategoryDto categoryDto = categoryService.create(modelMapper.map(category, CategoryDto.class));
        System.out.println(categoryDto.getDescription());
        System.out.println(categoryDto.getTitle());
        Assertions.assertNotNull(categoryDto);
        Assertions.assertEquals("this is tv product", categoryDto.getTitle());
    }

    @Test
    public void updateCategoryTest() {
        String categoryId = "sdf";
        Mockito.when(categoryRepo.findById(Mockito.anyString())).thenReturn(Optional.of(category));
        Mockito.when(categoryRepo.save(Mockito.any())).thenReturn(category);
        CategoryDto categoryDto = CategoryDto.builder()
                .title("Mobile")
                .description("this is a nice procuct")
                .coverImage("tns.gpag")
                .build();
        CategoryDto upadateCate = categoryService.upadate(categoryDto, categoryId);
        Assertions.assertEquals("Mobile", upadateCate.getTitle());
        System.out.println(upadateCate.getTitle());
    }

    @Test
    public void getAllCateTest() {

        Category category1 = Category.builder()
                .description("this is mixer product")
                .title("Mixer")
                .coverImage("asd.png")
                .build();
        Category category2 = Category.builder()
                .description("this is trimmer product")
                .title("Trimmer")
                .coverImage("asd.png")
                .build();
        List<Category> list = Arrays.asList(category, category1, category2);
        Page<Category> page = new PageImpl(list);
        Mockito.when(categoryRepo.findAll((Pageable) Mockito.any())).thenReturn(page);
        CategoryResponse allCategory = categoryService.getAllCategory(1, 2, "title", "desc");
        System.out.println(allCategory.getTotalElements());
        Assertions.assertEquals(3, allCategory.getContain().size());
    }

    @Test
    public void deleteCateTest() {

        Mockito.when(categoryRepo.findById(Mockito.any())).thenReturn(Optional.of(category));
        String categoryId = "";
        categoryService.deleteCategory(categoryId);
        Mockito.verify(categoryRepo, Mockito.times(1)).delete(category);
    }
    @Test
    public void getSingleCategory(){
        String categoryId="";
        Mockito.when(categoryRepo.findById(Mockito.any())).thenReturn(Optional.of(category));
        CategoryDto singleCategory = categoryService.getSingleCategory(categoryId);
        Assertions.assertEquals("TV",singleCategory.getTitle());

        System.out.println(singleCategory.getDescription());
    }

}
