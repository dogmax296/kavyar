package ua.kavyar.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ua.kavyar.model.CoffeeShop;
import ua.kavyar.model.ProductPrice;
import ua.kavyar.repository.CoffeeShopRepository;
import ua.kavyar.repository.specification.SpecificationManager;
import ua.kavyar.service.CoffeeShopService;
import ua.kavyar.service.ProductPriceService;
import ua.kavyar.service.handler.PaginationAndSortingHandler;

@Service
public class CoffeeShopServiceImpl implements CoffeeShopService {
    private final CoffeeShopRepository coffeeShopRepository;

    private final SpecificationManager<CoffeeShop> shopSpecificationManager;

    private final PaginationAndSortingHandler paginationAndSortingHandler;
    private final ProductPriceService productPriceService;

    public CoffeeShopServiceImpl(CoffeeShopRepository coffeeShopRepository,
                                 SpecificationManager<CoffeeShop> shopSpecificationManager,
                                 PaginationAndSortingHandler paginationAndSortingHandler,
                                 ProductPriceService productPriceService) {
        this.coffeeShopRepository = coffeeShopRepository;
        this.shopSpecificationManager = shopSpecificationManager;
        this.paginationAndSortingHandler = paginationAndSortingHandler;
        this.productPriceService = productPriceService;
    }

    @Override
    public CoffeeShop create(CoffeeShop coffeeShop) {
        return coffeeShopRepository.save(coffeeShop);
    }

    @Override
    public Page<CoffeeShop> findAll(Map<String, String> params) {
        List<String> specificationIgnoreParams = new ArrayList<>();
        Collections.addAll(specificationIgnoreParams, paginationAndSortingHandler.getFields());
        Specification<CoffeeShop> specification = null;
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (!specificationIgnoreParams.contains(param.getKey())) {
                Specification<CoffeeShop> sp = shopSpecificationManager.get(param.getKey(),
                        param.getValue().split(","));
                specification = specification == null ?
                        Specification.where(sp) : specification.and(sp);
            }
        }
        return coffeeShopRepository.findAll(specification,
                paginationAndSortingHandler.handle(params));
    }

    @Override
    public CoffeeShop getById(Long id) {
        return coffeeShopRepository.getReferenceById(id);
    }

    @Override
    public CoffeeShop delete(Long id) {
        CoffeeShop coffeeShop = coffeeShopRepository.getReferenceById(id);
        coffeeShop.setIsDisable(true);
        return coffeeShopRepository.save(coffeeShop);
    }

    @Override
    public CoffeeShop restore(Long id) {
        CoffeeShop coffeeShop = coffeeShopRepository.getReferenceById(id);
        coffeeShop.setIsDisable(false);
        return coffeeShopRepository.save(coffeeShop);
    }

    @Override
    public CoffeeShop update(CoffeeShop coffeeShop) {
        List<ProductPrice> productsFromDb = new ArrayList<>(
                coffeeShopRepository.getReferenceById(coffeeShop.getId()).getProducts());
        productsFromDb.removeAll(coffeeShop.getProducts());
        CoffeeShop updatedCoffeeShop = coffeeShopRepository.save(coffeeShop);
        productsFromDb.forEach(p -> productPriceService.delete(p.getId()));
        return updatedCoffeeShop;
    }

    @Override
    public CoffeeShop getByProductsContains(ProductPrice productPrice) {
        return coffeeShopRepository.getByProductsContains(productPrice);
    }
}
