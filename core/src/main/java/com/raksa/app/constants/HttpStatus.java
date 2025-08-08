package com.raksa.app.constants;

public class HttpStatus {
    public static final String SUCCESSFULLY = String.valueOf(org.springframework.http.HttpStatus.OK);
    public static final String RESOURCE_NOT_FOUND = String.valueOf(org.springframework.http.HttpStatus.NOT_FOUND);
    public static final String DUPLICATE_ENTRY = String.valueOf(org.springframework.http.HttpStatus.CONFLICT);
    public static final String VALIDATION_ERROR = String.valueOf(org.springframework.http.HttpStatus.BAD_REQUEST);
    public static final String INTERNAL_SERVER_ERROR = String.valueOf(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
    public static final String UNAUTHORIZED = String.valueOf(org.springframework.http.HttpStatus.UNAUTHORIZED);
}
