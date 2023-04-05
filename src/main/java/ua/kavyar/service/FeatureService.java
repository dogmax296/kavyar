package ua.kavyar.service;

import java.util.List;
import ua.kavyar.model.Feature;

public interface FeatureService {
    Feature create(Feature feature);

    List<Feature> findAll();

    Feature getById(Long id);

    void delete(Long id);

    Feature update(Feature feature);
}
