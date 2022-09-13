package com.example.demo.domain.sample.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // member() 생성자 메소드 뺄려고 엑세스 레벨은..?
public class Sample {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 요거 뭐하는 놈인지 찾아보기.
    private Long id;

    private String name;

    private String email;

    @Builder
    public Sample(String name, String email) {
        this.name = name;
        this.email = email;
    }
}


