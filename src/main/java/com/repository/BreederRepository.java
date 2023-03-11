package com.repository;


import com.model.table.Breeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BreederRepository extends JpaRepository<Breeder, String>, JpaSpecificationExecutor<Breeder> {}
