package ua.kavyar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kavyar.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
