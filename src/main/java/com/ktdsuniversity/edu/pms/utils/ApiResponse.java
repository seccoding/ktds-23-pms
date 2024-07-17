package com.ktdsuniversity.edu.pms.utils;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private int status;
    private String statusMessage;
    private int pages;
    private boolean next;
    private Object errors;
    private int count;
    private Object body;

    public static ApiResponse Ok(Object body) {
        ApiResponse response = new ApiResponse();
        response.status = HttpStatus.OK.value();
        response.statusMessage = HttpStatus.OK.getReasonPhrase();
        response.body = body;
        response.count = (body == null) ? 0 : 1;
        return response;
    }

    public static ApiResponse Ok(Object body, int count) {
        ApiResponse response = Ok(body);
        response.count = count;
        return response;
    }

    public static ApiResponse Ok(Object body, int count, int pages, boolean next) {
        ApiResponse response = Ok(body, count);
        response.pages = pages;
        response.next = next;
        return response;
    }

    public static ApiResponse Error(Object errorMessage) {
        ApiResponse response = new ApiResponse();
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        response.statusMessage = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        response.errors = errorMessage;
        return response;
    }

    public static ApiResponse Error(Object errorMessage, HttpStatus status) {
        ApiResponse response = new ApiResponse();
        response.status = status.value();
        response.statusMessage = status.getReasonPhrase();
        response.errors = errorMessage;
        return response;
    }

    public static ApiResponse FORBIDDEN(String errorMessage) {
        return Error(errorMessage, HttpStatus.FORBIDDEN);
    }

    public static ApiResponse BAD_REQUEST(List<String> errorMessage) {
        return Error(errorMessage, HttpStatus.BAD_REQUEST);
    }

    public static ApiResponse BAD_REQUEST(Object errorMessage) {
        return Error(errorMessage, HttpStatus.BAD_REQUEST);
    }

    public static ApiResponse NOT_FOUND(String errorMessage) {
        return Error(errorMessage, HttpStatus.NOT_FOUND);
    }

    public ApiResponse() {
    }

    public ApiResponse(HttpStatus status) {
        this.status = status.value();
        this.statusMessage = status.getReasonPhrase();
    }

    public ApiResponse setErrors(Object errors) {
        this.errors = errors;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public int getPages() {
        return pages;
    }

    public boolean isNext() {
        return next;
    }

    public Object getErrors() {
        return errors;
    }

    public int getCount() {
        return count;
    }

    public Object getBody() {
        return body;
    }
}
