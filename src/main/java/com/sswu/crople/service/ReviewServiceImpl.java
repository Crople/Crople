package com.sswu.crople.service;

import com.sswu.crople.dto.PlaceDTO;
import com.sswu.crople.dto.ReviewDTO;
import com.sswu.crople.entity.Place;
import com.sswu.crople.entity.Review;
import com.sswu.crople.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    // placeId에 해당하는 place의 review들을 반환하는 메서드
    @Override
    public List<ReviewDTO> getListOfPlace(Long placeId) {
        Place place = Place.builder().placeId(placeId).build();

        List<Review> result = reviewRepository.findByPlace(place);

        return result.stream().map(placeReview -> entityToDTO(placeReview)).collect(Collectors.toList());
    }

    // review를 등록하는 메서드
    @Override
    public Long register(ReviewDTO placeReviewDTO) {
        Review placeReview = dtoToEntity(placeReviewDTO);
        reviewRepository.save(placeReview);
        return placeReview.getReviewId();
    }

    // review를 수정하는 메서드
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

    // review를 삭제하는 메서드
    @Override
    public void remove(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
