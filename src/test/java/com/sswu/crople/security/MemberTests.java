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
    public void createAdmin(){
            Member member = Member.builder()
                    .email("admin")
                    .nickname("admin")
                    .password(passwordEncoder.encode("crople1919"))
                    .build();
            member.addMemberRole(MemberRole.ADMIN);

            repository.save(member);
    }
}
