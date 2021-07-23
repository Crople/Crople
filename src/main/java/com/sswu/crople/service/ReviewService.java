package com.sswu.crople.service;

import com.sswu.crople.dto.PlaceDTO;
import com.sswu.crople.dto.ReviewDTO;
import com.sswu.crople.entity.Place;
import com.sswu.crople.entity.Review;
import com.sswu.crople.entity.User;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getListOfPlace(Long placeId);

    Long register(ReviewDTO placeReviewDTO);

    void modify(ReviewDTO placeReviewDTO);

    void remove(Long reviewId);

    default Review dtoToEntity(ReviewDTO placeReviewDTO){
        Review placeReview = Review.builder()
                .reviewId(placeReviewDTO.getReviewId())
                .place(Place.builder().placeId(placeReviewDTO.getPlaceId()).build())
                .user(User.builder().userId(placeReviewDTO.getUserId()).build())
                .grade(placeReviewDTO.getGrade())
                .text(placeReviewDTO.getText())
                .build();
        return placeReview;
    }

    default ReviewDTO entityToDTO(Review placeReview){
        ReviewDTO placeReviewDTO = ReviewDTO.builder()
                .reviewId(placeReview.getReviewId())
                .placeId(placeReview.getPlace().getPlaceId())
                .userId(placeReview.getUser().getUserId())
                .nickname(placeReview.getUser().getNickname())
                .email(placeReview.getUser().getEmail())
                .grade(placeReview.getGrade())
                .text(placeReview.getText())
                .regDate(placeReview.getRegDate())
                .modDate(placeReview.getModDate())
                .build();

        return placeReviewDTO;
    }

}
