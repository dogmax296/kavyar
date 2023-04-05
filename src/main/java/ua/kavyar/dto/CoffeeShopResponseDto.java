package ua.kavyar.dto;

import java.time.LocalTime;
import java.util.List;
import lombok.Data;

@Data
public class CoffeeShopResponseDto {
    private Long id;
    private CityResponseDto city;
    private Boolean isDisable;
    private String title;
    private String description;
    private String phone;
    private LocalTime open;
    private LocalTime close;
    private String url;
    private String location;
    private String logo;
    private List<PhotoResponseDto> photos;
    private List<FeatureResponseDto> features;
    private List<ProductPriceResponseDto> productPrices;
}
