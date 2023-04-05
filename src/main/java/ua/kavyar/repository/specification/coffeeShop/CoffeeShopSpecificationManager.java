package ua.kavyar.repository.specification.coffeeShop;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ua.kavyar.model.CoffeeShop;
import ua.kavyar.repository.specification.SpecificationManager;
import ua.kavyar.repository.specification.SpecificationProvider;

@Component
public class CoffeeShopSpecificationManager implements SpecificationManager<CoffeeShop> {

    private final Map<String, SpecificationProvider<CoffeeShop>> providerMap;

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
