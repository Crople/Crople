package com.sswu.crople.repository;

import com.sswu.crople.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("select p, pi, avg(coalesce(r.grade, 0)), count(distinct r) from Place p " +
            "left outer join PlaceImage pi on pi.place = p " +
            "left outer join Review r on r.place = p group by p " )
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select p, pi, avg(coalesce(r.grade, 0)), count(r) " +
            " from Place p left outer join PlaceImage pi on pi.place = p " +
            " left outer join Review r on r.place = p " +
            " where p.placeId = :placeId group by pi")
    List<Object[]> getPlaceWithAll(Long placeId);
}
