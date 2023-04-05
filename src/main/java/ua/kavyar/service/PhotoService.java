package ua.kavyar.service;

import java.util.List;
import ua.kavyar.model.Photo;

public interface PhotoService {
    Photo create(Photo photo);

    List<Photo> findAll();

    Photo getById(Long id);

    void delete(Long id);

    Photo update(Photo photo);
}
