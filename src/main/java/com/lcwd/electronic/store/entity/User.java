package com.lcwd.electronic.store.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "USER")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
    @Column (name = "USER_NAME")
    private String name;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name="USER_PASS")
    private String password;

    @Column(name = "USER_GENDER")
    private String gender;

    @Column(name = "USER_ABOUT")
    private String about;

    @Column(name = "IMAGE_NAME")
    private String imageName;


}
