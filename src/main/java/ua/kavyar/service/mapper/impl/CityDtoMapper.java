package ua.kavyar.service.mapper.impl;

import org.springframework.stereotype.Service;
import ua.kavyar.dto.CityRequestDto;
import ua.kavyar.dto.CityResponseDto;
import ua.kavyar.model.City;
import ua.kavyar.service.mapper.DtoMapper;

@Service
public class CityDtoMapper implements DtoMapper<City, CityRequestDto, CityResponseDto> {
    @Override
    public CityResponseDto mapToDto(City city) {
        CityResponseDto cityResponseDto = new CityResponseDto();
        cityResponseDto.setId(city.getId());
        cityResponseDto.setName(city.getName());
        return cityResponseDto;
    }

    @Override
    public City mapToModel(CityRequestDto cityRequestDto) {
        City city = new City();
        city.setName(cityRequestDto.getName());
        return city;
    }
}
