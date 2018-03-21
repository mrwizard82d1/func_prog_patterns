package com.cjl_magistri.tinyweb;

import java.util.HashMap;
import java.util.Map;

/**
 * An immutable HTTP request.
 */
public class HttpRequest {
    private final String path;
    public String getPath() {
        return path;
    }

    private final String body;
    public String getBody() {
        return body;
    }

    private final Map<String, String> headers;
    public Map<String, String> getHeaders() {
        return headers;
    }

    private HttpRequest(Builder builder) {
        path = builder.path;
        body = builder.body;
        headers = builder.headers;
    }

    public static class Builder {
        private String path;
        public Builder path(String path) {
            this.path = path;
            return this;
        }

        private String body;
        public Builder body(String body) {
            this.body = body;
            return this;
        }

        private Map<String, String> headers;
        public Builder addHeader(String header, String value) {
            this.headers.put(header, value);
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }

        private Builder() {
            this.headers = new HashMap<String, String>();
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        /**
         * Create a Builder from an existing HttpRequest
         *
         * This method is used to support filters that "change" an immutable HttpRequest.
         * @param request The original, unmodified request
         * @return The "modified" request.
         */
        public static Builder builderFrom(HttpRequest request) {
            Builder builder = new Builder();
            builder.path(request.getPath());
            builder.body(request.getBody());

            Map<String, String> headers = request.getHeaders();
            for(String headerName: headers.keySet()) {
                builder.addHeader(headerName, headers.get(headerName));
            }

            return builder;
        }
    }
}
