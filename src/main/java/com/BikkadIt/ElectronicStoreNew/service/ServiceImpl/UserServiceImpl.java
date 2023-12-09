package com.BikkadIt.ElectronicStoreNew.service.ServiceImpl;

import com.BikkadIt.ElectronicStoreNew.Dto.UserDto;
import com.BikkadIt.ElectronicStoreNew.constant.AppConstant;
import com.BikkadIt.ElectronicStoreNew.entity.User;
import com.BikkadIt.ElectronicStoreNew.exception.ResourseNotFoundException;
import com.BikkadIt.ElectronicStoreNew.payload.UserResponse;
import com.BikkadIt.ElectronicStoreNew.repository.UserRepo;
import com.BikkadIt.ElectronicStoreNew.service.serviceI.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserServiceI {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Entering dao call for save user data");
        String str = UUID.randomUUID().toString();
        userDto.setId(str);
        User user = this.modelMapper.map(userDto, User.class);

        User save = this.userRepo.save(user);
        log.info("complete dao call for save user data");


        return this.modelMapper.map(save, UserDto.class);


    }

    @Override
    public UserDto getUserById(String userId) {
        log.info("Entering dao call for get user data {}" ,userId);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourseNotFoundException(AppConstant.NOT_FOUND +userId));
        log.info("complete dao call for get user data {}" ,userId);
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto,String userId) {
        log.info("Entering dao call for update user data{}",userId);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourseNotFoundException(AppConstant.NOT_FOUND + userId));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
       // user.setImageName(userDto.getImageName());
        User save = this.userRepo.save(user);
        log.info("complete dao call for update  user data {}" ,userId);
        return this.modelMapper.map(save,UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        log.info("Entering dao call for delete user data {}",userId);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourseNotFoundException(AppConstant.NOT_FOUND + userId));
         this.userRepo.delete(user);
        log.info("complete dao call for delete  user data {}",userId);
    }

    @Override
    public UserResponse gellAllUser(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
        log.info("Entering dao call for getAll  user data");
        Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sort);
        Page<User> users = this.userRepo.findAll(pageRequest);
        List<User> userList = users.getContent();

        userList.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        UserResponse userResponse= new UserResponse();
        userResponse.setContain(userList);
        userResponse.setPageNumber(users.getNumber());
        userResponse.setPageSize(users.getSize());
        userResponse.setTotalElements(users.getNumberOfElements());
        userResponse.setIsLast(users.isLast());

        log.info("complete dao call for getAll  user data");
        return userResponse;
    }


}



