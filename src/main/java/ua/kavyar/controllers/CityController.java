package ua.kavyar.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.kavyar.dto.CityRequestDto;
import ua.kavyar.dto.CityResponseDto;
import ua.kavyar.model.City;
import ua.kavyar.service.CityService;
import ua.kavyar.service.mapper.DtoMapper;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    private final DtoMapper<City, CityRequestDto, CityResponseDto> dtoMapper;

    public CityController(CityService cityService,
                          DtoMapper<City, CityRequestDto, CityResponseDto> dtoMapper) {
        this.cityService = cityService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public CityResponseDto add(@RequestBody CityRequestDto cityRequestDto) {
        return dtoMapper.mapToDto(cityService.create(dtoMapper.mapToModel(cityRequestDto)));
    }

    @GetMapping
    public List<CityResponseDto> getAll() {
        return cityService.findAll().stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CityResponseDto getById(@PathVariable Long id) {
        return dtoMapper.mapToDto(cityService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }

    @PutMapping("/{id}")
    public CityResponseDto update(@PathVariable Long id,
                                  @RequestBody CityRequestDto cityRequestDto) {
        City city = dtoMapper.mapToModel(cityRequestDto);
        city.setId(id);
        return dtoMapper.mapToDto(cityService.update(city));
    }
}
