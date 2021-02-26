package com.demo.weatherexercisenikhilbastikar.core.apis;

import com.demo.weatherexercisenikhilbastikar.model.CityWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService
{
    @GET("data/2.5/forecast/")
    Call<CityWeather> getCityWeatherData(@Query("q") String city, @Query("appid") String api_key);
}