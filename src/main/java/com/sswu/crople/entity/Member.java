package com.sswu.crople.entity;

import lombok.*;

import javax.persistence.*;
import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member {

    @Id
    private String email;

    private String password;

    private String nickname;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }

    public void changeNickname(String nickname){
        this.nickname = nickname;
    }
}
