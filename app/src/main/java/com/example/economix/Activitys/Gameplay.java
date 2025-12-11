package com.example.economix.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.economix.API.ProfissaoResponse;
import com.example.economix.Model.Casa;
import com.example.economix.Model.Jogador;
import com.example.economix.R;

import java.util.Random;

public class Gameplay extends AppCompatActivity {

    private TextView titulo;
    private TextView texto;
    private double pontuacao = 0.0;
    private int mesAtual = 0;
    private double luz, agua, gas;
    Random rand = new Random();
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
        //Pegando os dados da tela de criação de personagem
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nome");
        String profissaoNome = intent.getStringExtra("profissaoNome");
        double profissaoSalario = intent.getDoubleExtra("profissaoSalario", 0);
        String casaNome = intent.getStringExtra("casaNome");
        double casaAluguel = intent.getDoubleExtra("casaAluguel", 0);

        //Criação do jogador
        ProfissaoResponse profissao = new ProfissaoResponse(profissaoNome, profissaoSalario);
        Casa casa = new Casa(casaNome, casaAluguel);
        Jogador jogador = new Jogador(nome, profissao, casa);

        titulo = findViewById(R.id.textViewTituloGameplay);
        texto = findViewById(R.id.textViewTextoGameplay);

        apresentarJogador(jogador);
        virarMeses(jogador);
    }
    private void apresentarJogador(Jogador jogador){
        titulo.setText("Olá, " + jogador.getNome());
        texto.setText("Sua profissão: " +jogador.getProfissaoNome()+ "\n"
                +"Salário: " + jogador.getProfissaoSalario() + "\n"
                +"Sua casa: " + jogador.getCasaNome()+ "\n"
                +"Aluguel: "+jogador.getCasaAluguel());
    }
    private void virarMeses(Jogador jogador){
        if(mesAtual >= 12){
            //Aqui vaontrar a activity de pontuação
        }
        if(mesAtual > 0){
            jogador.receberSalsario();
        }
        gerarContasFixas(jogador);
        pagarContasFixas(jogador);
        new Handler().postDelayed(() -> {
            notificarContasFixas(jogador);
        },5000);
        mesAtual++;
    }
    private void gerarContasFixas(Jogador jogador){
        switch (jogador.getCasa()){
            case "Mansão":
                luz = rand.nextDouble()*1000.00 + 600.00;
                agua = rand.nextDouble()*300.00 + 150.00;
                gas = rand.nextDouble()*200.00 + 100.00;
                break;
            case "Casa":
                luz = rand.nextDouble()*450.00 + 200.00;
                agua = rand.nextDouble()*150.00 + 50.00;
                gas = rand.nextDouble()*50.00 + 30.00;
                break;
            case "Kitnet":
                luz = rand.nextDouble()*200.00 + 80.00;
                agua = rand.nextDouble()*80.00 + 30.00;
                gas = rand.nextDouble()*40.00 + 10.00;
                break;
        }
    }
    private void pagarContasFixas(Jogador jogador){
        jogador.pagarAluguel();
        jogador.contaDeLuz(luz);
        jogador.contaDeAgua(agua);
        jogador.contaDoGas(gas);
    }
    private void notificarContasFixas(Jogador jogador) {
        titulo.setText("Suas contas fixas foram pagas");
        String textoFormatado =
                "Aluguel: " + String.format("%.2f", jogador.getCasaAluguel()) + "\n" +
                        "Conta de luz: " + String.format("%.2f", luz) + "\n" +
                        "Conta de água: " + String.format("%.2f", agua) + "\n" +
                        "Conta de gás: " + String.format("%.2f", gas) + "\n\n" +
                        "Seu saldo em conta: " + String.format("%.2f", jogador.getSaldo());

        texto.setText(textoFormatado);
    }
}