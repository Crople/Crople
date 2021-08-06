package com.sswu.crople.dto;

import com.sswu.crople.entity.PlaceImage;
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
    private String map;
    private String menu;

    @Builder.Default
    private List<PlaceImageDTO> imageDTOList = new ArrayList<>();

    private double avg;
    private int reviewCnt;
}
