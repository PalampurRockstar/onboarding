package com.validator;

import com.model.jpa.PriceFilter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceFilterValidator implements ConstraintValidator<PriceRangeValidator, PriceFilter> {
    public boolean isValid(PriceFilter price, ConstraintValidatorContext cvc) {
        return (price.getFrom() != null && price.getTo() != null) || (price.getFrom() == null && price.getTo() == null);
    }
}
