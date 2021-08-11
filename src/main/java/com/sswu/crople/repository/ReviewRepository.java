package com.sswu.crople.repository;

import com.sswu.crople.entity.Place;
import com.sswu.crople.entity.Review;
import com.sswu.crople.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByPlace(Place place);

    @Modifying
    @Query("delete from Review mr where mr = :member")
    void deleteByUser(Member member);

}
