package ua.kavyar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kavyar.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
