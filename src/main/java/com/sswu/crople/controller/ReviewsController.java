package com.sswu.crople.controller;

import com.sswu.crople.dto.ReviewDTO;
import com.sswu.crople.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// /reviews에 대한 요청을 처리하는 컨트롤러
@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewsController {

    private final ReviewService reviewService;

    // placeId에 해당하는 place의 모든 review를 반환하는 메서드
    @GetMapping("/{placeId}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("placeId") Long placeId){
        log.info("=================list=======================");
        log.info("PlaceId: " + placeId);

        List<ReviewDTO> reviewDTOList = reviewService.getListOfPlace(placeId);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    // review를 등록하는 메서드
    @PostMapping("/{placeId}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO placeReviewDTO){
        log.info("--------------add PlaceReview-----------------");
        log.info("reviewDTO: " + placeReviewDTO);

        Long reviewId = reviewService.register(placeReviewDTO);

        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }

    // review를 수정하는 메서드
    @PutMapping("/{placeId}/{reviewId}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewId, @RequestBody ReviewDTO placeReviewDTO){
        log.info("--------------------modify PlaceReview----------------");
        log.info("reviewDTO: " + placeReviewDTO);
        reviewService.modify(placeReviewDTO);
        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }

    //review를 삭제하는 메서드
    @DeleteMapping("{placeId}/{reviewId}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewId){
        log.info("--------------------modify removeReview----------------");
        log.info("reviewDTO: " + reviewId);
        reviewService.remove(reviewId);
        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }
}
