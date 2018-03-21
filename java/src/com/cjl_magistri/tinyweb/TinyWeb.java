package com.cjl_magistri.tinyweb;

import java.util.List;
import java.util.Map;

public class TinyWeb {
    public TinyWeb(Map<String, Controller> routes, List<Filter> requestFilters) {
    }

    public HttpResponse handleRequest(HttpRequest request) {
        return HttpResponse.Builder.newBuilder()
                .body("some body")
                .responseCode(200)
                .build();
    }

}
