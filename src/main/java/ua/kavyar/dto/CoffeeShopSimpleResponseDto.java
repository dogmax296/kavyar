package ua.kavyar.dto;

import java.time.LocalTime;
import lombok.Data;

@Data
public class CoffeeShopSimpleResponseDto {
    private Long id;
    private Boolean isDisable;
    private String title;
    private LocalTime open;
    private LocalTime close;
    private String location;
    private String logo;
}
