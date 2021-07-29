package com.sswu.crople.repository;

import com.sswu.crople.entity.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    @Query(
            value = "SELECT * FROM review_image where review_review_id = :reviewId",
            nativeQuery = true)
    List<ReviewImage> findByReview(long reviewId);
}
