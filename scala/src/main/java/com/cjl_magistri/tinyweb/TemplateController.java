package com.cjl_magistri.tinyweb;

import java.util.List;
import java.util.Map;

public abstract class TemplateController implements Controller {
    private View view;

    public TemplateController(View view) {
        this.view = view;
    }

    @Override
    public HttpResponse handleRequest(HttpRequest request) {
        Integer responseCode = 200;
        String responseBody = "";

        try {
            Map<String, List<String>> model = doRequest(request);
            responseBody = view.render(model);
        } catch (ControllerException ce) {
            responseCode = ce.getStatusCode();
        } catch (RenderingException re) {
            responseCode = 500;
            responseBody = "Exception while rendering.";
        } catch (Exception ex) {
            responseCode = 500;
            responseBody = ex.getMessage();
        }

        return HttpResponse.Builder.newBuilder()
                .body(responseBody)
                .responseCode(responseCode)
                .build();
    }

    protected abstract Map<String, List<String>> doRequest(HttpRequest request);
}
