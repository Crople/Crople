package com.sswu.crople.security;

import com.sswu.crople.entity.Member;
import com.sswu.crople.entity.MemberRole;
import com.sswu.crople.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1, 10).forEach(i ->{
            Member member = Member.builder()
                    .email("user" + i + "@sungshin.ac.kr")
                    .nickname("testuser" + i)
                    .password(passwordEncoder.encode("12345678"))
                    .build();
            member.addMemberRole(MemberRole.USER);

            repository.save(member);
        });
    }
}
