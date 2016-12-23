package kripton.extensions.retrofit;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kripton.mvp.model.HttpStatusCode;
import com.kripton.mvp.model.SimpleHttpResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorFailedException;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by jgodinez on 7/15/2016.
 */
public class RetrofitFactoryService {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_ERROR_MSG = "Unknown error";
    private static final String UNKNOWN_HOST = "Unable to resolve host";
    private static final String SOCKET_TIMEOUT_EXCEPTION = "El canal de solicitud agotó el tiempo de espera de la respuesta.";

    private static final String TAG  = RetrofitFactoryService.class.getName();

    private static String APPLICATION_HOST ;

    public static  void  initialize(String host){
        APPLICATION_HOST = host;
    }

    @NonNull
    private static OkHttpClient getOkHttpClient() {
        // Define the interceptor for logging
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        builder.interceptors().add(loggingInterceptor);

        return builder.build();
    }

    @NonNull
    private static Retrofit getRetrofit() {
        // Define the Gson date formatter
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();

        OkHttpClient client = getOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APPLICATION_HOST)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    public static <T> T createRetrofitService(Class<T> classType) {
        Retrofit retrofit = getRetrofit();
        T service = retrofit.create(classType);

        return service;
    }

    public static <T> T parseError(Class<T> classType, ResponseBody errorBody) {
        Retrofit retrofit = getRetrofit();
        Converter<ResponseBody, T> errorConverter = retrofit
                .responseBodyConverter(classType, new Annotation[0]);

        try {
            T error = errorConverter.convert(errorBody);
            return error;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void getEntityResponseCallback(@NonNull final ILoaderCallback callback,
                                                     final Action<T> cacheData,
                                                     Observable<Response<T>> service) {
        try {
            service.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Response<T>>() {
                        @Override
                        public void call(Response<T> response) {
                            onSucces(response, cacheData, callback);
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            try {
                                onError(throwable, callback);
                            } catch (Throwable e) {
                                onError(new Throwable(SOCKET_TIMEOUT_EXCEPTION), callback);
                                Log.d(TAG, e.getMessage());
                            }
                        }
                    });
        } catch (Exception e) {
            callback.onError(e);
        }
    }

    private static void onError(Throwable throwable, @NonNull ILoaderCallback callback) {
        if(throwable instanceof HttpException) {
            SimpleHttpResponse errorResponse = RetrofitFactoryService.parseError(SimpleHttpResponse.class, ((HttpException) throwable).response().errorBody());
            if(errorResponse != null) {
                callback.onError(new Throwable(errorResponse.mError));
            } else {
                callback.onError(new Throwable(DEFAULT_ERROR_MSG));
            }
        } else if(throwable instanceof UnknownHostException) {
            Connectivity connectivity = new Connectivity();

            if(connectivity.isConnected()) {
                callback.onError(new Throwable(UNKNOWN_HOST));
            } else {
                callback.onError(new Throwable(Connectivity.ACCESS_NETWORK_ERROR));
            }
        } else if(throwable instanceof OnErrorFailedException) {
            Log.d(TAG, "OnErrorFailedException: " + throwable.getMessage());
        } else if(throwable instanceof SocketTimeoutException) {
            Log.d(TAG, "SocketTimeoutException: " + throwable.getMessage());
        } else {
            callback.onError(throwable);
        }
    }

    private static <T> void onSucces(Response<T> response, Action<T> cacheData, @NonNull ILoaderCallback callback) {
        if (response.code() == HttpStatusCode.OK.getValue() ||
                response.code() == HttpStatusCode.CREATED.getValue()) {

            if (cacheData != null) {
                cacheData.setType(response.body());
            }

            callback.onSuccess(response.body());
        } else if (response.code() == HttpStatusCode.NO_CONTENT.getValue()) {
            callback.onError(new SimpleHttpStateEntry(response.message(), HttpStatusCode.NO_CONTENT));
        } else {
            SimpleHttpResponse errorResponse = RetrofitFactoryService.parseError(SimpleHttpResponse.class, response.errorBody());
            if (errorResponse != null) {
                callback.onError(new Throwable(errorResponse.mError));
            } else {
                callback.onError(new SimpleHttpStateEntry(response.message(), response.code()));
            }
        }
    }

}
