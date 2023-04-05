package ua.kavyar.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import ua.kavyar.model.City;
import ua.kavyar.repository.CityRepository;
import ua.kavyar.service.CityService;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City create(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City getById(Long id) {
        return cityRepository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public City update(City city) {
        return cityRepository.save(city);
    }

}
