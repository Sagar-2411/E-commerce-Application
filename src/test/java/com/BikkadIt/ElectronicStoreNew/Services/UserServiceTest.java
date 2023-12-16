package com.BikkadIt.ElectronicStoreNew.Services;

import com.BikkadIt.ElectronicStoreNew.Dto.UserDto;
import com.BikkadIt.ElectronicStoreNew.entity.User;
import com.BikkadIt.ElectronicStoreNew.payload.UserResponse;
import com.BikkadIt.ElectronicStoreNew.repository.UserRepo;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.UserServiceI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepo userRepo;

    @Autowired    // here we use Autowire because of interface if we call classs then use @InjectMock
    private UserServiceI userService;

//    @InjectMocks
//    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    User user;

    @BeforeEach           //it is user for before excuting each method
    public void init() {
        user = User.builder()
                .name("RAUT")
                .email("sagar@gmail")
                .gender("male")
                .password("sagar")
                .imageName("abc.png")
                .about("this is sagar")
                .build();
    }

    @Test
    public void UserCreateTest() {
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        UserDto user1 = userService.createUser(modelMapper.map(user, UserDto.class));
        System.out.println(user1.getName());
        Assertions.assertNotNull(user1);
        Assertions.assertEquals("RAUT", user1.getName());


    }

    @Test
    public void UpdateUserTest() {

        String userId = "gfgh";
        UserDto userDto = UserDto.builder()
                .name("AnnaBhauSathe")
                .about("is form ahergoan")
                .imageName("xyz.gpa")
                .build();

        Mockito.when(userRepo.findById("gfgh")).thenReturn(Optional.of(user));// here we take findById(Mockito.anyString())
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);

        UserDto updatedUser = userService.updateUser(userDto, userId);
        System.out.println(updatedUser.getName());
        System.out.println(updatedUser.getImageName());
        Assertions.assertEquals(userDto.getName(), updatedUser.getName(), "name is not update");
        Assertions.assertNotNull(userDto);


    }

    @Test
    public void deleteUserTest() {
        String userId = "";
        Mockito.when(userRepo.findById(Mockito.anyString())).thenReturn(Optional.of(user));
        userService.deleteUser(userId);
        Mockito.verify(userRepo, Mockito.times(1)).delete(user);//here we chack how many time it will delete method run

    }

    @Test
    public void getAllUser(){

        User user1,user2;
        user1 = User.builder()
                .name("RAUT")
                .email("sagar@gmail")
                .gender("male")
                .password("sagar")
                .imageName("abc.png")
                .about("this is sagar")
                .build();

        user2 = User.builder()
                .name("RAUT")
                .email("sagar@gmail")
                .gender("male")
                .password("sagar")
                .imageName("abc.png")
                .about("this is sagar")
                .build();
        /*
        List<User> list=new ArrayList<>();
        list.add(user);
        list.add(user1);
        list.add(user2);
        */
        List<User> list = Arrays.asList(user,user1,user2);


        Page<User> page=new PageImpl(list);  //here page is interface we can not create object of Interface

        Mockito.when(userRepo.findAll((Pageable) Mockito.any())).thenReturn(page);

        UserResponse allUser = userService.gellAllUser(1, 2, "name", "ase");

        System.out.println(allUser.getTotalElements());
        Assertions.assertEquals(3,allUser.getContain().size());
    }

    @Test
    public void getsingleUser(){

        String userId="asd";
        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(user));
        UserDto userById = userService.getUserById(userId);

        Assertions.assertEquals("RAUT",userById.getName());
        System.out.println(userById.getName());
        Assertions.assertEquals(user.getName(),userById.getName(),"Name not match");
        Assertions.assertNotNull(userById);
    }

    @Test
    public void getUserByName(){

        String userName="";
        Mockito.when(userRepo.findByName(Mockito.any())).thenReturn(user);
        UserDto userDto = userService.searchUser(userName);
        Assertions.assertNotNull(userDto);
        Assertions.assertEquals("RAUT",userDto.getName());


    }


}
