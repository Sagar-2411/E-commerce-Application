package com.BikkadIt.ElectronicStoreNew.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {

    //default error massege
    String messege() default "Invalid Image Name!!";


    //group od constraint
    Class<?>[] groups() default { };

    Class<? extends Payload>[]  payload() default {};
}
