package com.sample.domain.member.entity;

import com.sample.common.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="loginId")
    private String loginId;

    @Column(name="password")
    private String password;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Builder
    public Member(String loginId, String password, String name, String email) {
        super();
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
