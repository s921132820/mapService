package com.my.mapService.config;

import com.my.mapService.repository.MapMemberRepository;
import com.my.mapService.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberRepository memberRepository() {
        return new MapMemberRepository();
    }
}
