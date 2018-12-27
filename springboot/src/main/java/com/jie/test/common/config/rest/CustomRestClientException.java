package com.jie.test.common.config.rest;

import org.springframework.web.client.RestClientException;

public class CustomRestClientException extends RestClientException {

    private static final long serialVersionUID = -561743989768735470L;

    private RestClientException restClientException;
    private String body;

    public RestClientException getRestClientException() {
        return restClientException;
    }

    public void setRestClientException(RestClientException restClientException) {
        this.restClientException = restClientException;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    CustomRestClientException(String msg, RestClientException restClientException, String body) {
        super(msg + " " + body);
        this.restClientException = restClientException;
        this.body = body;
    }
}
