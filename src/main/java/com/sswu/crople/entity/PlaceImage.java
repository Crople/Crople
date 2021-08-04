package com.sswu.crople.entity;

import lombok.*;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "place")
public class PlaceImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;

}
