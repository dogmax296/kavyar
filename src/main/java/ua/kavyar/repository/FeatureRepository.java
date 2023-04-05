package ua.kavyar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kavyar.model.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
}
