package com.myproject.api;

import com.myproject.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class MemberController {

    @Autowired private MemberService memberService;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public String login(@RequestBody Member member) {
        Member checkMember = memberService.findByEmail(member);

        return jwtTokenProvider.createToken(checkMember.getUsername(), checkMember.getRoles());
    }

    @PostMapping("/regist")
    public String register(@RequestBody Member member) {
        Member createMember = memberService.save(Member.builder()
                .email(member.getEmail())
                .password(passwordEncoder.encode(member.getPassword()))
                .username(member.getUsername())
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        System.out.println(2);
        return createMember.getEmail();
    }
}
