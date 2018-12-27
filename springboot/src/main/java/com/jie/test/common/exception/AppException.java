package com.jie.test.common.exception;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = -2060388860850768918L;

    public String code;

    public String messgae;

    public AppException(String code, String message) {
        this.code = code;
        this.messgae = message;
    }
}
