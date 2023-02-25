package com.repository.filter;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.util.annotation.AnnotationDescriptor;
import org.springframework.data.jpa.domain.Specification;
import com.model.jpa.PriceFilter;
import com.model.rest.SearchCriteria;
import com.model.table.Pet;
import com.model.table.Price;
import com.validator.PriceFilterValidator;
import com.validator.PriceRangeValidator;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PetSpecification extends SearchSpecification<SearchCriteria, Pet> {
    private SearchCriteria criteria;

    public PetSpecification(SearchCriteria search) {
        super(search);
    }

    @Override
    public Predicate toPredicate(Root<Pet> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, SearchCriteria search) {
        PriceFilterValidator validator=new PriceFilterValidator();
        validator.initialize(new AnnotationDescriptor.Builder<>(PriceRangeValidator.class, Collections.singletonMap("message", "...")).build().getAnnotation());

        List<Predicate> predicates = new ArrayList();
        if (StringUtils.isNotEmpty(search.getBreed()))
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("breed")
                    ), "%" + search.getBreed().toLowerCase() + "%" ));
        if (StringUtils.isNotEmpty(search.getGender()))
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("gender")
                    ), "%" + search.getGender().toLowerCase() + "%" ));
        if (StringUtils.isNotEmpty(search.getType()))
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("type")
                    ), "%" + search.getType().toLowerCase() + "%" ));
        if (StringUtils.isNotEmpty(search.getLocation())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.join("location", JoinType.LEFT).get("name")
                    ), "%" + search.getLocation().toLowerCase() + "%"));
        }
        if (search.getPriceFilter()!=null && validator.isValid(search.getPriceFilter(),null)) {
            PriceFilter priceFilter=search.getPriceFilter();
            Join<Pet, Price> price = root.join("price", JoinType.LEFT);
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(price.get("amount"),  priceFilter.getFrom()));
            predicates.add(criteriaBuilder.lessThanOrEqualTo(price.get("amount"),  priceFilter.getTo()));

        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

@Data
abstract class SearchSpecification<S,T> implements Specification<T> {

    private static final long serialVersionUID = 1L;

    private S search;

    public SearchSpecification(S search) {
        this.search = search;
    }

    public abstract Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, S search);

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return this.toPredicate(root, query, criteriaBuilder, this.search);
    }

}