package com.sswu.crople.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long reviewId;
    private Long placeId;
    private String nickname;
    private String email;
    private int grade;
    private String text;
    private LocalDateTime regDate, modDate;

    @Builder.Default
    private List<ReviewImageDTO> imageDTOList = new ArrayList<>();

}
