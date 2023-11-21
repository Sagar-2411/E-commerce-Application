package com.lcwd.electronic.store.exception;

import com.lcwd.electronic.store.helper.ApiResponse;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {


@ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ApiResponse> resourseNotFoundExeptionHandler(ResourseNotFoundException ex){
        String message = ex.getMessage();
        ApiResponse apiResponse= new ApiResponse(message,false);

        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

   @ExceptionHandler (BadApiRequest.class)
    public ResponseEntity<ApiResponse> BadHandlerRequest(BadApiRequest ex){
        String message = ex.getMessage();
        ApiResponse apiResponse= new ApiResponse(message,false);

        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    //for validation not proper putting
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {

        Map<String, Object> resp = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String Message = error.getDefaultMessage();
            resp.put(field, Message);
        });
        return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.BAD_REQUEST);
    }



}
