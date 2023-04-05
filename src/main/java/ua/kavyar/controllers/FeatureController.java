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
import ua.kavyar.dto.FeatureRequestDto;
import ua.kavyar.dto.FeatureResponseDto;
import ua.kavyar.model.Feature;
import ua.kavyar.service.FeatureService;
import ua.kavyar.service.mapper.DtoMapper;

@RestController
@RequestMapping("/features")
public class FeatureController {
    private final FeatureService featureService;
    private final DtoMapper<Feature, FeatureRequestDto, FeatureResponseDto> dtoMapper;

    public FeatureController(FeatureService featureService,
                             DtoMapper<Feature, FeatureRequestDto, FeatureResponseDto> dtoMapper) {
        this.featureService = featureService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping
    public FeatureResponseDto add(@RequestBody FeatureRequestDto featureRequestDto) {
        return dtoMapper.mapToDto(featureService.create(dtoMapper.mapToModel(featureRequestDto)));
    }

    @GetMapping
    public List<FeatureResponseDto> getAll() {
        return featureService.findAll().stream()
                .map(dtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FeatureResponseDto getById(@PathVariable Long id) {
        return dtoMapper.mapToDto(featureService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        featureService.delete(id);
    }

    @PutMapping("/{id}")
    public FeatureResponseDto update(@PathVariable Long id,
                                     @RequestBody FeatureRequestDto featureRequestDto) {
        Feature feature = dtoMapper.mapToModel(featureRequestDto);
        feature.setId(id);
        return dtoMapper.mapToDto(featureService.update(feature));
    }
}
