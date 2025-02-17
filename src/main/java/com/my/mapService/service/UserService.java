package com.my.mapService.service;

import com.my.mapService.dto.Member;
import com.my.mapService.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    // 신규회원가입
    public Long signUp(User user);

    // 아이디로 검색해서 1개 찾기
    public Optional<User> findById(String id);

    // 전체 리스트 출력
    public List<User> findAll();

    // 삭제 처리
    public void deleteById(String id);

    // 수정 처리
    public void update(User user);
}