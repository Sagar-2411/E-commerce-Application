package com.BikkadIt.ElectronicStoreNew.service.ServiceImpl;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.constant.AppConstant;
import com.BikkadIt.ElectronicStoreNew.entity.Category;
import com.BikkadIt.ElectronicStoreNew.exception.ResourseNotFoundException;
import com.BikkadIt.ElectronicStoreNew.payload.CategoryResponse;
import com.BikkadIt.ElectronicStoreNew.repository.CategoryRepo;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.CategoryServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryServiceI {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        log.info("Entering dao call for save Category data");
        String catId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(catId);
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category save = this.categoryRepo.save(category);
        log.info("complete dao call for save category data");

        return this.modelMapper.map(save,CategoryDto.class);


    }

    @Override
    public CategoryDto upadate(CategoryDto categoryDto, String categoryId) {
        log.info("Entering dao call for update Category data {}" ,categoryId);
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourseNotFoundException(AppConstant.NOT_FOUND + categoryId));
       category.setDescription(categoryDto.getDescription());
       category.setTitle(categoryDto.getTitle());
       category.setCoverImage(categoryDto.getCoverImage());
        Category save = this.categoryRepo.save(category);
        log.info("complete dao call for save category data {}", categoryId);
        return this.modelMapper.map(save,CategoryDto.class);
    }

    @Override
    public CategoryDto getSingleCategory(String categoryId) {
        log.info("Entering dao call for get Category data {}" ,categoryId);

        Category category=  categoryRepo.findById(categoryId).orElseThrow(()->new ResourseNotFoundException(AppConstant.NOT_FOUND +categoryId));
        log.info("complete dao call for get category data {}", categoryId);
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @DeleteMapping("/{categoryId}")
    @Override
    public void deleteCategory(String categoryId) {
        log.info("Entering dao call for delete Category data {}" ,categoryId);
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourseNotFoundException(AppConstant.NOT_FOUND + categoryId));
        categoryRepo.delete(category);
        log.info("complete dao call for delete category data {}", categoryId);

    }

    @Override
    public CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        log.info("Entering dao call for getAll Category data ");
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sort);
        Page<Category> categories = categoryRepo.findAll(pageRequest);
        List<Category> categoryList = categories.getContent();


       categoryList.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());



        CategoryResponse categoryResponse= new CategoryResponse();
        categoryResponse.setContain(categoryList);
        categoryResponse.setPageNumber(categories.getNumber());
        categoryResponse.setPageSize(categories.getSize());
        categoryResponse.setTotalElements(categories.getNumberOfElements());
        categoryResponse.setIsLast(categories.isLast());
        log.info("Entering dao call for getAll Category data");
        return categoryResponse;
    }
}
