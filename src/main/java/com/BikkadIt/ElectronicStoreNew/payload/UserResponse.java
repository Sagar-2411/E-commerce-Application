package com.BikkadIt.ElectronicStoreNew.payload;

 import com.BikkadIt.ElectronicStoreNew.Dto.UserDto;
 import com.BikkadIt.ElectronicStoreNew.entity.User;
 import lombok.*;

 import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse<T> {

    private List<UserDto> contain;
    private Integer totalElements;
    private Integer pageNumber;
    private Integer pageSize;
    private Boolean isLast;
}
