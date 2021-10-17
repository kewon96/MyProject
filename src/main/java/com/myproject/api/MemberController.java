package com.myproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public Object login(@RequestBody Member member) {
//        memberService.registMember(member);
        return "123";
    }

    @PostMapping("/regist")
    public String register(@RequestBody Member member) {
        memberService.registMember(member);

        return "end";
    }
}
