package com.ll.basic1.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class Rq {
    HttpServletRequest req;
    HttpServletResponse res;

    public void setCookie(String key, String value) {
        res.addCookie(new Cookie(key, value));
    }

    public void setCookie(String key, long value) {
        res.addCookie(new Cookie(key, String.valueOf(value)));
    }

    public String getCookie(String key, String defaultValue){
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return defaultValue;
        }

        return Arrays
                .stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(key))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(defaultValue);
    }

    public Long getCookieAsLong(String key, Long defaultValue) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return defaultValue;
        }

        return Arrays
                .stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals(key))
                .mapToLong(cookie -> Long.parseLong(cookie.getValue()))
                .findFirst()
                .orElse(0);
    }

    public void removeCookie(String key) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return;
        }

        Arrays
                .stream(cookies)
                .filter(cookie -> cookie.getName().equals(key))
                .forEach(cookie -> {
                    cookie.setMaxAge(0);
                    res.addCookie(cookie);
                });
    }
}
