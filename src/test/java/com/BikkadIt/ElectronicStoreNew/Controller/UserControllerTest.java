package com.BikkadIt.ElectronicStoreNew.Controller;

import com.BikkadIt.ElectronicStoreNew.Dto.UserDto;
import com.BikkadIt.ElectronicStoreNew.entity.User;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.UserServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    private UserServiceI userServiceI;

    private User user;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MockMvc mockMvc;  //this mockMvc require to perform url methods

    @BeforeEach           //it is user for before excuting each method
    public void init() {
        user = User.builder()
                .name("Raut")
                .email("Srdff33@gami.com")
                .gender("male")
                .password("Sfd@77564")
                .imageName("jpag")
                .about("this is sagar")
                .build();
    }
    @Test
    public void createUserTest() throws Exception {

        UserDto userDto = modelMapper.map(user, UserDto.class);

        Mockito.when(userServiceI.createUser(Mockito.any())).thenReturn(userDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convetObjectToJsonString(user))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").exists());  //we get json data in that data we check name is containt
    }
//this method we created explicitally this method conver user date to json string
    private String convetObjectToJsonString(Object user) {
        try{
            return new ObjectMapper().writeValueAsString(user);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Test
    public void updateUserTest(){

        String userId="";
        UserDto userDto = UserDto.builder()
                .name("Raut")
                .email("Srdff33@gami.com")
                .gender("male")
                .password("Sfd@77564")
                .imageName("jpag")
                .about("this is sagar")
                .build();
        Mockito.when(userServiceI.updateUser(userDto,Mockito.any())).thenReturn(userDto);



    }
}
