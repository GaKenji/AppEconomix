package com.example.economix.API;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProfissoesAPI {
    @GET("Profissao.JSON")
    Call<List<ProfissaoResponse>> getProfissoes();
}
