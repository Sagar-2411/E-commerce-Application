package com.BikkadIt.ElectronicStoreNew.Dto;

import com.BikkadIt.ElectronicStoreNew.validate.ImageNameValid;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String id;

    @NotEmpty
    @Size(min = 2, message = "User name must be min 2 charector")
    private String name;

   // @Pattern(message = "Email is not valid", regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])}")
    //@NotNull(message = "Email Id not null")
    private String email;


    @Size(min = 3, max = 20, message = "password must be min of 3 and max of 20 char")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "password first latter must be capital , add some special symbol,add some number")
    private String password;

    @NotNull
    @Size(min = 10, message = "about must be greater than 10 charector")
    private String about;

    @NotNull(message = "gender not null ")
    private String gender;

    //@ImageNameValid( message = "Image Name Must Not  Be Blank")
    private String imageName;


}
