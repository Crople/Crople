package com.sswu.crople.security.service;

import com.sswu.crople.entity.Member;
import com.sswu.crople.entity.MemberRole;
import com.sswu.crople.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CropleOAuthUserDetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    static int numOfUser = 0;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        String clientName = userRequest.getClientRegistration().getClientName();

        OAuth2User oAuth2User = super.loadUser(userRequest);

        //34~36 내가 추가함
        oAuth2User.getAttributes().forEach((k,v)->{
            log.info(k+":"+v);
        });
        String email = null;

        if(clientName.equals("Google")){
            email = oAuth2User.getAttribute("email");
        }

        Member member = saveSocialMember(email);

        return oAuth2User;
    }

    private Member saveSocialMember(String email) {
        Optional<Member> result = memberRepository.findByEmail(email);

        if(result.isPresent()){
            return result.get();
        }

        Member member = Member.builder()  //email(email)내가 추가함
                .email(email)
                .nickname(email) //원래 nickname + n번째 유저
                .build();
        member.addMemberRole(MemberRole.USER);

        memberRepository.save(member);

        return member;
    }
}