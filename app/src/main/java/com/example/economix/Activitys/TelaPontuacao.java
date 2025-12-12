package com.example.economix.Activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.economix.R;

public class TelaPontuacao extends AppCompatActivity implements View.OnClickListener{

    private TextView txtMostraPontuacao, txtMostraRecorde;
    private Button btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_pontuacao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtMostraPontuacao = findViewById(R.id.textMostrarPontuacao);
        txtMostraRecorde = findViewById(R.id.textMostrarRecorde);
        btnVoltar = findViewById(R.id.voltarAoMenu);

        double pontuacaoFinal = getIntent().getDoubleExtra("pontuacaoFinal", 0);
        double recorde = getIntent().getDoubleExtra("recorde", 0);

        txtMostraPontuacao.setText(String.format("R$ %.2f", pontuacaoFinal));
        txtMostraRecorde.setText(String.format("R$ %.2f", recorde));
        btnVoltar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.voltarAoMenu){
            finish();
        }
    }
}