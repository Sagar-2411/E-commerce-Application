package com.BikkadIt.ElectronicStoreNew.helper;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String message;

    private Boolean success;

    private HttpStatus status;

}