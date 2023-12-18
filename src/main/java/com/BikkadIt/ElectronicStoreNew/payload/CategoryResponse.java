package com.BikkadIt.ElectronicStoreNew.payload;

import com.BikkadIt.ElectronicStoreNew.Dto.CategoryDto;
import com.BikkadIt.ElectronicStoreNew.entity.Category;
import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private List<CategoryDto> contain;
    private Integer totalElements;
    private Integer pageNumber;
    private Integer pageSize;
    private Boolean isLast;
}
