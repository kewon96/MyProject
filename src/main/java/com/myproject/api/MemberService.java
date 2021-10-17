package com.myproject.api;

import com.myproject.common.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService implements BaseService, UserDetailsService {

    @Autowired
    private MemberRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Object> searchList(Object param) {
        return null;
    }

    @Override
    public Object searchOne(Object param) {
        return null;
    }

    @Override
    public int addData(Object param) {
        return 0;
    }

    @Override
    public int editData(Object param) {
        return 0;
    }

    @Override
    public int removeData(Object param) {
        return 0;
    }

    public Member registMember(Member member) {
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        Member save = userRepository.save(member);
        System.out.println(save);
        return save;
    }

    /** 계정이 가지고 있는 권한 목록 return */
    private Collection<? extends GrantedAuthority> authorities(Member member) {
        return member.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<Member> option = Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id)));

        Member member = option.get();

        return new User(member.getEmail(), member.getPassword(), authorities(member));
    }
}
