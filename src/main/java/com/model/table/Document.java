package com.model.table;

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
@Table(name="documents")
public class Document {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String name;
    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "valid_until")
    private String validUntil;

    @ManyToOne(fetch=FetchType.EAGER)
    private Pet pet;
}