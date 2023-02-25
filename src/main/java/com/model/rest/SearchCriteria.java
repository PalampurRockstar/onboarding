package com.model.rest;

import lombok.*;
import org.springframework.validation.annotation.Validated;
import com.model.jpa.PriceFilter;
import com.validator.PriceRangeValidator;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class SearchCriteria {
    private String location;
    private String breed;
    private String type;
    private String gender;

    @PriceRangeValidator
    private PriceFilter priceFilter;

}