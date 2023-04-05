package ua.kavyar.service.mapper.impl;

import org.springframework.stereotype.Service;
import ua.kavyar.dto.CoffeeShopSimpleResponseDto;
import ua.kavyar.model.CoffeeShop;
import ua.kavyar.service.mapper.ResponseDtoMapper;

@Service
public class CoffeeShopSimpleResponseDtoMapper implements
        ResponseDtoMapper<CoffeeShop, CoffeeShopSimpleResponseDto> {
    @Override
    public CoffeeShopSimpleResponseDto mapToDto(CoffeeShop coffeeShop) {
        CoffeeShopSimpleResponseDto coffeeShopSimpleResponseDto = new CoffeeShopSimpleResponseDto();
        coffeeShopSimpleResponseDto.setId(coffeeShop.getId());
        coffeeShopSimpleResponseDto.setIsDisable(coffeeShop.getIsDisable());
        coffeeShopSimpleResponseDto.setTitle(coffeeShop.getTitle());
        coffeeShopSimpleResponseDto.setOpen(coffeeShop.getOpen());
        coffeeShopSimpleResponseDto.setClose(coffeeShop.getClose());
        coffeeShopSimpleResponseDto.setLocation(coffeeShop.getLocation());
        coffeeShopSimpleResponseDto.setLogo(coffeeShop.getLogo().getUrl());
        return coffeeShopSimpleResponseDto;
    }
}
