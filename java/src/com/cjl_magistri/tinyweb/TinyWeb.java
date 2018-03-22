package com.cjl_magistri.tinyweb;

import java.util.List;
import java.util.Map;

public class TinyWeb {
    private final Map<String, Controller> routes;
    private final List<Filter> filters;

    public TinyWeb(Map<String, Controller> routes, List<Filter> filters) {
        this.routes = routes;
        this.filters = filters;
    }

    public HttpResponse handleRequest(HttpRequest request) {
        HttpRequest currentRequest = request;
        for (Filter toApply: filters) {
            currentRequest = toApply.doFilter(currentRequest);
        }

        Controller controller = routes.get(currentRequest.getPath());
        if (null == controller) {
            return null;
        }
        return controller.handleRequest(currentRequest);
    }

}
