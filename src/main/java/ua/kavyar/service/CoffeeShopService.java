package ua.kavyar.service;

import java.util.Map;
import org.springframework.data.domain.Page;
import ua.kavyar.model.CoffeeShop;
import ua.kavyar.model.ProductPrice;

public interface CoffeeShopService {
    CoffeeShop create(CoffeeShop coffeeShop);

    Page<CoffeeShop> findAll(Map<String, String> params);

    CoffeeShop getById(Long id);

    CoffeeShop delete(Long id);

    CoffeeShop restore(Long id);

    CoffeeShop update(CoffeeShop coffeeShop);

    CoffeeShop getByProductsContains(ProductPrice productPrice);
}
