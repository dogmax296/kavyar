package com.example.demo.repository.specification.coffeeShop;

import com.example.demo.model.CoffeeShop;
import com.example.demo.repository.specification.SpecificationManager;
import com.example.demo.repository.specification.SpecificationProvider;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CoffeeShopSpecificationManager implements SpecificationManager<CoffeeShop> {

    private Map<String, SpecificationProvider<CoffeeShop>> providerMap;

    public CoffeeShopSpecificationManager(List<SpecificationProvider<CoffeeShop>> productSpecifications) {
        this.providerMap = productSpecifications.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<CoffeeShop> get(String filterKey, String[] params) {
        if (!providerMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is not supported for data filtering");
        }
        return providerMap.get(filterKey).getSpecification(params);
    }
}
