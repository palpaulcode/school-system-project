/*package com.example.demoschool.responses;

import com.example.demoschool.exception.ApiRequestException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class response {
    private String message;
    private int statuscode;
    private ZonedDateTime time;
    private boolean success;
    private Object responseBody;

    *//*public response() {
        this.message = "success_1";
        this.statuscode = 200;
        this.time = ZonedDateTime.now(ZoneId.of(ZoneId.systemDefault().getId()));
        this.success = true;
        this.responseBody=responseBody;
    }*//*

    public response(Object responseBody) {
        this.message = "success_!";
        this.statuscode = 200;
        this.time = ZonedDateTime
                .now(ZoneId.of(ZoneId.systemDefault().getId()));
        this.success = true;
        this.responseBody = responseBody;
    }

    public response(ApiRequestException e) {
        this.message = e.getMessage();
        this.statuscode = 500;
        this.time = ZonedDateTime
                .now(ZoneId.of(ZoneId.systemDefault().getId()));
        this.success = false;
    }

    public String getMessage() {
        return message;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getResponseBody() {
        return responseBody;
    }
}*/
