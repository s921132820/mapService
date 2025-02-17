package com.my.mapService.repository;

import com.my.mapService.dto.Member;
import com.my.mapService.dto.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    // 1. 회원 리스트에 저장(userStore : List)
    User save(User user);

    // 2. 특정 ID를 갖고 검색하는 기능
    Optional<User> findById(String userId);
    // 3. 전체 데이터를 검색
    List<User> findAll();

    // 4. 삭제(ID) 하기
    void deleteById(String userId);

    // 5. ID를 갖고 수정하기
    User updateById(String userId, User user);
}