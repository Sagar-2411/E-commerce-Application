package com.BikkadIt.ElectronicStoreNew.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String productId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "ADD_DATE")
    private Date addDate;

    @Column(name = "LIVE")
    private boolean live;

    @Column(name = "STOCK")
    private boolean stock;


}
