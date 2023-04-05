package com.example.demo.service.mapper;

import com.example.demo.dto.CityRequestDto;
import com.example.demo.dto.CityResponseDto;
import com.example.demo.dto.CoffeeShopRequestDto;
import com.example.demo.dto.CoffeeShopResponseDto;
import com.example.demo.dto.FeatureRequestDto;
import com.example.demo.dto.FeatureResponseDto;
import com.example.demo.dto.PhotoRequestDto;
import com.example.demo.dto.PhotoResponseDto;
import com.example.demo.model.City;
import com.example.demo.model.CoffeeShop;
import com.example.demo.model.Feature;
import com.example.demo.model.Photo;
import com.example.demo.service.CityService;
import com.example.demo.service.DtoMapper;
import com.example.demo.service.FeatureService;
import com.example.demo.service.PhotoService;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CoffeeShopDtoMapper implements
        DtoMapper<CoffeeShop, CoffeeShopRequestDto, CoffeeShopResponseDto> {
    private final DtoMapper<City, CityRequestDto, CityResponseDto> cityDtoMapper;
    private final DtoMapper<Photo, PhotoRequestDto, PhotoResponseDto> photoDtoMapper;
    private final DtoMapper<Feature, FeatureRequestDto, FeatureResponseDto> featureDtoMapper;
    private final CityService cityService;
    private final PhotoService photoService;
    private final FeatureService featureService;

    public CoffeeShopDtoMapper(
            DtoMapper<City, CityRequestDto, CityResponseDto> cityDtoMapper,
            DtoMapper<Photo, PhotoRequestDto, PhotoResponseDto> photoDtoMapper,
            DtoMapper<Feature, FeatureRequestDto, FeatureResponseDto> featureDtoMapper,
            CityService cityService, PhotoService photoService,
            FeatureService featureService) {
        this.cityDtoMapper = cityDtoMapper;
        this.photoDtoMapper = photoDtoMapper;
        this.featureDtoMapper = featureDtoMapper;
        this.cityService = cityService;
        this.photoService = photoService;
        this.featureService = featureService;
    }

    @Override
    public CoffeeShopResponseDto mapToDto(CoffeeShop coffeeShop) {
        CoffeeShopResponseDto coffeeShopResponseDto = new CoffeeShopResponseDto();
        coffeeShopResponseDto.setId(coffeeShop.getId());
        coffeeShopResponseDto.setCity(cityDtoMapper.mapToDto(coffeeShop.getCity()));
        coffeeShopResponseDto.setIsDisable(coffeeShop.getIsDisable());
        coffeeShopResponseDto.setTitle(coffeeShop.getTitle());
        coffeeShopResponseDto.setDescription(coffeeShop.getDescription());
        coffeeShopResponseDto.setPhone(coffeeShop.getPhone());
        coffeeShopResponseDto.setOpen(coffeeShop.getOpen());
        coffeeShopResponseDto.setClose(coffeeShop.getClose());
//        coffeeShopResponseDto.setInstagram(coffeeShop.getInstagram());
//        coffeeShopResponseDto.setFacebook(coffeeShop.getFacebook());
//        coffeeShopResponseDto.setUrl(coffeeShop.getUrl());
//        coffeeShopResponseDto.setLogo(photoDtoMapper.mapToDto(coffeeShop.getLogo()));
//        coffeeShopResponseDto.setLocation(coffeeShop.getLocation());
//        coffeeShopResponseDto.setPhotos(coffeeShop.getPhotos().stream()
//                .map(photoDtoMapper::mapToDto)
//                .toArray(PhotoResponseDto[]::new));
        coffeeShopResponseDto.setFeatures(coffeeShop.getFeatures().stream()
                .map(featureDtoMapper::mapToDto)
                .toArray(FeatureResponseDto[]::new));
//        coffeeShopResponseDto.setLocation(coffeeShop.getLocation());
        return coffeeShopResponseDto;
    }

    @Override
    public CoffeeShop mapToModel(CoffeeShopRequestDto coffeeShopRequestDto) {
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setCity(cityService.getById(coffeeShopRequestDto.getCityId()));
        coffeeShop.setTitle(coffeeShopRequestDto.getTitle());
        coffeeShop.setDescription(coffeeShopRequestDto.getDescription());
        coffeeShop.setPhone(coffeeShopRequestDto.getPhone());
        coffeeShop.setOpen(coffeeShopRequestDto.getOpen());
        coffeeShop.setClose(coffeeShopRequestDto.getClose());
//        coffeeShop.setInstagram(coffeeShopRequestDto.getInstagram());
//        coffeeShop.setFacebook(coffeeShopRequestDto.getFacebook());
//        coffeeShop.setUrl(coffeeShopRequestDto.getUrl());
//        coffeeShop.setLogo(photoService.getById(coffeeShopRequestDto.getLogoId()));
//        coffeeShop.setPhotos(Arrays.stream(coffeeShopRequestDto.getPhotos())
//                .map(photoService::getById)
//                .collect(Collectors.toList()));
        coffeeShop.setFeatures(Arrays.stream(coffeeShopRequestDto.getFeatures())
                .map(featureService::getById)
                .collect(Collectors.toList()));
//        coffeeShop.setLocation(coffeeShopRequestDto.getLocation());
        return coffeeShop;
    }
}
