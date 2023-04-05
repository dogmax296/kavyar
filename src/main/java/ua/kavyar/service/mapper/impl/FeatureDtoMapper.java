package ua.kavyar.service.mapper.impl;

import org.springframework.stereotype.Service;
import ua.kavyar.dto.FeatureRequestDto;
import ua.kavyar.dto.FeatureResponseDto;
import ua.kavyar.model.Feature;
import ua.kavyar.service.mapper.DtoMapper;

@Service
public class FeatureDtoMapper implements
        DtoMapper<Feature, FeatureRequestDto, FeatureResponseDto> {
    @Override
    public FeatureResponseDto mapToDto(Feature feature) {
        FeatureResponseDto featureResponseDto = new FeatureResponseDto();
        featureResponseDto.setId(feature.getId());
        featureResponseDto.setName(feature.getName());
        featureResponseDto.setDescription(feature.getDescription());
        return featureResponseDto;
    }

    @Override
    public Feature mapToModel(FeatureRequestDto featureRequestDto) {
        Feature feature = new Feature();
        feature.setName(featureRequestDto.getName());
        feature.setDescription(featureRequestDto.getDescription());
        return feature;
    }
}
