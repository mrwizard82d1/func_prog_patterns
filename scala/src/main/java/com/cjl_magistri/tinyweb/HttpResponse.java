package com.cjl_magistri.tinyweb;

public class HttpResponse {
    private final String body;
    public String getBody() {
        return body;
    }

    private final Integer responseCode;
    public Integer getResponseCode() {
        return responseCode;
    }

    private HttpResponse(Builder builder) {
        body = builder.body;
        responseCode = builder.responseCode;
    }

    public static class Builder {
        private String body;
        public Builder body(String body) {
            this.body = body;
            return this;
        }

        private Integer responseCode;
        public Builder responseCode(Integer responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this);
        }

        public static Builder newBuilder() {
            return new Builder();
        }
    }
}
