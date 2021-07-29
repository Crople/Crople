package com.sswu.crople.service;

import com.sswu.crople.dto.PlaceDTO;
import com.sswu.crople.dto.ReviewDTO;
import com.sswu.crople.entity.Place;
import com.sswu.crople.entity.Review;
import com.sswu.crople.entity.ReviewImage;
import com.sswu.crople.repository.ReviewImageRepository;
import com.sswu.crople.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository imageRepository;

    @Override
    public List<ReviewDTO> getListOfPlace(Long placeId) {
        Place place = Place.builder().placeId(placeId).build();

        List<Review> result = reviewRepository.findByPlace(place);

        List<ReviewDTO> reviewDTOList = result.stream()
                .map(placeReview -> entityToDTO(placeReview, imageRepository.findByReview(placeReview.getReviewId())))
                .collect(Collectors.toList());

        return reviewDTOList;
    }

    @Override
    public Long register(ReviewDTO placeReviewDTO) {

        Map<String, Object> entityMap = dtoToEntity(placeReviewDTO);
        Review placeReview = (Review) entityMap.get("review");
        List<ReviewImage> images = (List<ReviewImage>) entityMap.get("imgList");

        reviewRepository.save(placeReview);
        if(images != null) {
            images.forEach(reviewImage -> {
                imageRepository.save(reviewImage);
            });
        }

        return placeReview.getReviewId();
    }

    @Override
    public void modify(ReviewDTO placeReviewDTO) {
        Optional<Review> result = reviewRepository.findById(placeReviewDTO.getReviewId());

        if(result.isPresent()){
            Review placeReview = result.get();
            placeReview.changeGrade(placeReviewDTO.getGrade());
            placeReview.changeText(placeReviewDTO.getText());

            reviewRepository.save(placeReview);
        }
    }

    @Override
    public void remove(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
