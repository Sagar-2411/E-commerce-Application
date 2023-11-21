package com.lcwd.electronic.store.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourseNotFoundException extends RuntimeException {


    public ResourseNotFoundException(String message) {
        super(message);
    }
}
