package com.example.rpp2.retrofit;

import com.example.rpp2.Technology;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonRequest {
    @GET("data/techs.ruleset.json")
    Call<List<Technology>> getTechologys();
}
