package com.cjl_magistri.tinyweb.example;

import com.cjl_magistri.tinyweb.Controller;
import com.cjl_magistri.tinyweb.Filter;
import com.cjl_magistri.tinyweb.TinyWeb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHarness {

    public static void main(String[] args) {
        System.out.println("Hello, Java Test Harness.");

        TinyWeb framework = new TinyWeb(makeRoutes(), makeFilters());
    }

    private static Map<String, Controller> makeRoutes() {
        return new HashMap<String, Controller>();
    }

    private static List<Filter> makeFilters() {
        return new ArrayList<Filter>();
    }
}
