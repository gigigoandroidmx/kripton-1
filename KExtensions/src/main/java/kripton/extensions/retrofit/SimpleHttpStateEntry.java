package kripton.extensions.retrofit;

import com.kripton.mvp.model.HttpStatusCode;

/**
 * Created by Daniel on 06/09/2016.
 */
public class SimpleHttpStateEntry
        extends Throwable {

    private HttpStatusCode mCode;

    public SimpleHttpStateEntry(String message, int statusCode) {
        this(message, HttpStatusCode.tryGetHttpStatusCode(statusCode));
    }

    public SimpleHttpStateEntry(String message, HttpStatusCode statusCode) {
        super(message);
        mCode = statusCode;
    }

    public HttpStatusCode getHttpStatusCode() {
        return mCode;
    }
}
