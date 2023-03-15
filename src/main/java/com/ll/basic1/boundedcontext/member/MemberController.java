package com.ll.basic1.boundedcontext.member;

import com.ll.basic1.common.Rq;
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
            return RsData.result("F-3", "Username 과 password를 입력해주세요.");
        }

        RsData rsData = memberService.tryLogin(username, password);
        if (rsData.isSuccess()) {
            rs.addCookie(new Cookie("user", rsData.getData().toString()));
        }

        return rsData;
    }

    @GetMapping("/logout")
    public RsData login(HttpServletRequest req, HttpServletResponse res) {
        Rq rq = new Rq(req, res);
        rq.removeCookie("user");

        return RsData.result("S-1", "로그아웃 되었습니다.");
    }

    @GetMapping("/me")
    public RsData me(HttpServletRequest req) {
        Rq rq = new Rq(req, null);
        String username = rq.getCookie("user", "anonymous");

        if (username.equals("anonymous")) {
            return RsData.result("F-1", "로그인 후 이용해주세요");
        }

        return RsData.result("S-1", String.format("당신의 username은 %s입니다.", username));
    }
}
