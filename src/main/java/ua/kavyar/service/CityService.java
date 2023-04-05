package ua.kavyar.service;

import java.util.List;
import ua.kavyar.model.City;

public interface CityService {
    City create(City city);

    List<City> findAll();

    City getById(Long id);

    void delete(Long id);

    City update(City city);
}
