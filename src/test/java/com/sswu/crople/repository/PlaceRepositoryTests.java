package com.sswu.crople.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootTest
public class PlaceRepositoryTests {

    @Autowired
    private PlaceRepository placeRepository;

    @Commit
    @Transactional
    @Test
    public void insertPlaces(){
        //임의의 place를 추가하는 코드
//        Place place = Place.builder()
//                .name("청춘예찬")
//                .type("술집")
//                .address("동소문로20길 29-14")
//                .tel("?")
//                .build();
//        placeRepository.save(place);
//
//        PlaceImage placeImage = PlaceImage.builder()
//                .uuid(UUID.randomUUID().toString())
//                .place(place)
//                .imgName("test.jpg").build();
//        imageRepository.save(placeImage);
    }

//    @Test
//    public void testListPage(){
//        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "placeId"));
//
//        Page<Object[]> result = placeRepository.getListPage(pageRequest);
//
//        for(Object[] objects : result.getContent()){
//            System.out.println(Arrays.toString(objects));
//        }
//    }

//    @Test
//    public void testGetPlaceWithAll(){
//        List<Object[]> result = placeRepository.getPlaceWithAll(1L);
//
//        System.out.println(result);
//
//        for(Object[] arr : result){
//            System.out.println(Arrays.toString(arr));
//        }
//    }
}
