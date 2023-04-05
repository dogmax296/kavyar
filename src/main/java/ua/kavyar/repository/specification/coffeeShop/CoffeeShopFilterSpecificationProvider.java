package ua.kavyar.repository.specification.coffeeShop;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ua.kavyar.model.CoffeeShop;
import ua.kavyar.model.Feature;
import ua.kavyar.repository.specification.SpecificationProvider;

@Component
public class CoffeeShopFilterSpecificationProvider implements SpecificationProvider<CoffeeShop> {
    private static final String FILTER_KEY = "filter";
    private static final String FIELD_NAME = "features";

    @Override
    public Specification<CoffeeShop> getSpecification(String[] features) {
        return (root, query, cb) -> {
            Join<CoffeeShop, Feature> featuresJoin = root.join(FIELD_NAME, JoinType.LEFT);
            CriteriaBuilder.In<String> predicate = cb.in(featuresJoin.get("name"));
            for (String feature : features) {
                predicate.value(feature);
            }
            return cb.and(predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
