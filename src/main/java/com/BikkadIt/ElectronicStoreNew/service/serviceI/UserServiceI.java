package com.BikkadIt.ElectronicStoreNew.service.serviceI;


import com.BikkadIt.ElectronicStoreNew.Dto.UserDto;
import com.BikkadIt.ElectronicStoreNew.payload.UserResponse;

import java.util.List;

public interface UserServiceI {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(String userId);

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId);

    UserDto searchUser(String Keyword);

    UserResponse gellAllUser(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);
}
