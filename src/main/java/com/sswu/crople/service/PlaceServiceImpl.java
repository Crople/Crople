package com.sswu.crople.service;

import com.sswu.crople.dto.PageRequestDTO;
import com.sswu.crople.dto.PageResultDTO;
import com.sswu.crople.dto.PlaceDTO;
import com.sswu.crople.entity.Place;
import com.sswu.crople.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{

    private final PlaceRepository placeRepository;

    // place를 reposigory에 등록하는 메서드
    @Transactional
    @Override
    public Long register(PlaceDTO placeDTO) {
        Place place = (Place) dtoToEntity(placeDTO);

        placeRepository.save(place);

        return place.getPlaceId();
    }

    // pageRequestDTO의 page, size에 맞는 Place리스트를 담고있는 PageResultDTO를 반환하는 메서드
    @Override
    public PageResultDTO<PlaceDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("placeId").descending());

        Page<Object[]> result = placeRepository.getListPage(pageable);

        Function<Object[], PlaceDTO> fn = (arr -> entityToDTO(
                (Place) arr[0],
                (Double) arr[1],
                (Long) arr[2]
        ));

        return new PageResultDTO<>(result, fn);
    }

    // placeId에 해당하는 placeDTO를 반환하는 메서드
    @Override
    public PlaceDTO getPlace(Long placeId) {

        List<Object[]> result = placeRepository.getPlaceWithAll(placeId);

        Place place = (Place) result.get(0)[0];
        Double avg = (Double) result.get(0)[1];
        Long reviewCnt = (Long) result.get(0)[2];

        return entityToDTO(place, avg, reviewCnt);
    }
}
