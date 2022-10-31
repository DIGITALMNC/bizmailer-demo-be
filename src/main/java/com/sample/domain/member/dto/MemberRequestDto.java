package com.sample.domain.member.dto;

import com.sample.domain.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {

    //swagger test용 예시값 세팅
    @ApiModelProperty(required = true, example = "aaa")
    private String loginId;

    @ApiModelProperty(required = true, example = "1111")
    private String password;

    @ApiModelProperty(required = true, example = "aaa")
    private String name;

    @ApiModelProperty(required = true, example = "aaa@test.com")
    private String email;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .build();
    }
}
