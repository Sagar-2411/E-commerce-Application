package com.BikkadIt.ElectronicStoreNew.payload;

import com.BikkadIt.ElectronicStoreNew.Dto.ProductDto;
import com.BikkadIt.ElectronicStoreNew.entity.Product;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse<T> {

    private List<ProductDto> contain;
    private Long totalElements;
    private Integer pageNumber;
    private Integer pageSize;
    private Boolean isLast;

}
