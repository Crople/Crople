package com.sswu.crople.security.service;

import com.sswu.crople.entity.Member;
import com.sswu.crople.repository.MemberRepository;
import com.sswu.crople.security.dto.MemberAuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CropleUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("CropleUserDetailsService loadUserByUsername" + username);

        Optional<Member> result = memberRepository.findByEmail(username);

        if(!result.isPresent()){
            throw new UsernameNotFoundException("Check Email");
        }

        Member member = result.get();

        log.info("----------------------------------------" + member);

        MemberAuthDTO memberAuthDTO = new MemberAuthDTO(
                member.getEmail(),
                member.getPassword(),
                member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toList())
        );

        memberAuthDTO.setNickname(member.getNickname());

        return memberAuthDTO;
    }
}
