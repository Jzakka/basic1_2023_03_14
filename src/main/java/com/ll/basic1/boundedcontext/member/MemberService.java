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

    public RsData login(HttpServletResponse rs, String username, String password){
        RsData res;
        Member findMember = memberRepository.findByUsername(username);

        if (findMember == null) {
            res = RsData.result("F-2", String.format("%s는 존재하지 않는 회원입니다.", username));
        } else {
            if (findMember.getPassword().equals(password)) {
                res = RsData.result("S-1", String.format("%s 님 환영합니다.", username));
                rs.addCookie(new Cookie("user", findMember.getName()));
            } else {
                res = RsData.result("F-1", "비밀번호가 일치하지 않습니다.");
            }
        }

        return res;
    }

    public RsData getUser(HttpServletRequest rq) {
        Cookie[] cookies = rq.getCookies();
        if (cookies == null) {
            return RsData.result("F-1", "로그인 후 이영해주세요");
        }
        Optional<Cookie> user = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("user"))
                .findFirst();
        return user
                .map(cookie -> RsData.result("S-1", String.format("당신의 username은 %s입니다.", cookie.getName())))
                .orElse(null);
    }
}
