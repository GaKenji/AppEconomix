package com.example.economix.Activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.economix.API.ProfissaoResponse;
import com.example.economix.API.ProfissoesAPI;
import com.example.economix.API.RetrofitClient;
import com.example.economix.R;
import com.example.economix.Util.ProfissaoAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriadordePersonagem extends AppCompatActivity {

    private Spinner spinnerProfissoes;
    private List<ProfissaoResponse> listaProfissoes;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_criadordepersonagens);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinnerProfissoes = findViewById(R.id.SpinnerEscolhaProfissao);
        carregarProfissoes();
        spinnerProfissoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ProfissaoResponse profissaoSelecionada = listaProfissoes.get(position);
                String nome = profissaoSelecionada.getNome();
                double salario = profissaoSelecionada.getSalariomensal();

                Toast.makeText(CriadordePersonagem.this, "Você escolheu: " + nome + " (R$" + salario + ")", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void carregarProfissoes(){
        ProfissoesAPI api = RetrofitClient.getProfissoesAPI();
        api.getProfissoes().enqueue(new Callback<List<ProfissaoResponse>>() {
            @Override
            public void onResponse(Call<List<ProfissaoResponse>> call, Response<List<ProfissaoResponse>> response) {
                if(response.isSuccessful() && response.body() != null){
                    listaProfissoes = response.body();

                    List<String> profissoes = new ArrayList<>();
                    for(ProfissaoResponse p:listaProfissoes){
                        profissoes.add(p.getNome() + "      Salário: " + p.getSalariomensal() + " R$");
                    }

                    ProfissaoAdapter adapter = new ProfissaoAdapter(CriadordePersonagem.this, listaProfissoes);
                    spinnerProfissoes.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<ProfissaoResponse>> call, Throwable t) {
                Toast.makeText(CriadordePersonagem.this, "Erro ao carregar as profissoes!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}