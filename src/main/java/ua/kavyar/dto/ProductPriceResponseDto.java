package ua.kavyar.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductPriceResponseDto {
    private Long id;
    private ProductResponseDto product;
    private BigDecimal price;
}
