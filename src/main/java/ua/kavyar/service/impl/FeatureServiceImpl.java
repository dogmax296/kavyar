package ua.kavyar.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import ua.kavyar.model.Feature;
import ua.kavyar.repository.FeatureRepository;
import ua.kavyar.service.FeatureService;

@Service
public class FeatureServiceImpl implements FeatureService {
    private final FeatureRepository featureRepository;

    public FeatureServiceImpl(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    @Override
    public Feature create(Feature feature) {
        return featureRepository.save(feature);
    }

    @Override
    public List<Feature> findAll() {
        return featureRepository.findAll();
    }

    @Override
    public Feature getById(Long id) {
        return featureRepository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        featureRepository.deleteById(id);
    }

    @Override
    public Feature update(Feature feature) {
        return featureRepository.save(feature);
    }
}
