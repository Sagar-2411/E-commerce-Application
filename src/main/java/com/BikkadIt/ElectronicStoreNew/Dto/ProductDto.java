package com.BikkadIt.ElectronicStoreNew.Dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private String productId;

    private String title;

    private String description;

    private int quantity;

    private Date addDate;

    private boolean live;

    private boolean stock;

}
