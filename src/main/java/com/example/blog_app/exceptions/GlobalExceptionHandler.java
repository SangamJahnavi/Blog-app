package com.example.blog_app.exceptions;

import com.example.blog_app.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionResponseEntity(ResourceNotFoundException ex){
        String msg=ex.getMessage();
        ApiResponse apiResponse=new ApiResponse(msg,false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> invaliddataResponse(MethodArgumentNotValidException ex){
        Map<String,String> res = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldname=((FieldError)error).getField();
            String fieldmsg=error.getDefaultMessage();
            res.put(fieldname,fieldmsg);
                }
                );
        return  new ResponseEntity<Map<String,String>>(res,HttpStatus.BAD_REQUEST);
    }

}
