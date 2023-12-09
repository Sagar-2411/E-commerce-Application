package com.BikkadIt.ElectronicStoreNew.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourseNotFoundException extends RuntimeException {


    public ResourseNotFoundException(String message) {
        super(message);
    }
}
