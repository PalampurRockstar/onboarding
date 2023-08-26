package com.model.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="addresses")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Address {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String street;

    private String pin;
    @JsonIgnore
//    @JsonBackReference
    @OneToOne(mappedBy = "address",cascade = {CascadeType.MERGE})
    private Location location;

}