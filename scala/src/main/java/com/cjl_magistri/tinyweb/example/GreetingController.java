package com.cjl_magistri.tinyweb.example;

import com.cjl_magistri.tinyweb.HttpRequest;
import com.cjl_magistri.tinyweb.TemplateController;
import com.cjl_magistri.tinyweb.View;

import java.util.*;

public class GreetingController extends TemplateController {

    private final Random random;

    public GreetingController(View view) {
        super(view);
        random = new Random();
    }

    @Override
    protected Map<String, List<String>> doRequest(HttpRequest request) {
        Map<String, List<String>> helloModel = new HashMap<String, List<String>>();
        helloModel.put("greetings", generateGreetings(request.getBody()));
        return helloModel;
    }

    private List<String> generateGreetings(String commaSeparatedNames) {
        String[] names = commaSeparatedNames.split(",");
        List<String> greetings = new ArrayList<String>();
        for (String name: names) {
            greetings.add(makeGreeting(name));
        }
        return greetings;
    }

    private String makeGreeting(String name) {
        String[] greetings = {"Hello", "Greetings", "Salutations", "Hola", "Mahalo"};
        String greetingPrefix = greetings[random.nextInt(greetings.length)];
        return String.format("%s %s", greetingPrefix, name);
    }
}
