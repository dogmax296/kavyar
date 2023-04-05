package ua.kavyar.service.mapper.impl;

import org.springframework.stereotype.Service;
import ua.kavyar.dto.CategoryRequestDto;
import ua.kavyar.dto.CategoryResponseDto;
import ua.kavyar.dto.ProductRequestDto;
import ua.kavyar.dto.ProductResponseDto;
import ua.kavyar.model.Category;
import ua.kavyar.model.Product;
import ua.kavyar.service.CategoryService;
import ua.kavyar.service.mapper.DtoMapper;

@Service
public class ProductDtoMapper implements
        DtoMapper<Product, ProductRequestDto, ProductResponseDto> {
    private final DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> categoryDtoMapper;
    private final CategoryService categoryService;

    public ProductDtoMapper(
            DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> categoryDtoMapper,
            CategoryService categoryService) {
        this.categoryDtoMapper = categoryDtoMapper;
        this.categoryService = categoryService;
    }

    @Override
    public ProductResponseDto mapToDto(Product product) {
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getName());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setCategory(categoryDtoMapper.mapToDto(product.getCategory()));
        return productResponseDto;
    }

    @Override
    public Product mapToModel(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        return product;
    }
}
