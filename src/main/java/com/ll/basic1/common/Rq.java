package com.ll.basic1.common;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;

@Component
@RequestScope
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

    public boolean removeCookie(String key) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return false;
        }

        Arrays
                .stream(cookies)
                .filter(cookie -> cookie.getName().equals(key))
                .forEach(cookie -> {
                    cookie.setMaxAge(0);
                    res.addCookie(cookie);
                });
        return true;
    }

    public void setSession(String name, String value) {
        HttpSession session = req.getSession();
        session.setAttribute(name, value);
    }

    public String getSession(String name, String defaultValue) {
        String value = getSessionAsStr(name, null);

        if (value == null) {
            return defaultValue;
        }

        try {
            return value;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private String getSessionAsStr(String name,String defaultValue) {
        try {
            String value = (String) req.getSession().getAttribute(name);
            if (value == null) {
                return defaultValue;
            }
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private long getSessionAsLong(String name,long defaultValue) {
        try {
            Long value = (Long) req.getSession().getAttribute(name);
            if (value == null) {
                return defaultValue;
            }
            return value;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean removeSession(String name) {
        HttpSession session = req.getSession();

        if (session.getAttribute(name) == null) {
            return false;
        }

        session.removeAttribute(name);
        return true;
    }
}
