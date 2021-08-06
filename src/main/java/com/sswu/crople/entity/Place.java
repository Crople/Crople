package com.sswu.crople.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Place{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    private String name;

    private String type;

    private String address;

    private String tel;

    private String map;

    private String menu;
}
