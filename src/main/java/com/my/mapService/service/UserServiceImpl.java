package com.my.mapService.service;

import com.my.mapService.dto.User;
import com.my.mapService.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Long signUp(User user) {
        User saveUser = userRepository.save(user);
        if(! ObjectUtils.isEmpty(saveUser)) return 0L;
        else return -1L;
    }

    @Override
    public Optional<User> findById(String id) {
        User findUser = userRepository.findById(id).orElse(null);
        if(! ObjectUtils.isEmpty(findUser)) return Optional.ofNullable(findUser);
        else return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(User user) {
        userRepository.updateById(user.getUserId(), user);
    }
}