package com.lcwd.electronic.store.serviceI;

import com.lcwd.electronic.store.Dto.UserDto;
import com.lcwd.electronic.store.payload.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserServiceI {

    UserDto  createUser(UserDto userDto);

    UserDto getUserById(String userId);

    UserDto updateUser(UserDto userDto,String userId);

    void deleteUser(String userId);

    UserResponse gellAllUser(Integer pageSize, Integer pageNumber, String sortBy, String sortDir);
}
