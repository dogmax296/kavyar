package ua.kavyar.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductPriceRequestDto {
    private Long productId;
    private BigDecimal price;
}
