package com.sswu.crople.service;

import com.sswu.crople.dto.PlaceDTO;
import com.sswu.crople.dto.ReviewDTO;
import com.sswu.crople.dto.ReviewImageDTO;
import com.sswu.crople.entity.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ReviewService {

    List<ReviewDTO> getListOfPlace(Long placeId);
    Long register(ReviewDTO placeReviewDTO);
    void modify(ReviewDTO placeReviewDTO);
    void remove(Long reviewId);

    default Map<String, Object> dtoToEntity(ReviewDTO placeReviewDTO){
        Map<String, Object> entityMap = new HashMap<>();

        Review placeReview = Review.builder()
                .reviewId(placeReviewDTO.getReviewId())
                .place(Place.builder().placeId(placeReviewDTO.getPlaceId()).build())
                .member(Member.builder().email(placeReviewDTO.getEmail()).build())
                .grade(placeReviewDTO.getGrade())
                .text(placeReviewDTO.getText())
                .build();

        entityMap.put("review", placeReview);

        List<ReviewImageDTO> imageDTOList = placeReviewDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0){
            List<ReviewImage> reviewImageList = imageDTOList.stream()
                    .map(reviewImageDTO -> {
                        ReviewImage reviewImage = ReviewImage.builder()
                                .url(reviewImageDTO.getObjectURL())
                                .review(placeReview)
                                .build();
                        return reviewImage;
                    }).collect(Collectors.toList());
            entityMap.put("imgList", reviewImageList);
        }

        return entityMap;
    }

    default ReviewDTO entityToDTO(Review placeReview, List<ReviewImage> reviewImages){
        ReviewDTO placeReviewDTO = ReviewDTO.builder()
                .reviewId(placeReview.getReviewId())
                .placeId(placeReview.getPlace().getPlaceId())
                .nickname(placeReview.getMember().getNickname())
                .email(placeReview.getMember().getEmail())
                .grade(placeReview.getGrade())
                .text(placeReview.getText())
                .regDate(placeReview.getRegDate())
                .modDate(placeReview.getModDate())
                .build();

        List<ReviewImageDTO> reviewImageDTOList = reviewImages.stream()
                .map(reviewImage -> {
                    return ReviewImageDTO.builder()
                            .objectURL(reviewImage.getUrl())
                            .build();
                }).collect(Collectors.toList());

        placeReviewDTO.setImageDTOList(reviewImageDTOList);

        return placeReviewDTO;
    }
}
