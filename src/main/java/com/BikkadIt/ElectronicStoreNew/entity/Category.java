package com.BikkadIt.ElectronicStoreNew.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CATEGORY")
public class Category {

    @Id
    private String categoryId;

    @Column(name="CATEGORY_TITLE")
    private String title;
    @Column(name = "DESCRIPTION")
    private String description;
    private String coverImage;

}
