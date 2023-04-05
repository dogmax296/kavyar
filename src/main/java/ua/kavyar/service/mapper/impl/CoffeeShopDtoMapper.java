package ua.kavyar.service.mapper.impl;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ua.kavyar.dto.CityRequestDto;
import ua.kavyar.dto.CityResponseDto;
import ua.kavyar.dto.CoffeeShopCreateRequestDto;
import ua.kavyar.dto.CoffeeShopResponseDto;
import ua.kavyar.dto.FeatureRequestDto;
import ua.kavyar.dto.FeatureResponseDto;
import ua.kavyar.dto.PhotoRequestDto;
import ua.kavyar.dto.PhotoResponseDto;
import ua.kavyar.dto.ProductPriceRequestDto;
import ua.kavyar.dto.ProductPriceResponseDto;
import ua.kavyar.model.City;
import ua.kavyar.model.CoffeeShop;
import ua.kavyar.model.Feature;
import ua.kavyar.model.Photo;
import ua.kavyar.model.ProductPrice;
import ua.kavyar.service.CityService;
import ua.kavyar.service.FeatureService;
import ua.kavyar.service.PhotoService;
import ua.kavyar.service.mapper.DtoMapper;

@Service
public class CoffeeShopDtoMapper implements
        DtoMapper<CoffeeShop, CoffeeShopCreateRequestDto, CoffeeShopResponseDto> {
    private static final boolean DEFAULT_IS_DISABLE_COFFEE_SHOP = false;
    private final DtoMapper<City, CityRequestDto, CityResponseDto> cityDtoMapper;
    private final DtoMapper<Photo, PhotoRequestDto, PhotoResponseDto> photoDtoMapper;
    private final DtoMapper<Feature, FeatureRequestDto, FeatureResponseDto> featureDtoMapper;
    private final DtoMapper<ProductPrice, ProductPriceRequestDto,
            ProductPriceResponseDto> productPriceDtoMapper;
    private final CityService cityService;
    private final PhotoService photoService;
    private final FeatureService featureService;

    public CoffeeShopDtoMapper(
            DtoMapper<City, CityRequestDto, CityResponseDto> cityDtoMapper,
            DtoMapper<Photo, PhotoRequestDto, PhotoResponseDto> photoDtoMapper,
            DtoMapper<Feature, FeatureRequestDto, FeatureResponseDto> featureDtoMapper,
            DtoMapper<ProductPrice, ProductPriceRequestDto,
                    ProductPriceResponseDto> productPriceDtoMapper,
            CityService cityService, PhotoService photoService,
            FeatureService featureService) {
        this.cityDtoMapper = cityDtoMapper;
        this.photoDtoMapper = photoDtoMapper;
        this.featureDtoMapper = featureDtoMapper;
        this.productPriceDtoMapper = productPriceDtoMapper;
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
        coffeeShopResponseDto.setUrl(coffeeShop.getUrl());
        coffeeShopResponseDto.setLocation(coffeeShop.getLocation());
        coffeeShopResponseDto.setLogo(coffeeShop.getLogo().getUrl());
        coffeeShopResponseDto.setPhotos(coffeeShop.getPhotos().stream()
                .map(photoDtoMapper::mapToDto)
                .collect(Collectors.toList()));
        coffeeShopResponseDto.setFeatures(coffeeShop.getFeatures().stream()
                .map(featureDtoMapper::mapToDto)
                .collect(Collectors.toList()));
        coffeeShopResponseDto.setProductPrices(coffeeShop.getProducts().stream()
                .map(productPriceDtoMapper::mapToDto)
                .sorted((p1, p2) ->
                        Math.toIntExact(p1.getProduct().getCategory().getId()
                                - p2.getProduct().getCategory().getId()))
                .collect(Collectors.toList()));
        return coffeeShopResponseDto;
    }

    @Override
    public CoffeeShop mapToModel(CoffeeShopCreateRequestDto coffeeShopCreateRequestDto) {
        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setCity(cityService.getById(coffeeShopCreateRequestDto.getCityId()));
        coffeeShop.setIsDisable(DEFAULT_IS_DISABLE_COFFEE_SHOP);
        coffeeShop.setTitle(coffeeShopCreateRequestDto.getTitle());
        coffeeShop.setDescription(coffeeShopCreateRequestDto.getDescription());
        coffeeShop.setPhone(coffeeShopCreateRequestDto.getPhone());
        coffeeShop.setOpen(coffeeShopCreateRequestDto.getOpen());
        coffeeShop.setClose(coffeeShopCreateRequestDto.getClose());
        coffeeShop.setUrl(coffeeShopCreateRequestDto.getUrl());
        coffeeShop.setLocation(coffeeShopCreateRequestDto.getLocation());
        coffeeShop.setLogo(photoService.getById(coffeeShopCreateRequestDto.getLogoId()));
        coffeeShop.setPhotos(coffeeShopCreateRequestDto.getPhotos().stream()
                .map(photoService::getById).collect(Collectors.toList()));
        coffeeShop.setFeatures(coffeeShopCreateRequestDto.getFeatures().stream()
                .map(featureService::getById).collect(Collectors.toList()));
        coffeeShop.setProducts(coffeeShopCreateRequestDto.getProductPrices().stream()
                .map(productPriceDtoMapper::mapToModel)
                .collect(Collectors.toList()));
        return coffeeShop;
    }
}
