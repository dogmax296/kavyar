package com.example.demo.service;

import com.example.demo.model.CoffeeShop;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoffeeShopService {
    CoffeeShop create(CoffeeShop coffeeShop);

    Page<CoffeeShop> findAll(Map<String, String> params);

    CoffeeShop getById(Long id);

    CoffeeShop delete(Long id);

    CoffeeShop update(CoffeeShop coffeeShop);

}
