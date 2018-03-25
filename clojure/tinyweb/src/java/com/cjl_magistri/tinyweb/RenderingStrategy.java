package com.cjl_magistri.tinyweb;

import java.util.List;
import java.util.Map;

public interface RenderingStrategy {
    String renderView(Map<String, List<String>> model);
}
