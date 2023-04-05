package ua.kavyar.service.mapper.impl;

import org.springframework.stereotype.Service;
import ua.kavyar.dto.ProductPriceRequestDto;
import ua.kavyar.dto.ProductPriceResponseDto;
import ua.kavyar.dto.ProductRequestDto;
import ua.kavyar.dto.ProductResponseDto;
import ua.kavyar.model.Product;
import ua.kavyar.model.ProductPrice;
import ua.kavyar.service.ProductService;
import ua.kavyar.service.mapper.DtoMapper;

@Service
public class ProductPriceDtoMapper implements
        DtoMapper<ProductPrice, ProductPriceRequestDto, ProductPriceResponseDto> {
    private final ProductService productService;
    private final DtoMapper<Product, ProductRequestDto, ProductResponseDto> productDtoMapper;

    public ProductPriceDtoMapper(
            ProductService productService,
            DtoMapper<Product, ProductRequestDto, ProductResponseDto> productDtoMapper) {
        this.productDtoMapper = productDtoMapper;
        this.productService = productService;
    }

    @Override
    public ProductPriceResponseDto mapToDto(ProductPrice productPrice) {
        ProductPriceResponseDto productPriceResponseDto = new ProductPriceResponseDto();
        productPriceResponseDto.setId(productPrice.getId());
        productPriceResponseDto.setProduct(productDtoMapper.mapToDto(productPrice.getProduct()));
        productPriceResponseDto.setPrice(productPrice.getPrice());
        return productPriceResponseDto;
    }

    @Override
    public ProductPrice mapToModel(ProductPriceRequestDto productPriceRequestDto) {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setProduct(productService.getById(productPriceRequestDto.getProductId()));
        productPrice.setPrice(productPriceRequestDto.getPrice());
        return productPrice;
    }
}
