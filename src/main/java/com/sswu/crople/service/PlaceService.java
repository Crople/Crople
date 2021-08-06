package com.sswu.crople.service;

import com.sswu.crople.dto.PageRequestDTO;
import com.sswu.crople.dto.PageResultDTO;
import com.sswu.crople.dto.PlaceDTO;
import com.sswu.crople.entity.Place;
public interface PlaceService {

    Long register(PlaceDTO placeDTO);

    PageResultDTO<PlaceDTO, Object[]> getList(PageRequestDTO requestDTO);

    PlaceDTO getPlace(Long placeId);

    default Place dtoToEntity(PlaceDTO placeDTO){

        Place place = Place.builder()
                .placeId(placeDTO.getPlaceId())
                .name(placeDTO.getName())
                .type(placeDTO.getType())
                .address(placeDTO.getAddress())
                .tel(placeDTO.getTel())
                .menu(placeDTO.getMenu())
                .map(placeDTO.getMap())
                .imgURL(placeDTO.getImgURL())
                .build();

        return place;
    }

    default PlaceDTO entityToDTO(Place place, Double avg, Long reviewCnt){

        PlaceDTO placeDTO = PlaceDTO.builder()
                .placeId(place.getPlaceId())
                .name(place.getName())
                .type(place.getType())
                .address(place.getAddress())
                .tel(place.getTel())
                .menu(place.getMenu())
                .map(place.getMap())
                .imgURL(place.getImgURL())
                .build();

        placeDTO.setAvg(avg);
        placeDTO.setReviewCnt(reviewCnt.intValue());

        return placeDTO;
    }

}
