package com.example.productts.dtos;

import com.example.productts.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductresponseDto {
    private long id;
    private String title;
    private String desc;
    private double price;
    private  String image;
    private String category;      //mistake here i kept  private Category category;
    private RatingResponseDto rating;

}
