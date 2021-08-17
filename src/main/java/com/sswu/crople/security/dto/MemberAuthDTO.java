package com.sswu.crople.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Setter
@Getter
@ToString
public class MemberAuthDTO extends User {

    private String email;

    private String nickname;

    //22~30 내가 수정함.
    private Map<String, Object> attr;
    public MemberAuthDTO(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities, Map<String,Object> attr){
        this(username, password,authorities);
        this.attr=attr;
    }

    public MemberAuthDTO(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities) {

        super(username, password, authorities);
        this.email = username;
    }

    public Map<String,Object> getAttributes(){
        return this.attr;
    }
}