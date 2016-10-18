package com.kripton.mvp.model;

/**
 * Created by jgodinez on 9/6/2016.
 */
public enum HttpStatusCode {
    OK(200),
    CREATED(201),
    NO_CONTENT(204),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    private int mValue;

    HttpStatusCode(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }

    public static HttpStatusCode tryGetHttpStatusCode(int statusCode) {
        HttpStatusCode httpStatusCode = OK;

        try {
            for (HttpStatusCode code : HttpStatusCode.values()) {
                if(code.getValue() == statusCode) {
                    httpStatusCode = code;
                    break;
                }
            }
        } catch (Exception e) {}

        return httpStatusCode;
    }
}
