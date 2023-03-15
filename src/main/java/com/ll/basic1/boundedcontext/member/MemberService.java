package com.ll.basic1.boundedcontext.member;

import com.ll.basic1.common.RsData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public RsData tryLogin(String username, String password) {
        RsData res;
        Member findMember = memberRepository.findByUsername(username);

        if (findMember == null) {
            res = RsData.result("F-2", String.format("%s는 존재하지 않는 회원입니다.", username));
        } else {
            if (findMember.getPassword().equals(password)) {
                res = RsData.result("S-1", String.format("%s 님 환영합니다.", username), findMember.getName());
            } else {
                res = RsData.result("F-1", "비밀번호가 일치하지 않습니다.");
            }
        }

        return res;
    }
}
