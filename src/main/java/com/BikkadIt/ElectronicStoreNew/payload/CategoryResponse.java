package com.BikkadIt.ElectronicStoreNew.payload;

import com.BikkadIt.ElectronicStoreNew.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private List<Category> contain;
    private Integer totalElements;
    private Integer pageNumber;
    private Integer pageSize;
    private Boolean isLast;
}
