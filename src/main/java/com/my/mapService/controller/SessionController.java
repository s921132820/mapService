package com.my.mapService.controller;

import com.my.mapService.dto.User;
import com.my.mapService.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class SessionController {
    private final UserServiceImpl userService;

    public SessionController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String loginProcess(User user, HttpSession session) {
        System.out.println("login Info : " + user);
        User tempUser = userService
                .findById(user.getUserId()).orElse(null);
        User sessionUser = tempUser;
        // 해당 회원정보를 찾았을 경우, 못 찾았을 경우(null)
        // 스프링에서 널값 또는 빈값을 비교할 때 사용...
        if (! ObjectUtils.isEmpty(sessionUser)) {
            if (user.getPassword().equals(
                    sessionUser.getPassword())) {
                // 로그인 성공
                // 세션을 생성해서 전달
                // 비밀번호 확인 후
                session.setAttribute("sessionInfo", sessionUser);
                session.setMaxInactiveInterval(60 * 30); // 30분
                return "home";
            } else {
                // 로그인 실패
                return "/users/login";
            }
        } else {
            // 로그인 실패
            return "/users/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "home";
    }
}