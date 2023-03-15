package com.ll.basic1.boundedcontext.member;

import com.ll.basic1.common.RsData;
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
    public RsData login(HttpServletResponse rs, String username, String password){
        if (username == null || password == null) {
            return RsData.result("F-3", "Username 과 password를 잉ㅂ력해주세요.");
        }

        return memberService.login(rs, username, password);
    }

    @GetMapping("/me")
    public RsData me(HttpServletRequest rq){
        return memberService.getUser(rq);
    }
}
