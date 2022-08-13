package com.tr.countries.di;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chuckerteam.chucker.api.ChuckerCollector;
import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.tr.countries.localstorage.CountryRepo;
import com.tr.countries.network.CountryService;
import com.tr.countries.retrofit.HttpRetrofitInterceptor;

import java.util.Collections;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
interceptor class
 */
public class MyActivity extends AppCompatActivity {
    private static Retrofit _retrofit;
    private static CountryRepo _countryRepo;
    private static CountryService _countryService;
    public CountryService countryService;
    public CountryRepo countryRepo;

    private final String BASE_URL="https://wft-geo-db.p.rapidapi.com/v1/geo/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getRetrofit();
        getRoomDatabase();
        super.onCreate(savedInstanceState);

    }

    private void getRoomDatabase(){
        if (_countryRepo == null){
           _countryRepo = new CountryRepo(getApplication());
        }
        countryRepo = _countryRepo;
    }

    private void getRetrofit() {
        if (_retrofit == null){
            _retrofit = createRetrofit();
            _countryService = _retrofit.create(CountryService.class);
        }
        countryService=_countryService;
    }

    private Retrofit createRetrofit(){
        HttpRetrofitInterceptor interceptor = new HttpRetrofitInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);


        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new ChuckerInterceptor.Builder(getApplicationContext())
                                .collector(new ChuckerCollector(getApplicationContext()))
                                .maxContentLength(250000L)
                                .redactHeaders(Collections.<String>emptySet())
                                .alwaysReadResponseBody(false)
                                .build()
                )
                .addInterceptor(interceptor)
                .build();


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }
}
