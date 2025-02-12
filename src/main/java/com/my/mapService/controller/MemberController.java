package com.my.mapService.controller;

import com.my.mapService.dto.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {
    @GetMapping({"/" , ""})
    public String list () {
        return "/members/memberList";
    }

    @GetMapping("/new")
    public String newMembers() {
        return "/members/createMemberForm";
    }

    @PostMapping("/new")
    public String createMember(Member member) {
        // 1. 회원가입 창에서 보낸 자료를 memberDto로 받는다
        System.out.println(member);
        // 2. 받은 회원정보를 서비스에 보내서 맵에 저장한다.
        return "redirect:/members";
    }
}
