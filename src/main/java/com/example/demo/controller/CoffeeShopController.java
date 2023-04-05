package com.example.demo.controller;

import com.example.demo.dto.CoffeeShopRequestDto;
import com.example.demo.dto.CoffeeShopResponseDto;
import com.example.demo.model.CoffeeShop;
import com.example.demo.service.CoffeeShopService;
import com.example.demo.service.DtoMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coffee-shops")
public class CoffeeShopController {
    private final CoffeeShopService coffeeShopService;
    private final DtoMapper<CoffeeShop, CoffeeShopRequestDto, CoffeeShopResponseDto> dtoMapper;

    public CoffeeShopController(
            CoffeeShopService coffeeShopService,
            DtoMapper<CoffeeShop, CoffeeShopRequestDto, CoffeeShopResponseDto> dtoMapper) {
        this.coffeeShopService = coffeeShopService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public CoffeeShopResponseDto add(@RequestBody CoffeeShopRequestDto coffeeShopRequestDto) {
        return dtoMapper.mapToDto(
                coffeeShopService.create(
                        dtoMapper.mapToModel(coffeeShopRequestDto)));
    }

    @GetMapping
    public List<CoffeeShopResponseDto> getAll(@RequestParam Map<String, String> params) {
        return coffeeShopService.findAll(params).stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CoffeeShopResponseDto getById(@PathVariable Long id) {
        return dtoMapper.mapToDto(coffeeShopService.getById(id));
    }

    @DeleteMapping("/{id}")
    public CoffeeShopResponseDto delete(@PathVariable Long id) {
        return dtoMapper.mapToDto(coffeeShopService.delete(id));
    }

    @PutMapping("/{id}")
    public CoffeeShopResponseDto update(@PathVariable Long id,
                                        @RequestBody CoffeeShopRequestDto coffeeShopRequestDto) {
        CoffeeShop coffeeShop = dtoMapper.mapToModel(coffeeShopRequestDto);
        coffeeShop.setId(id);
        return dtoMapper.mapToDto(coffeeShopService.update(coffeeShop));
    }
}
