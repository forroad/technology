package com.ycjw.technology.exception;

import com.ycjw.technology.model.Response;

public class ExceptionZyc extends Exception {
    private Response response;

    public ExceptionZyc() {
    }

    public ExceptionZyc(Response response) {
        this.response = response;
        this.response.setMessage("error");
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}