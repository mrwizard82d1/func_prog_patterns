package com.cjl_magistri.tinyweb;

import java.util.List;
import java.util.Map;

public class StrategyView implements View {
    private RenderingStrategy viewRenderer;

    public StrategyView(RenderingStrategy viewRenderer) {
        this.viewRenderer = viewRenderer;
    }

    @Override
    public String render(Map<String, List<String>> model) throws RenderingException {
        try {
            return viewRenderer.renderView(model);
        } catch (Exception ex) {
            throw new RenderingException(ex);
        }
    }
}
