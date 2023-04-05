package ua.kavyar.service.mapper.impl;

import org.springframework.stereotype.Service;
import ua.kavyar.dto.CategoryRequestDto;
import ua.kavyar.dto.CategoryResponseDto;
import ua.kavyar.model.Category;
import ua.kavyar.service.mapper.DtoMapper;

@Service
public class CategoryDtoMapper implements
        DtoMapper<Category, CategoryRequestDto, CategoryResponseDto> {
    @Override
    public CategoryResponseDto mapToDto(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }

    @Override
    public Category mapToModel(CategoryRequestDto categoryRequestDto) {
        Category category = new Category();
        category.setName(categoryRequestDto.getName());
        return category;
    }
}
