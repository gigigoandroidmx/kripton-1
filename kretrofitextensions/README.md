===================================
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)

# KRetrofitExtensions

Conjunto de extensiones para el cliente Retrofit type-safe Http para Android

#Configuración y uso

En Application

```java
    @Override
    public void onCreate() {
        super.onCreate();

        // Registro de solicitudes y respuestas Http
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final Connectivity connectivity = new Connectivity(this);
        Interceptor connectivityInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                if (connectivity != null &&
                        connectivity.isConnected()) {
                    Request request = chain.request()
                            .newBuilder()
                            .build();

                    return chain.proceed(request);
                } else {
                    throw new IOException("It looks like your internet connection is off. Please turn it on and try again.");
                }
            }
        };

        ServiceGenerator.init(this)
                .setBaseUrl(BuildConfig.APP_HOST)
                .setLoggingInterceptor(loggingInterceptor)
                .setConnectivityInterceptor(connectivityInterceptor);
    }
```

Configuración de servicio

```java
    @GET(BuildConfig.PATH_CHANNEL)
    Call<ChannelItem> getChannelVideoList(@Query("part") String part,
                             @Query("channelId") String channelId,
                             @Query("order") String order,
                             @Query("maxResults") String maxResults,
                             @Query("key") String key);
```

Consumo del servicio 

 - Se realiza el llamado la método createService de ServiceFactory especificando el tipo de interfaz que generará para su implementación

```java
        ServiceFactory.createService(Class<T> classType)
```

 - Se realiza el llamado al método createCall de ServiceFactory, especificando los parámetros necesarios para su ejecución
 
```java
        ServiceFactory.createCall(Call<T> call,
                                  IKCallbackContract.IViewExtendedCallback callback,
                                  boolean cancelable)
```

 - La implemnetación final en el interactor

```java
        IYoutubeService service = ServiceFactory.createService(IYoutubeService.class);

        ServiceFactory.createCall(service.getChannelVideoList(part, channelId, order, maxResults, key),
                callback,
                false);
```

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
# License

```
Copyright 2016 gigigo México

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

===================================
