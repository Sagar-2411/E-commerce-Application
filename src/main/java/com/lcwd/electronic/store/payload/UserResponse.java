package com.lcwd.electronic.store.payload;

import com.lcwd.electronic.store.entity.User;
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
