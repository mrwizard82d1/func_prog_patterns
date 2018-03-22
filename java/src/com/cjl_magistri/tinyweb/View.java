package com.cjl_magistri.tinyweb;

import java.util.List;
import java.util.Map;

public interface View {
    String render(Map<String, List<String>> model) throws RenderingException;
}