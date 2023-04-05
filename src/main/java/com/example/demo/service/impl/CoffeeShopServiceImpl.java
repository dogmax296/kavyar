package com.example.demo.service.impl;

import com.example.demo.model.CoffeeShop;
import com.example.demo.repository.CoffeeShopRepository;
import com.example.demo.repository.specification.SpecificationManager;
import com.example.demo.service.CoffeeShopService;
import com.example.demo.service.handler.PaginationAndSortingHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CoffeeShopServiceImpl implements CoffeeShopService {
    private final CoffeeShopRepository coffeeShopRepository;

    private final SpecificationManager<CoffeeShop> shopSpecificationManager;

    private final PaginationAndSortingHandler paginationAndSortingHandler;

    public CoffeeShopServiceImpl(CoffeeShopRepository coffeeShopRepository,
                                 SpecificationManager<CoffeeShop> shopSpecificationManager,
                                 PaginationAndSortingHandler paginationAndSortingHandler) {
        this.coffeeShopRepository = coffeeShopRepository;
        this.shopSpecificationManager = shopSpecificationManager;
        this.paginationAndSortingHandler = paginationAndSortingHandler;
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
    public CoffeeShop update(CoffeeShop coffeeShop) {
        return coffeeShopRepository.save(coffeeShop);
    }
}
