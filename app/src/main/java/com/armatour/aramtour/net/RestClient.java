package com.armatour.aramtour.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String BASE_URL = "https://google.com";

    public static final RestClient restClient;

    public ArmatourService service;

    static {
        restClient = new RestClient();
    }

    private RestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ArmatourService.class);
    }
}
