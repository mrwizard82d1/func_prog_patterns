package com.cjl_magistri.tinyweb;

public class ControllerException extends RuntimeException {
    private final Integer statusCode;

    public ControllerException(Integer statusCode) {
        this.statusCode = statusCode;
    }


    public Integer getStatusCode() {
        return statusCode;
    }
}
