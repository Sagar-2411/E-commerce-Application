package com.BikkadIt.ElectronicStoreNew.payload;

 import com.BikkadIt.ElectronicStoreNew.entity.User;
 import lombok.AllArgsConstructor;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private List<User> contain;
    private Integer totalElements;
    private Integer pageNumber;
    private Integer pageSize;
    private Boolean isLast;
}
