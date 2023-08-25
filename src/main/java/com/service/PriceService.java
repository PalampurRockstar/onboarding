package com.service;


import com.model.table.Price;

import java.util.List;

public interface PriceService {

    List<?> findAll();

    Price findById(String id);

    Price save(Price image);

	Price update(String id, Price image);

    void delete(String id);

}
