package com.cjl_magistri.tinyweb.example;

import com.cjl_magistri.tinyweb.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHarness {

    public static void main(String[] args) {
        System.out.println("Hello, Java Test Harness.");

        TinyWeb framework = new TinyWeb(makeRoutes(), makeFilters());

        HttpRequest request = HttpRequest.Builder.newBuilder()
                .path("greeting")
                .body("Mike,Joe,John,Steve")
                .addHeader("X-Example", "exampleHeader")
                .build();

        HttpResponse response = framework.handleRequest(request);
        System.out.println(response.getResponseCode());
        System.out.println(response.getBody());
    }

    private static Map<String, Controller> makeRoutes() {
        return new HashMap<String, Controller>();
    }

    private static List<Filter> makeFilters() {
        return new ArrayList<Filter>();
    }
}
