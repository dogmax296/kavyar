package com.example.demo.dto;

import lombok.Data;

@Data
public class CoffeeShopRequestDto {
    private Long cityId;
    private String title;
    private String description;
    private String phone;
    private String open;
    private String close;
//    private String instagram;
//    private String facebook;
//    private String url;
//    private Long logoId;
//    private Long[] photos;
    private Long[] features;
//    private String location;
}
