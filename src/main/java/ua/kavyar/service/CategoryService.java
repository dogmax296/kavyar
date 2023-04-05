package ua.kavyar.service;

import java.util.List;
import ua.kavyar.model.Category;

public interface CategoryService {
    Category create(Category city);

    List<Category> findAll();

    Category getById(Long id);

    void delete(Long id);

    Category update(Category category);
}
