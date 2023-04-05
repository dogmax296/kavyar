package ua.kavyar.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.kavyar.dto.CoffeeShopCreateRequestDto;
import ua.kavyar.dto.CoffeeShopResponseDto;
import ua.kavyar.dto.CoffeeShopSimpleResponseDto;
import ua.kavyar.dto.CoffeeShopUpdateRequestDto;
import ua.kavyar.model.CoffeeShop;
import ua.kavyar.service.CoffeeShopService;
import ua.kavyar.service.mapper.DtoMapper;
import ua.kavyar.service.mapper.RequestDtoMapper;
import ua.kavyar.service.mapper.ResponseDtoMapper;

@RestController
@RequestMapping("/coffee-shops")
public class CoffeeShopController {
    private final CoffeeShopService coffeeShopService;
    private final DtoMapper<CoffeeShop,
            CoffeeShopCreateRequestDto, CoffeeShopResponseDto> coffeeShopDtoMapper;
    private final ResponseDtoMapper<
            CoffeeShop, CoffeeShopSimpleResponseDto> simpleResponseDtoMapper;
    private final RequestDtoMapper<CoffeeShop, CoffeeShopUpdateRequestDto> updateDtoMapper;

    public CoffeeShopController(
            CoffeeShopService coffeeShopService,
            DtoMapper<CoffeeShop,
                    CoffeeShopCreateRequestDto, CoffeeShopResponseDto> coffeeShopDtoMapper,
            ResponseDtoMapper<CoffeeShop, CoffeeShopSimpleResponseDto> simpleResponseDtoMapper,
            RequestDtoMapper<CoffeeShop, CoffeeShopUpdateRequestDto> updateDtoMapper) {
        this.coffeeShopService = coffeeShopService;
        this.coffeeShopDtoMapper = coffeeShopDtoMapper;
        this.simpleResponseDtoMapper = simpleResponseDtoMapper;
        this.updateDtoMapper = updateDtoMapper;
    }

    @PostMapping
    public CoffeeShopResponseDto add(
            @RequestBody CoffeeShopCreateRequestDto coffeeShopCreateRequestDto) {
        return coffeeShopDtoMapper.mapToDto(
                coffeeShopService.create(
                        coffeeShopDtoMapper.mapToModel(coffeeShopCreateRequestDto)));
    }

    @GetMapping
    public List<CoffeeShopSimpleResponseDto> getAll(@RequestParam Map<String, String> params) {
        return coffeeShopService.findAll(params).stream()
                .map(simpleResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public CoffeeShopResponseDto getById(@PathVariable Long id) {
        return coffeeShopDtoMapper.mapToDto(coffeeShopService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public CoffeeShopResponseDto delete(@PathVariable Long id) {
        return coffeeShopDtoMapper.mapToDto(coffeeShopService.delete(id));
    }

    @PutMapping("/restore/{id}")
    public CoffeeShopResponseDto restore(@PathVariable Long id) {
        return coffeeShopDtoMapper.mapToDto(coffeeShopService.restore(id));
    }

    @PutMapping("/update")
    public CoffeeShopResponseDto update(
            @RequestBody CoffeeShopUpdateRequestDto coffeeShopUpdateRequestDto) {
        return coffeeShopDtoMapper.mapToDto(coffeeShopService.update(
                updateDtoMapper.mapToModel(coffeeShopUpdateRequestDto)));
    }
}
