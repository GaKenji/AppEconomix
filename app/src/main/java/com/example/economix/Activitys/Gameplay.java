package com.example.economix.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.economix.API.ProfissaoResponse;
import com.example.economix.Model.Casa;
import com.example.economix.Model.Jogador;
import com.example.economix.R;

public class Gameplay extends AppCompatActivity {

    private TextView txtJogador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gameplay);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        String profissaoNome = intent.getStringExtra("profissaoNome");
        double profissaoSalario = intent.getDoubleExtra("profissaoSalario", 0);
        ProfissaoResponse profissao = new ProfissaoResponse(profissaoNome, profissaoSalario);
        String casaNome = intent.getStringExtra("casaNome");
        double casaAluguel = intent.getDoubleExtra("casaAluguel", 0);
        Casa casa = new Casa(casaNome, casaAluguel);
        Jogador jogador = new Jogador(nome, profissao, casa);

        txtJogador = findViewById(R.id.textViewPersonagemGamePlay);
        txtJogador.setText("Nome: " + jogador.getNome() + "\n" + "Profissao: " + profissaoNome + profissaoSalario + casaNome + " " + casaAluguel);
    }
}