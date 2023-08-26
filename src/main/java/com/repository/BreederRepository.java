package com.repository;


import com.model.table.Breeder;
import com.model.table.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreederRepository extends JpaRepository<Breeder, String>, JpaSpecificationExecutor<Breeder> {
    @Query("select b from  Breeder b inner join b.pets p where p.id  = :petId")
    Breeder findPetId(@Param("petId") String petId);

}
