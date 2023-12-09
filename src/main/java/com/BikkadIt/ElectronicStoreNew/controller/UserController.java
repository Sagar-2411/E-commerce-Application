package com.BikkadIt.ElectronicStoreNew.controller;


import com.BikkadIt.ElectronicStoreNew.Dto.UserDto;
import com.BikkadIt.ElectronicStoreNew.constant.AppConstant;
import com.BikkadIt.ElectronicStoreNew.helper.ApiResponse;
import com.BikkadIt.ElectronicStoreNew.payload.UserResponse;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.FileService;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private FileService fileService;
@Value("${project.image}")
    private String imageUploadPath;

    /**
     * @param userDto
     * @author Sagar
     * @apiNote to save user data in database
     * @since 1.0v
     * @return userDto
     */
    @PostMapping("/user")
    public ResponseEntity<UserDto> creatUser(@Valid @RequestBody UserDto userDto) {

        log.info("Entering request for Create user data in controller layer");
        UserDto userDto1 = userServiceI.createUser(userDto);
        log.info("complete request for create user data controller layer");
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    /**
     * @param userId
     * @return
     * @apiNote to get singleUser
     * @author sagar
     * @since 1.0v
     */

    @GetMapping("/{userId}")

    public ResponseEntity<UserDto> getSingleUser(@PathVariable String userId) {
        log.info("Entering request for gate user data in controller layer");
        UserDto userById = this.userServiceI.getUserById(userId);
        log.info("complete request for get user data controller layer");
        return new ResponseEntity<UserDto>(userById, HttpStatus.OK);


    }
/**
 *  @param userId
 *  @author sagar
 *  @apiNote to update User
 *  @return
 *  @since 1.0v
 */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable String userId){
        log.info("Entering request for update user data in controller layer");
        UserDto userDto1 = this.userServiceI.updateUser(userDto, userId);
        log.info("complete request for update user data controller layer");
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }

    /**
     * @author Sagar
     * @apiNote to delete user data
     * @param userId
     * @since 1.0v
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUset(@PathVariable String userId) {
        log.info("Entering request for Delete user data in controller layer");

        this.userServiceI.deleteUser(userId);
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage("user delete succefully");
        apiResponse.setSuccess(true);
        apiResponse.setStatus(HttpStatus.OK);

        log.info("complete request for Delete user data controller layer");
       // return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);

        return new ResponseEntity<ApiResponse>(new ApiResponse().builder().message("delete succefully").status(HttpStatus.OK).success(true).build(),HttpStatus.OK);
    }

/**
 * @author sagar
 * @apiNote to getall user
 * @since 1.0v
 * @return
 */
    @GetMapping("/")
    public  ResponseEntity<UserResponse> getAllUsers(
            @RequestParam (value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam (value = "pageSize"  ,defaultValue = AppConstant.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam (value = "sortBy"  ,defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
            @RequestParam (value = "sortDir" ,defaultValue = AppConstant.SORT_DIR,required = false)String sortDir
            ){
        log.info("Entering request for getll all user data in controller layer");

        UserResponse userDtos = this.userServiceI.gellAllUser(pageNumber,pageSize,sortBy,sortDir);
        log.info("complete request for GetAll user data controller layer");
        return new ResponseEntity<UserResponse>(userDtos,HttpStatus.OK);
    }

//@PostMapping("/image/{userId}")
//    public ResponseEntity<ImageResponse> uploadImage(@RequestParam ("userImage") MultipartFile image, @PathVariable String userId) throws IOException {
//    log.info("Entering request for uploading image  in user controller layer");
//
//    String imageName = fileService.uploadFile(image, imageUploadPath);
//
//    UserDto user = userServiceI.getUserById(userId);
//    user.setImageName(imageName);
//
//    UserDto userDto = userServiceI.updateUser(user, userId);
//    ImageResponse imageResponse= ImageResponse.builder().imageName(imageName).message("uploades succefully").success(true).status(HttpStatus.OK).build();
//    log.info("complete request for uploading image  in user controller layer");
//
//    return new ResponseEntity<ImageResponse>(imageResponse,HttpStatus.CREATED);




  //  }
}
