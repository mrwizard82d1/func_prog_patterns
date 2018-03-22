package com.cjl_magistri.tinyweb;

public interface Controller {
    HttpResponse handleRequest(HttpRequest httpRequest);
}
