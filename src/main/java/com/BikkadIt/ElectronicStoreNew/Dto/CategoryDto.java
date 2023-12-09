package com.BikkadIt.ElectronicStoreNew.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CATEGOTY_DTO")
public class CategoryDto {


    private String categoryId;


   @Size(min=2 ,message = "please enter the title")
    private String title;

    @Size(min = 5,max = 100)

    private String description;


    private String coverImage;

}
