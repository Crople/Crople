package com.sswu.crople.repository;

import com.sswu.crople.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertUsers(){
        //testuser1 ~ testuser100 생성하는 코드
        IntStream.rangeClosed(1, 100).forEach(i -> {
            User user = User.builder()
                    .email("testuser" + i + "@test.com")
                    .pw("password")
                    .nickname("testuser" + i).build();
            userRepository.save(user);
        });
    }

//    @Commit
//    @Transactional
//    @Test
//    public void testDeleteUser(){
//
//        User user = User.builder().userId(32L).build();
//
//        //testuser32가 작성한 리뷰가 있는 경우 exception 발생
////        userRepository.deleteById(32L);
////        reviewRepository.deleteByUser(user);
//
//        reviewRepository.deleteByUser(user);
//        userRepository.deleteById(32L);
//    }
}
