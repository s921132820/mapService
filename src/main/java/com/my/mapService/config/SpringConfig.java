package com.my.mapService.config;

import com.my.mapService.repository.ListUserRepository;
import com.my.mapService.repository.MapMemberRepository;
import com.my.mapService.repository.MemberRepository;
import com.my.mapService.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberRepository memberRepository() {
        return new MapMemberRepository();
    }

    @Bean
    public UserRepository userRepository() {
        return new ListUserRepository();
    }
}