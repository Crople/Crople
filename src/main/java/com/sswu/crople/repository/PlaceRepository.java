package com.sswu.crople.repository;

import com.sswu.crople.entity.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    @Query("select p, avg(coalesce(r.grade, 0)), count(distinct r) from Place p" +
            " left outer join Review r on r.place = p where p.type=:type group by p " )
    Page<Object[]> getListPage(String type, Pageable pageable);

    @Query("select p, avg(coalesce(r.grade, 0)), count(r) from Place p" +
            " left outer join Review r on r.place = p where p.placeId = :placeId" )
    List<Object[]> getPlaceWithAll(Long placeId);
}
