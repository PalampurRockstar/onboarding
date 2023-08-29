package com.model.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="images")
public class Image {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String file;
    private String path;
    private String cdn;
    private Boolean isProfilePicture;
    @ManyToOne(fetch=FetchType.LAZY)
    private Pet pet;


//    @JsonBackReference(value="review-pet")
//    @ManyToOne(fetch=FetchType.EAGER)
//    private Review review;
}