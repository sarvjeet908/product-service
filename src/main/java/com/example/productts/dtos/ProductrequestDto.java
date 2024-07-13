package com.example.productts.dtos;

import com.example.productts.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductrequestDto {

    private String title;
    private String desc;
    private int price;
    private  String image;
    private String category;
}
