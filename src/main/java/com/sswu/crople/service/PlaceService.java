package com.sswu.crople.service;

import com.sswu.crople.dto.PageRequestDTO;
import com.sswu.crople.dto.PageResultDTO;
import com.sswu.crople.dto.PlaceDTO;
import com.sswu.crople.dto.PlaceImageDTO;
import com.sswu.crople.entity.Place;
import com.sswu.crople.entity.PlaceImage;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface PlaceService {

    Long register(PlaceDTO placeDTO);

    PageResultDTO<PlaceDTO, Object[]> getList(PageRequestDTO requestDTO);

    PlaceDTO getPlace(Long placeId);

    default Map<String, Object> dtoToEntity(PlaceDTO placeDTO){

        Map<String, Object> entityMap = new HashMap<>();

        Place place = Place.builder()
                .placeId(placeDTO.getPlaceId())
                .name(placeDTO.getName())
                .type(placeDTO.getType())
                .address(placeDTO.getAddress())
                .tel(placeDTO.getTel())
                .map(placeDTO.getMap())
                .menu(placeDTO.getMenu())
                .build();
        entityMap.put("place", place);

        List<PlaceImageDTO> imageDTOList = placeDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0){
            List<PlaceImage> placeImageList = imageDTOList.stream()
                    .map(placeImageDTO -> {
                        PlaceImage placeImage = PlaceImage.builder()
                                .path(placeImageDTO.getPath())
                                .imgName(placeImageDTO.getImgName())
                                .uuid(placeImageDTO.getUuid())
                                .place(place)
                                .build();
                        return placeImage;
                    }).collect(Collectors.toList());

            entityMap.put("imgList", placeImageList);
        }

        return entityMap;
    }

    default PlaceDTO entitiesToDTO(Place place, List<PlaceImage> placeImages, Double avg, Long reviewCnt){

        PlaceDTO placeDTO = PlaceDTO.builder()
                .placeId(place.getPlaceId())
                .name(place.getName())
                .type(place.getType())
                .address(place.getAddress())
                .tel(place.getTel())
                .map(place.getMap())
                .menu(place.getMenu())
                .build();

        List<PlaceImageDTO> placeImageDTOList = placeImages.stream()
                .map(placeImage -> {
                    return PlaceImageDTO.builder()
                            .path(placeImage.getPath())
                            .imgName(placeImage.getImgName())
                            .uuid(placeImage.getUuid())
                            .build();
        }).collect(Collectors.toList());

        placeDTO.setImageDTOList(placeImageDTOList);
        placeDTO.setAvg(avg);
        placeDTO.setReviewCnt(reviewCnt.intValue());

        return placeDTO;
    }

}
