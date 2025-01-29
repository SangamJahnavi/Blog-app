package com.example.blog_app.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    String resourcename;
    String fieldname;
    long fieldvalue;

    public ResourceNotFoundException(String resourcename,String fieldname,long fieldvalue) {
        super(String.format("%s not found wih %s and %l",resourcename,fieldname,fieldvalue));
        this.resourcename = resourcename;
        this.fieldname=fieldname;
        this.fieldvalue=fieldvalue;
    }
}

