package com.BikkadIt.ElectronicStoreNew.Services;

import com.BikkadIt.ElectronicStoreNew.entity.User;
import com.BikkadIt.ElectronicStoreNew.repository.UserRepo;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.UserServiceI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceI userServiceI;

    User user;
    @BeforeEach
    public void init(){
        user=User.builder()
                . name("sagar")
                .email("sagar@gmail")
                .gender("male")
                .password("sagar")
                .imageName("abc.png")
                .about("this is sagar")
                .build();
    }


    public void UserCreateTest(){
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);

    }



}
