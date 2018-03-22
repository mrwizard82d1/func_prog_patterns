package com.cjl_magistri.tinyweb.example;

import com.cjl_magistri.tinyweb.Filter;
import com.cjl_magistri.tinyweb.HttpRequest;

public class LoggingFilter implements Filter {
    @Override
    public HttpRequest doFilter(HttpRequest request) {
        System.out.println("In LoggingFilter: request for path: " + request.getPath());
        return request;
    }
}
