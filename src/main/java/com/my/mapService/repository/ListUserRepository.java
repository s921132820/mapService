package com.my.mapService.repository;

import com.my.mapService.dto.User;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListUserRepository implements UserRepository{
    public static List<User> userStore = new ArrayList<>();

    @Override
    public User save(User user) {
        userStore.add(user);
        return user;
    }

    @Override
    public Optional<User> findById(String userId) {
        return userStore
                .stream()
                .filter(x -> x.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return userStore;
    }

    @Override
    public void deleteById(String userId) {
        User findUser =
                userStore
                        .stream()
                        .filter(x -> x.getUserId().equals(userId))
                        .findAny()
                        .orElse(null);
        if (! ObjectUtils.isEmpty(findUser)) {
            userStore.remove(findUser);
        }
    }

    @Override
    public User updateById(String userId, User user) {
        userStore.set(findByStoreIndex(userId), user);
        return user;

    }

    private int findByStoreIndex(String userId) {
        User findUser = userStore
                .stream()
                .filter(x -> x.getUserId().equals(userId))
                .findAny()
                .get();
        return userStore.indexOf(findUser);
    }

    public void clearStore() {
        userStore.clear();
    }
}