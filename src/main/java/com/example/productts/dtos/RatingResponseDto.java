package com.example.productts.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingResponseDto {
    private double rate;
    private int count;
}
