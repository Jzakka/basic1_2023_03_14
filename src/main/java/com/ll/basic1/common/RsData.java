package com.ll.basic1.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RsData {
    private final String resultCode;
    private final String msg;
    private final Object data;

    public static RsData result(String resultCode, String msg) {
        return result(resultCode, msg,null);
    }

    public static RsData result(String resultCode, String msg, Object data) {
        return new RsData(resultCode, msg, data);
    }

    public boolean isSuccess(){
        return resultCode.startsWith("S");
    }
}
