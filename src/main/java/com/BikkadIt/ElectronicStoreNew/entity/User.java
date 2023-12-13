package com.BikkadIt.ElectronicStoreNew.entity;

import lombok.*;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @Column(name="USER_ID")
    private String id;

    @Column(name = "USER_NAME")
     private String name;

    @Column(name = "USER_EMAIL")
      private String email;

    @Column(name = "USER_PASS")
      private String password;

      @Column(name = "USER_ABOUT")
      private String about;

      @Column(name = "USER_GENDER")
      private String gender;


    private String imageName;


}
