package com.stucrud.StuCrud.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shoe_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Shoe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoe_id")
    private int shoeId;

    @Column(name = "shoe_brand")
    private String shoeBrand;

    @Column(name = "shoe_colour")
    private String shoeColour;

}
