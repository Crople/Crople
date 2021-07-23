package com.sswu.crople.service;

import com.sswu.crople.dto.PageRequestDTO;
import com.sswu.crople.dto.PageResultDTO;
import com.sswu.crople.dto.PlaceDTO;
import com.sswu.crople.entity.Place;
import com.sswu.crople.entity.PlaceImage;
import com.sswu.crople.repository.PlaceImageRepository;
import com.sswu.crople.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{

    private final PlaceRepository placeRepository;

    private final PlaceImageRepository imageRepository;

    // place를 reposigory에 등록하는 메서드
    @Transactional
    @Override
    public Long register(PlaceDTO placeDTO) {
        Map<String, Object> entityMap = dtoToEntity(placeDTO);
        Place place = (Place) entityMap.get("place");
        List<PlaceImage> placeImageList = (List<PlaceImage>) entityMap.get("imgList");

        placeRepository.save(place);

        placeImageList.forEach(placeImage -> {
            imageRepository.save(placeImage);
        });

        return place.getPlaceId();
    }

    // pageRequestDTO의 page, size에 맞는 Place리스트를 담고있는 PageResultDTO를 반환하는 메서드
    @Override
    public PageResultDTO<PlaceDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("placeId").descending());

        Page<Object[]> result = placeRepository.getListPage(pageable);

        Function<Object[], PlaceDTO> fn = (arr -> entitiesToDTO(
                (Place) arr[0],
                (List<PlaceImage>) (Arrays.asList((PlaceImage)arr[1])),
                (Double) arr[2],
                (Long) arr[3]
        ));

        return new PageResultDTO<>(result, fn);
    }

    // placeId에 해당하는 placeDTO를 반환하는 메서드
    @Override
    public PlaceDTO getPlace(Long placeId) {

        List<Object[]> result = placeRepository.getPlaceWithAll(placeId);

        Place place = (Place) result.get(0)[0];
        List<PlaceImage> placeImageList =  new ArrayList<>();

        result.forEach(arr -> {
            PlaceImage placeImage = (PlaceImage) arr[1];
            placeImageList.add(placeImage);
        });

        Double avg = (Double) result.get(0)[2];
        Long reviewCnt = (Long) result.get(0)[3];

        return entitiesToDTO(place, placeImageList, avg, reviewCnt);
    }
}
