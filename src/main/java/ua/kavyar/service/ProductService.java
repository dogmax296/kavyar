package ua.kavyar.service;

import java.util.List;
import ua.kavyar.model.Product;

public interface ProductService {
    Product create(Product product);

    List<Product> findAll();

    Product getById(Long id);

    void delete(Long id);

    Product update(Product product);
}
