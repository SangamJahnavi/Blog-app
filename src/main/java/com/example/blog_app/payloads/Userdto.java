package com.example.blog_app.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Userdto {

    private int id;

    @NotEmpty
    @Size(min=2,message = "Give a minimum of 2 characters")
    private String name;

    @Email
    private  String email;

    @NotEmpty
    @Size(min=3,max = 11,message = "Give min of 3 and max of 11 chars")
    private String password;

    @NotEmpty
    private String about;


}
