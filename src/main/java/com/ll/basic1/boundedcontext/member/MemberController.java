package com.ll.basic1.boundedcontext.member;

import com.ll.basic1.common.RsData;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public RsData login(HttpServletResponse rs, String username, String password) {
        if (username == null || password == null) {
            return RsData.result("F-3", "Username 과 password를 입력력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);
        if (rsData.isSuccess()) {
            rs.addCookie(new Cookie("user", rsData.getData().toString()));
        }

        return rsData;
    }

    @GetMapping("/logout")
    public RsData login(HttpServletRequest rq, HttpServletResponse rs) {
        Arrays
                .stream(rq.getCookies())
                .filter(cookie -> cookie.getName().equals("user"))
                .forEach(cookie -> {
                    cookie.setMaxAge(0);
                    rs.addCookie(cookie);
                });
        return RsData.result("S-1", "로그아웃 되었습니다.");
    }

    @GetMapping("/me")
    public RsData me(HttpServletRequest rq) {
        Cookie[] cookies = rq.getCookies();
        if (cookies == null) {
            return RsData.result("F-1", "로그인 후 이영해주세요");
        }

        return memberService.getUser(cookies);
    }
}
