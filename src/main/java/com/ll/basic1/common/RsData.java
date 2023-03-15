package com.ll.basic1.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData {
    private String resultCode;
    private String msg;

    public static RsData result(String resultCode, String msg) {
        return new RsData(resultCode, msg);
    }
}
