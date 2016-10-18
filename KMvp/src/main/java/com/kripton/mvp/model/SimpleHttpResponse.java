package com.kripton.mvp.model;


import java.io.Serializable;

/**
 * Created by jgodinez on 8/5/2016.
 */
public class SimpleHttpResponse
        implements Serializable {
    public int mStatus = HttpStatusCode.OK.getValue();

    public String mError;

    public String mMessage;

    public boolean isSucces() {
        return (mStatus == HttpStatusCode.OK.getValue()
                || mStatus == HttpStatusCode.CREATED.getValue());
    }
}