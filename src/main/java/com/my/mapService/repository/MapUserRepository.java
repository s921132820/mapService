package com.my.mapService.repository;

import com.my.mapService.dto.User;

import java.util.*;

public class MapUserRepository implements UserRepository {
    // 전체 회원가입자를 저장할 맵을 선언
    public static Map<String, User> userStore = new HashMap<>();
    // 맵에 저장할 때 사용할 ID(key) 선언
    private static Long userIndex = 1L;

    @Override
    public User save(User user) {
        String userId = user.getUserId();
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 ID입니다.");
        }
        userStore.put(userId, user);
        return user;
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(userStore.get(userId));
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>(userStore.values());
        return userList;
    }

    @Override
    public void deleteById(String userId) {
//        userStore.remove(userId);
        if (userStore.containsKey(userId)) {
            userStore.remove(userId);
            System.out.println("삭제 성공: ID=" + userId);
        } else {
            System.out.println("삭제 실패: 존재하지 않는 ID=" + userId);
        }
    }


    @Override
    public User updateById(String userId, User user) {
        // 기존 userId를 가진 데이터가 있으면 삭제
        userStore.remove(userId);
        // 새 데이터로 업데이트
        userStore.put(userId, user);
        return user;
    }
}