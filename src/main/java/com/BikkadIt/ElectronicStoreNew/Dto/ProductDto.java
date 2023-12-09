package com.BikkadIt.ElectronicStoreNew.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String productId;

    private String title;

    private String description;

    private int quantity;

    private Date addDate;

    private boolean live;

    private boolean stock;

}
