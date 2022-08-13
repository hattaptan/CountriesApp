package com.tr.countries.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
/*
retrofit class
 */
public class HttpRetrofitInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request requestWithUrl = request.newBuilder()
                .url(request.url().toString())
                .build();
        return chain.proceed(createRequest(requestWithUrl));
    }

    private Request createRequest(Request request){
        Buffer buffer = new Buffer();
        try {
            if (request.body() != null){
                request.body().writeTo(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request.newBuilder()//given api key
                .header("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com")
                .header("X-RapidAPI-Key", "6ca3fe976amsha9f248ebbf466c0p1f8b77jsn428133ae8f64")
                .method(request.method(),request.body())
                .build();
    }
}