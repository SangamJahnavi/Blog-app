package com.example.blog_app.payloads;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {


    private Integer id;

    private Integer Category_id;

    @NotEmpty
    @Size(min=3)
    private String Category_name;

    @NotEmpty
    @Size(min=3)
    private  String Category_description;

}
