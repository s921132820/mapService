package com.my.mapService.controller;

import com.my.mapService.dto.Member;
import com.my.mapService.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService service;

//    private final MemberService service;
//
//    public MemberController(MemberService service) {
//        this.service = service;
//    }

//    @Autowired
//    MemberService service;

    @GetMapping({"/" , ""})
    public String list (Model model) {
        // 전체 리스트 얻어오기
        List<Member> memberList = service.findAll();
        model.addAttribute("memberList", memberList);
        return "/members/memberList";
    }

    @GetMapping("/new")
    public String newMembers(Model model) {
        // 신규 회원가입하기 창 보이기
        model.addAttribute("member", new Member());
        return "/members/createMemberForm";
    }

    @PostMapping("/new")
    public String createMember(Member member) {
        // 1. 회원가입 창에서 보낸 자료를 memberDto로 받는다
        System.out.println(member);
        // 2. 받은 회원정보를 서비스에 보내서 맵에 저장한다.
        service.join(member);
        return "redirect:/members";
    }

    @GetMapping("/delete/{id}")
    public String deleteMember(
            @PathVariable("id")Long id
    ) {
//        System.out.println(id);
        service.deleteById(id);
        return "redirect:/members";
    }

    @GetMapping("/update/{id}")
    public String updateMember(@PathVariable("id")Long id, Model model) {
        // 1. id로 검색 후 Member를 수정 폼에 전달해서 뿌려준다.
        model.addAttribute("member", service.findOne(id));
        return "/members/updateMemberForm";
    }

    @PostMapping("/update")
    public String update(Member member) {
        System.out.println(member);
        service.update(member);
        return "redirect:/members/";
    }
}
