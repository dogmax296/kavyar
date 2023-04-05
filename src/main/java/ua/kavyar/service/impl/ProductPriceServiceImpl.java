package ua.kavyar.service.impl;

import org.springframework.stereotype.Service;
import ua.kavyar.model.ProductPrice;
import ua.kavyar.repository.ProductPriceRepository;
import ua.kavyar.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
    private final ProductPriceRepository productPriceRepository;

    public ProductPriceServiceImpl(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Override
    public ProductPrice create(ProductPrice productPrice) {
        return productPriceRepository.save(productPrice);
    }

    @Override
    public ProductPrice getById(Long id) {
        return productPriceRepository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        productPriceRepository.deleteById(id);
    }

    @Override
    public ProductPrice update(ProductPrice productPrice) {
        return productPriceRepository.save(productPrice);
    }
}
