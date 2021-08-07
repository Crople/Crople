package com.sswu.crople.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {

    private Long placeId;
    private String name;
    private String type;
    private String address;
    private String tel;
    private String menu;
    private String map;
    private String imgURL;

    private double avg;
    private int reviewCnt;
}
