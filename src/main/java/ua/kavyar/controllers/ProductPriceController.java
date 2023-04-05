package ua.kavyar.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kavyar.dto.ProductPriceRequestDto;
import ua.kavyar.dto.ProductPriceResponseDto;
import ua.kavyar.model.CoffeeShop;
import ua.kavyar.model.ProductPrice;
import ua.kavyar.service.CoffeeShopService;
import ua.kavyar.service.ProductPriceService;
import ua.kavyar.service.mapper.DtoMapper;

@RestController
@RequestMapping("/product-prices")
public class ProductPriceController {
    private final ProductPriceService productPriceService;
    private final CoffeeShopService coffeeShopService;
    private final DtoMapper<ProductPrice, ProductPriceRequestDto,
            ProductPriceResponseDto> dtoMapper;

    public ProductPriceController(
            ProductPriceService productPriceService,
            CoffeeShopService coffeeShopService,
            DtoMapper<ProductPrice, ProductPriceRequestDto, ProductPriceResponseDto> dtoMapper) {
        this.productPriceService = productPriceService;
        this.coffeeShopService = coffeeShopService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public ProductPriceResponseDto add(@RequestBody ProductPriceRequestDto productPriceRequestDto) {
        return dtoMapper.mapToDto(
                productPriceService.create(dtoMapper.mapToModel(productPriceRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductPriceResponseDto getById(@PathVariable Long id) {
        return dtoMapper.mapToDto(productPriceService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ProductPrice productPrice = productPriceService.getById(id);
        CoffeeShop coffeeShop = coffeeShopService.getByProductsContains(productPrice);
        coffeeShop.getProducts().remove(productPrice);
        coffeeShopService.update(coffeeShop);
        productPriceService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductPriceResponseDto update(
            @PathVariable Long id,
            @RequestBody ProductPriceRequestDto productPriceRequestDto) {
        ProductPrice productPrice = dtoMapper.mapToModel(productPriceRequestDto);
        productPrice.setId(id);
        return dtoMapper.mapToDto(productPriceService.update(productPrice));
    }
}
