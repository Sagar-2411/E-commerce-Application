package com.BikkadIt.ElectronicStoreNew.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.naming.Name;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    private String productId;

    @Column(name = "PRODUCT_TITLE")
    private String title;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @Column(name = "PRODUCT_QUANTITY")
    private int quantity;

    @Column(name = "PRODUCT_ADD_DATE")
   // @Temporal(TemporalType.DATE)
    private Date addDate;

    @Column(name = "PRODUCT_LIVE")
    private boolean live;

    @Column(name = "PRODUCT_STOCK")
    private boolean stock;


}
