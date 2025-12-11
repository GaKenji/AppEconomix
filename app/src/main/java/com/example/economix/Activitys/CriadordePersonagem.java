package com.example.economix.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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
import com.example.economix.Model.Casa;
import com.example.economix.Model.Jogador;
import com.example.economix.R;
import com.example.economix.Util.ProfissaoAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriadordePersonagem extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinnerProfissoes;
    private List<ProfissaoResponse> listaProfissoes;
    private ArrayAdapter<String> adapter;
    private RadioGroup radioEscolhaCasa;
    private EditText editNomePersonagem;
    private Button btnConfirmar;
    private Casa casa;
    private ProfissaoResponse profissao;

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
        radioEscolhaCasa = findViewById(R.id.radioGroupCasa);
        editNomePersonagem = findViewById(R.id.editNomePersonagem);
        btnConfirmar = findViewById(R.id.buttonConfirmarCriacaoPersonagem);
        carregarProfissoes();
        clickSpinner();
        escolhaCasa();
        btnConfirmar.setOnClickListener(this);
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

    private void clickSpinner(){
        spinnerProfissoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                profissao = listaProfissoes.get(position);
                String nome = profissao.getNome();
                double salario = profissao.getSalariomensal();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void escolhaCasa(){
        radioEscolhaCasa.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.radioButtonKitnet){
                casa = new Casa("Kitnet", 700.00);
            }
            else if (checkedId == R.id.radioButtonCasa) {
                casa = new Casa("Casa", 1500.00);
            }
            else if(checkedId == R.id.radioButtonMansao){
                casa = new Casa("Mansão", 4000.00);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonConfirmarCriacaoPersonagem){
            String nome = editNomePersonagem.getText().toString().trim();
            if(nome.isEmpty()){
                Toast.makeText(this, "Digite um nome para o seu personagem!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(profissao == null || casa == null){
                Toast.makeText(this, "Escolha uma profissão e uma casa!", Toast.LENGTH_SHORT).show();
                return;
            }

            Jogador jogador = new Jogador(nome, profissao, casa);
            Intent intent = new Intent(this, Gameplay.class);
            intent.putExtra("nome", nome);
            intent.putExtra("profissaoNome", profissao.getNome());
            intent.putExtra("profissaoSalario", profissao.getSalariomensal());
            intent.putExtra("casaNome", casa.getNome());
            intent.putExtra("casaAluguel", casa.getAluguel());
            startActivity(intent);
            finish();
        }
    }
}