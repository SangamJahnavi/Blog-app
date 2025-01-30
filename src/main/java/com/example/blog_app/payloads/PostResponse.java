package com.example.blog_app.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

    private List<Postdto> content;
    private int pageNumber;
    private int pageSize;
    private int totalpages;
    private int totalelements;
    private boolean lastPage;
}
