package ua.kavyar.service.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationAndSortingHandler {

    private final String[] fields;
    private final int defaultCount;
    private final int defaultPage;

    @Autowired
    public PaginationAndSortingHandler() {
        fields = new String[]{"page", "count", "sortBy"};
        defaultCount = 2;
        defaultPage = 0;
    }

    public Pageable handle(Map<String, String> params) {
        return PageRequest.of(
                params.containsKey(fields[0]) ?
                        Integer.parseInt(params.get(fields[0])) :
                        defaultPage,
                params.containsKey(fields[1]) ?
                        Integer.parseInt(params.get(fields[1])) :
                        defaultCount,
                params.containsKey(fields[2]) ? getSort(params) : Sort.unsorted());
    }

    private Sort getSort(Map<String, String> params) {
        String sortBy = params.get(fields[2]);
        List<Sort.Order> orders = new ArrayList<>();
        if (sortBy.contains(":")) {
            String[] sortingFields = sortBy.split(";");
            for (String field : sortingFields) {
                Sort.Order order;
                if (field.contains(":")) {
                    String[] fieldsAndDirection = field.split(":");
                    String direction = fieldsAndDirection[1].toUpperCase();
                    order = new Sort.Order(Sort.Direction.valueOf(direction),
                            fieldsAndDirection[0]);
                } else {
                    order = new Sort.Order(Sort.Direction.ASC, field);
                }
                orders.add(order);
            }
        } else {
            Sort.Order order = new Sort.Order(Sort.Direction.ASC, sortBy);
            orders.add(order);
        }

        return Sort.by(orders);
    }

    public String[] getFields() {
        return fields;
    }
}
