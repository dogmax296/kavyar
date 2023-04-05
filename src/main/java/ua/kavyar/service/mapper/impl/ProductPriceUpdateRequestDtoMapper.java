package ua.kavyar.service.mapper.impl;

import org.springframework.stereotype.Service;
import ua.kavyar.dto.ProductPriceUpdateRequestDto;
import ua.kavyar.model.ProductPrice;
import ua.kavyar.service.ProductPriceService;
import ua.kavyar.service.mapper.RequestDtoMapper;

@Service
public class ProductPriceUpdateRequestDtoMapper implements
        RequestDtoMapper<ProductPrice, ProductPriceUpdateRequestDto> {
    private final ProductPriceService productPriceService;

    public ProductPriceUpdateRequestDtoMapper(ProductPriceService productPriceService) {
        this.productPriceService = productPriceService;
    }

    @Override
    public ProductPrice mapToModel(ProductPriceUpdateRequestDto productPriceUpdateRequestDto) {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId(productPriceUpdateRequestDto.getProductPriceId());
        productPrice.setProduct(productPriceService.getById(
                productPriceUpdateRequestDto.getProductPriceId()).getProduct());
        productPrice.setPrice(productPriceUpdateRequestDto.getPrice());
        return productPrice;
    }
}
