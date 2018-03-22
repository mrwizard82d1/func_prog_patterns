package com.cjl_magistri.tinyweb.example;

import com.cjl_magistri.tinyweb.*;

import java.util.*;

public class TestHarness {

    public static void main(String[] args) {
        TinyWeb framework = new TinyWeb(makeRoutes(), makeFilters());

        HttpRequest request = HttpRequest.Builder.newBuilder()
                .path("greeting/")
                .body("Mike,Joe,John,Steve")
                .addHeader("X-Example", "exampleHeader")
                .build();

        HttpResponse response = framework.handleRequest(request);
        if (response != null) {
            System.out.println(response.getResponseCode());
            System.out.println(response.getBody());
        } else {
            System.out.println("A null. Something is missing in the state of Denmark!");
        }
    }

    private static Map<String, Controller> makeRoutes() {
        GreetingRenderingStrategy viewRenderer = new GreetingRenderingStrategy();
        StrategyView greetingView = new StrategyView(viewRenderer);
        GreetingController greetingController = new GreetingController(greetingView);

        Map<String, Controller> routeMap = new HashMap<String, Controller>();
        routeMap.put("greeting/", greetingController);
        return Collections.unmodifiableMap(routeMap);
    }

    private static List<Filter> makeFilters() {
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(new LoggingFilter());
        return filters;
    }
}
