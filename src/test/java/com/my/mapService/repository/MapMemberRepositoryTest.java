package com.my.mapService.repository;

import com.my.mapService.dto.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MapMemberRepositoryTest {
    MapMemberRepository repository = new MapMemberRepository();

    @AfterEach
    // test 실행 후 맵의 자료구조 깨끗하게 만들어줌
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        // Given
        Member member = new Member();
        member.setName("장원영");
        member.setAddress("서울");
        repository.save(member);

        // When
        Member result = repository.findById(member.getId()).get();

        // Then
//        Long saveId =1L;

        // org.junit.juipter.api
        Assertions.assertEquals(member, result);
        // org.assertj.core.api -- static import
        assertThat(result).isEqualTo(result);
    }

    @Test
    @DisplayName("ID로 검색하기")
    void findById() {
        // Given
        Member member = new Member();
        member.setName("안유진");
        member.setAddress("제주");
        repository.save(member);

        // When
        Long id = 1L;
        Member findMember = repository.findById(id).get();

        // Then
        assertThat(findMember.getName()).isEqualTo("안유진");
    }

    @Test
    @DisplayName("ID로 검색실패")
    void findById_Fail() {
        // Given
        Member member = new Member();
        member.setName("안유진");
        member.setAddress("제주");
        repository.save(member);

        // When
        Long id = 2L;
        Member findMember = repository.findById(id).orElse(null);

        // Then
        assertThat(findMember).isEqualTo(null);
    }

    @Test
    @DisplayName("전체 검색 테스트")
    void findAll() {
        // Given
        Member jang = new Member();
        jang.setName("장원영");
        jang.setAddress("서울");
        repository.save(jang);

        Member ahn = new Member();
        ahn.setName("안유진");
        ahn.setAddress("제주");
        repository.save(ahn);

        //When
        List<Member> list = repository.findAll();

        //Then
        assertThat(list.size()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("이름으로 검색하기")
    void findByName() {
        // 장원영, 안유진을 입력
        // 장원영 검색
        // 찾은 Member 객체가 장원영 객체와 비교, 같은지 확인

        // Given
        Member jang = new Member();
        jang.setName("장원영");
        jang.setAddress("서울");
        repository.save(jang);

        Member ahn = new Member();
        ahn.setName("안유진");
        ahn.setAddress("제주");
        repository.save(ahn);

        //When
//        Optional<Member> list = repository.findByName("장원영");
        Member result = repository.findByName("안유진").get();

        //Then
        assertThat(result).isEqualTo(ahn);
    }

    @Test
    @DisplayName("이름으로 검색하기 실패")
    void findByNameFail() {
        // 장원영, 안유진을 입력
        // 장원영 검색
        // 찾은 Member 객체가 장원영 객체와 비교, 같은지 확인

        // Given
        Member jang = new Member();
        jang.setName("장원영");
        jang.setAddress("서울");
        repository.save(jang);

        //When
        Member result = repository.findByName("안유진").orElse(null);

        //Then
       //
        assertThat(result).isEqualTo(null);
    }

    @Test
    @DisplayName("삭제")
    void deleteById() {
        //Given
        Member member = new Member();
        member.setName("장원영");
        member.setAddress("서울");
        repository.save(member);

        //When
        repository.deleteById(1L);
        Member result = repository.findById(1L).orElse(null);

        //Then
        assertThat(result).isEqualTo(null);
    }

    @Test
    @DisplayName("데이터 수정 확인")
    void updateById() {
        //Given
        //장원영, 서울 입력 --> ID:1
        Member member = new Member();
        member.setName("장원영");
        member.setAddress("서울");
        repository.save(member);

        //When
        // 객체 생성(1. 장원영, 제주) --> updateMenber
        // updateById(1, updateMember)
        Member updateMember = new Member();
        updateMember.setId(1L);
        updateMember.setName("장원영");
        updateMember.setAddress("제주");

        repository.updateById(1L, updateMember);

        //Then
        // id 1번을 읽어와서 주소가 제주로 바꼈는지 확인
        Member result = repository.findById(1L).orElse(null);
        assertThat(result.getAddress()).isEqualTo("제주");
    }
}