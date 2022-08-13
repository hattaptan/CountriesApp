package com.tr.countries.network;

import com.tr.countries.data.Countries;
import com.tr.countries.data.CountryDom;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
/*
service class
 */
public interface CountryService {

    @GET("countries?limit=10")
    Call<Countries> getCountryData();

    @GET("countries/{code}")
    Call<CountryDom> getCountry(@Path("code") String countryCode);

}
