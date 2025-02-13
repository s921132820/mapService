package com.my.mapService.service;

import com.my.mapService.dto.Member;
import com.my.mapService.repository.MapMemberRepository;
import com.my.mapService.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class MemberService {
    // 1. 필드 주입 방법
//    @Autowired
//    MapMemberRepository memberRepository;

    // 2. 필드 주입 방법
    // @RequiredArgsConstructor와 함께 사용
    // private final MapMemberRepository repository;

    // 3. 생성자 주입 방법
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입 기능
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원은 배제
        Optional<Member> result = memberRepository.findByName(member.getName());
        // 이름 주소 둘 다 확인
        if(result.isPresent() &&  result.get().getAddress().equals(member.getAddress())){
                return -1L;
            } else {
            // 같은 이름이 없으면 입력 가능
            Member save = memberRepository.save(member);
            return save.getId();
        }
    }

    // 아이디로 검색해서 1개 찾기
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }

    // 전체 리스트 출력
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    // 삭제 처리
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    // 수정처리
    public void update(Member member) {
        memberRepository.updateById(member.getId(), member);
    }
}
