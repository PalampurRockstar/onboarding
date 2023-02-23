package com.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Entity
    @Table(name="prices")
    public class Price {
        @Id @GeneratedValue(generator="system-uuid")
        @GenericGenerator(name="system-uuid", strategy = "uuid")
        private String id;
        private float amount;
        @Column(name = "currency_code")
        private String currencyCode;
        @JsonBackReference
        @OneToOne(mappedBy = "price")
        private Pet pet;
    }