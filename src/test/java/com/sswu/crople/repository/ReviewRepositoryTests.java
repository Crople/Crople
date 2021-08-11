package com.sswu.crople.repository;

import com.sswu.crople.entity.Place;
import com.sswu.crople.entity.Review;
import com.sswu.crople.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertPlaceReview(){
        //임의의 리뷰를 추가하는 코드
//        Review placeReview = Review.builder()
//                .user(User.builder().userId(89L).build())
//                .place(Place.builder().placeId(1L).build())
//                .grade(4)
//                .text("place1에 대한 testuser89의 리뷰")
//                .build();
//
//        reviewRepository.save(placeReview);
    }

//    @Test
//    public void testGetPlaceReviews(){
//        Place place = Place.builder().placeId(1L).build();
//
//        List<Review> result = reviewRepository.findByPlace(place);
//
//        result.forEach(placeReview -> {
//            System.out.print(placeReview.getReviewId() + " ");
//            System.out.print(placeReview.getGrade()  + " ");
//            System.out.print(placeReview.getText()  + " ");
//            System.out.println(placeReview.getUser().getEmail()  + " ");
//            System.out.println("===============================");
//        });
//    }
}
