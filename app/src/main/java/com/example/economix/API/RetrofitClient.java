package com.example.economix.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String urlBase = "https://raw.githubusercontent.com/GaKenji/APIProfissoes/main/";
    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().
                    baseUrl(urlBase).
                    addConverterFactory(GsonConverterFactory.create()).
                    build();
        }
        return retrofit;
    }

    public static ProfissoesAPI getProfissoesAPI(){
        return getRetrofit().create(ProfissoesAPI.class);
    }
}
