package com.repository;


import com.model.table.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, String>, JpaSpecificationExecutor<Price> {
    List<Price> findByPetId(String petId);
}
