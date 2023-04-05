package com.example.demo.dto;

import lombok.Data;

@Data
public class CoffeeShopResponseDto {
    private Long id;
    private CityResponseDto city;
    private Boolean isDisable;
    private String title;
    private String description;
    private String phone;
    private String open;
    private String close;
//    private String instagram;
//    private String facebook;
//    private String url;
//    private PhotoResponseDto logo;
//    private PhotoResponseDto[] photos;
    private FeatureResponseDto[] features;
//    private String location;
}
