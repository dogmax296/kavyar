package ua.kavyar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.kavyar.model.ProductPrice;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {
}
