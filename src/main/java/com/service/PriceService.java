package com.service;


import com.model.table.Price;

import java.util.List;

public interface PriceService {

    public List<?> findAll();

    public Price findById(String id);
    public List<Price> findByPetId(String id);

    public Price save(Price image);

    public Price update(String id, Price image);

    public void delete(String id);

}
