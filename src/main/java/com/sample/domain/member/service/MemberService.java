package com.sample.domain.member.service;

import com.sample.common.security.SecurityUtil;
import com.sample.domain.member.dto.MemberResponseDto;
import com.sample.domain.member.entity.Member;
import com.sample.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    public final MemberRepository memberRepository;

    public MemberResponseDto getUserInfo(Long id) {
        return memberRepository.findById(id).map(MemberResponseDto::of).orElseThrow(() -> new RuntimeException("not found user"));
    }

    public MemberResponseDto getCurrentUserInfo() {
        return memberRepository.findByLoginId(SecurityUtil.getCurrentUserLoginId()).map(MemberResponseDto::of).orElseThrow(() -> new RuntimeException("not found login user"));
    }

    public Member getCurrentUserInfoNotOptional() {
        return memberRepository.findByLoginId(SecurityUtil.getCurrentUserLoginId()).orElseThrow(() -> new RuntimeException("not found login user"));
//        String id = SecurityUtil.getCurrentUserLoginId();
//        Optional<Member> member = memberRepository.findByLoginId(SecurityUtil.getCurrentUserLoginId());
//        return member.orElseThrow(() -> new RuntimeException("not found login user"));
    }

    public Member getUserNotOptional(Long id) {
        Optional<Member> member = memberRepository.findById(getCurrentUserInfo().getId());
        return member.orElseThrow(() -> new NoSuchElementException("could not find data with id : " + id));
    }
}
