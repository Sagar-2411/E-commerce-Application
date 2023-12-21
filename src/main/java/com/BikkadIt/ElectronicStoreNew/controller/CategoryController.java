package com.BikkadIt.ElectronicStoreNew.controller;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.constant.AppConstant;
import com.BikkadIt.ElectronicStoreNew.helper.ApiResponse;
import com.BikkadIt.ElectronicStoreNew.payload.CategoryResponse;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.CategoryServiceI;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryServiceI categoryServiceI;
    /**
     * @param categoryDto
     * @author Sagar
     * @apiNote to save category data in database
     * @since 1.0v
     * @return userDto
     */

    @PostMapping("/category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        log.info("Entering request for CreateCategory  data in controller layer");
        CategoryDto categoryDto1 = categoryServiceI.create(categoryDto);
        log.info("complete request for create Category data controller layer");
        return new ResponseEntity<>(categoryDto1,HttpStatus.CREATED);
    }

    /**
     *  @param categoryId
     *  @author sagar
     *  @apiNote to update User
     *  @return
     *  @since 1.0v
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String categoryId){

        log.info("Entering request for CreateCategory  data in controller layer{}" ,categoryId);
        CategoryDto upadate = categoryServiceI.upadate(categoryDto, categoryId);
        log.info("complete request for Update category  data controller layer {}" ,categoryId);
        return new ResponseEntity<>(upadate,HttpStatus.OK);
    }

    /**
     * @param categoryId
     * @return
     * @apiNote to get singlecategory
     * @author sagar
     * @since 1.0v
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable String categoryId){
        log.info("Entering request for get category  data in controller layer{}" ,categoryId);
        CategoryDto singleCategory = categoryServiceI.getSingleCategory(categoryId);
        log.info("complete request for get category  data controller layer {}" ,categoryId);
        return new ResponseEntity<>(singleCategory,HttpStatus.OK);
    }

    /**
     * @author sagar
     * @apiNote to getall category
     * @since 1.0v
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<CategoryResponse> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize" , defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy" , defaultValue =  AppConstant.SORT_BY_CATEGORY,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir
    )
    {
        log.info("Entering request for get All category  data in controller layer" );
        CategoryResponse allCategory = this.categoryServiceI.getAllCategory(pageNumber, pageSize, sortBy, sortDir);
        log.info("complete request for get All category  data controller layer");
        return new ResponseEntity<CategoryResponse>(allCategory, HttpStatus.OK);
    }


/**
 * @author Sagar
 * @apiNote to delete category data
 * @param categoryId
 * @since 1.0v
 */

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String categoryId){
        log.info("Entering request for delete category  data in controller layer{}" ,categoryId);
        this.categoryServiceI.deleteCategory(categoryId);
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage("user delete succefully");
        apiResponse.setSuccess(true);
        apiResponse.setStatus(HttpStatus.OK);
        log.info("Entering request for delete category  data in controller layer{}" ,categoryId);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

    }



}
