package com.model.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="reviews")
public class Review {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String comment;
    private Integer likeCount;
    private Integer disLikeCount;

    @JsonBackReference(value="review-pet")
    @ManyToOne(fetch=FetchType.EAGER)
    private Pet pet;

    @JsonBackReference(value="review-breeder")
    @ManyToOne(fetch=FetchType.EAGER)
    private Breeder breeder;

    @JsonManagedReference(value="review-pet")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "evidence_id")
    private List<Image> evidence;

}