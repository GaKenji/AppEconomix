package com.example.economix.Activitys;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.economix.API.ProfissaoResponse;
import com.example.economix.Model.Casa;
import com.example.economix.Model.EventoNegativo;
import com.example.economix.Model.EventoPositivo;
import com.example.economix.Model.Jogador;
import com.example.economix.R;

import java.util.Random;

public class Gameplay extends AppCompatActivity implements View.OnClickListener {

    private Jogador jogador;
    private TextView titulo;
    private TextView texto;
    private TextView despeza;
    private TextView tituloSaldo;
    private TextView txtMostrarSaldo;
    private EditText tipoDespeza;
    private Button btnConfimrar, btnAvancaMes;
    private double pontuacao = 0.0;
    private int mesAtual = 0;
    private double luz, agua, gas;
    private int controle = 0;
    private Random rand = new Random();
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
        jogador = new Jogador(nome, profissao, casa);

        //link dos ids
        titulo = findViewById(R.id.textViewTituloGameplay);
        texto = findViewById(R.id.textViewTextoGameplay);
        despeza = findViewById(R.id.textViewDespeza);
        tituloSaldo = findViewById(R.id.textViewTituloSaldo);
        txtMostrarSaldo = findViewById(R.id.TextViewMostrarSaldo);
        tipoDespeza = findViewById(R.id.EditMostrarTipoDespeza);
        btnConfimrar = findViewById(R.id.buttonConfirmaInvestimento);
        btnAvancaMes = findViewById(R.id.buttonAvancaMes);

        btnConfimrar.setVisibility(View.INVISIBLE);
        btnAvancaMes.setVisibility(View.INVISIBLE);
        despeza.setVisibility(View.INVISIBLE);
        tipoDespeza.setVisibility(View.INVISIBLE);
        apresentarJogador();
        virarMeses();
    }
    private void virarMeses(){
        btnAvancaMes.setVisibility(View.INVISIBLE);
        if(mesAtual >= 12){
            //Aqui vaontrar a activity de pontuação
        }
        if(mesAtual < 12){
            this.jogador.receberSalsario();
        }
        gerarContasFixas();
        pagarContasFixas();
        new Handler().postDelayed(() -> {
            notificarContasFixas();
            mostrarOrcamentos();
        },5000);
        mesAtual++;
    }
    private void apresentarJogador(){
        titulo.setText("Olá, " + this.jogador.getNome());
        texto.setText("Sua profissão: " +this.jogador.getProfissaoNome()+ "\n"
                +"Salário: " + this.jogador.getProfissaoSalario() + "\n"
                +"Sua casa: " + this.jogador.getCasaNome()+ "\n"
                +"Aluguel: "+ this.jogador.getCasaAluguel());
    }
    private void gerarContasFixas(){
        switch (this.jogador.getCasaNome()){
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
    private void pagarContasFixas(){
        this.jogador.pagarAluguel();
        this.jogador.contaDeLuz(luz);
        this.jogador.contaDeAgua(agua);
        this.jogador.contaDoGas(gas);
    }
    private void notificarContasFixas() {
        titulo.setText("Suas contas fixas foram pagas");
        String textoFormatado =
                "Aluguel: " + String.format("%.2f", this.jogador.getCasaAluguel()) + "\n" +
                        "Conta de luz: " + String.format("%.2f", luz) + "\n" +
                        "Conta de água: " + String.format("%.2f", agua) + "\n" +
                        "Conta de gás: " + String.format("%.2f", gas);

        texto.setText(textoFormatado);
        mostrarSaldo();
    }
    private void mostrarOrcamentos(){
        despeza.setVisibility(View.VISIBLE);
        tipoDespeza.setVisibility(View.VISIBLE);
        btnConfimrar.setVisibility(View.VISIBLE);
        exibirOrcamentoAtual();
        btnConfimrar.setOnClickListener(this);
    }
    private void mostrarSaldo(){
        tituloSaldo.setText("Saldo disponível:");
        txtMostrarSaldo.setText(String.format("R$ %.2f", this.jogador.getSaldo()));
    }
    private void exibirOrcamentoAtual(){
        switch (controle){
            case 0:
                despeza.setText("Orçamento para alimentação: ");
                break;
            case 1:
                despeza.setText("Orçamento para lazer: ");
                break;
            case 2:
                despeza.setText("Reserva de emergência: ");
                break;
            case 3:
                despeza.setText("Investimento em ações: ");
                break;
        }
        tipoDespeza.setText("");
    }
    private void processarOrcamento(){
        String orcamento = tipoDespeza.getText().toString().trim();
        if(orcamento.isEmpty()){
            return;
        }
        double valor = Double.parseDouble(orcamento);
        switch (controle){
            case 0:
                jogador.comprarComida(valor);
                break;
            case 1:
                jogador.lazer(valor);
                break;
            case 2:
                jogador.reservaDeEmergencia(valor);
                break;
            case 3:
                jogador.investeAcoes(valor);
                break;
        }
        mostrarSaldo();
        controle++;
        if(controle <= 3){
            exibirOrcamentoAtual();
        } else {
            finalizarEtapasDeOrcamento();
        }
    }
    private void finalizarEtapasDeOrcamento(){
        tipoDespeza.setVisibility(View.INVISIBLE);
        btnConfimrar.setVisibility(View.INVISIBLE);
        btnAvancaMes.setVisibility(View.VISIBLE);
        String[] evento = gerarEventoAleatorio(); // pega infos do evento

        if(evento != null){
            mostrarDialogEvento(evento[0], evento[1], evento[2]);
        }
        jogador.adicionaSaldo(jogador.proventosReservaEmergencia());
        jogador.adicionaSaldo(jogador.proventosAcoes());
        // atualizar saldo na tela
        mostrarSaldo();
        // calcular prejuízo
        jogador.prejuizo();

        // somar à pontuação total
        pontuacao += jogador.getSaldo();
        mostrarDialogResumoMensal();
        btnAvancaMes.setOnClickListener(v -> {
            // aqui chama o próximo mês
            controle = 0;
            virarMeses();
        });
    }
    private String[] gerarEventoAleatorio() {
        int prc = rand.nextInt(100) + 1;
        String descricao, valorStr, tipo;

        if (prc <= 10){
            EventoNegativo ev = jogador.getEvNegativo().get(0);
            jogador.aplicarEventoNegativo(0);
            descricao = ev.getDescricao();
            valorStr = String.format("- R$ %.2f", ev.getValorPerda());
            tipo = "negativo";
        }
        else if (prc <= 20){
            EventoNegativo ev = jogador.getEvNegativo().get(1);
            jogador.aplicarEventoNegativo(1);
            descricao = ev.getDescricao();
            valorStr = String.format("- R$ %.2f", ev.getValorPerda());
            tipo = "negativo";
        }
        else if (prc <= 30) {
            EventoNegativo ev = jogador.getEvNegativo().get(2);
            jogador.aplicarEventoNegativo(2);
            descricao = ev.getDescricao();
            valorStr = String.format("- R$ %.2f", ev.getValorPerda());
            tipo = "negativo";

        }
        else if (prc <= 40) {
            EventoPositivo ev = jogador.getEvPositivo().get(0);
            jogador.aplicarEventoPositivo(0);
            descricao = ev.getDescricao();
            valorStr = String.format("+ R$ %.2f", ev.getValorGanho());
            tipo = "positivo";

        }
        else if (prc <= 50) {
            EventoPositivo ev = jogador.getEvPositivo().get(1);
            jogador.aplicarEventoPositivo(1);
            descricao = ev.getDescricao();
            valorStr = String.format("+ R$ %.2f", ev.getValorGanho());
            tipo = "positivo";

        }
        else if (prc <= 60) {
            EventoPositivo ev = jogador.getEvPositivo().get(2);
            jogador.aplicarEventoPositivo(2);
            descricao = ev.getDescricao();
            valorStr = String.format("+ R$ %.2f", ev.getValorGanho());
            tipo = "positivo";

        }
        else {
            return null; // nenhum evento ocorreu
        }
        // retorna descrição, valor alterado e o tipo do evento
        return new String[]{descricao, valorStr, tipo};
    }

    private void mostrarDialogEvento(String descricao, String valor, String tipo){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String titulo = tipo.equals("positivo")
                ? "🎉 Evento Positivo!"
                : "⚠️ Evento Negativo!";

        builder.setTitle(titulo);
        builder.setMessage(descricao + "\n\n" + "Impacto no saldo: " + valor);
        builder.setPositiveButton("OK", (dialog, which) -> {
            mostrarSaldo();   // atualiza saldo na tela
            dialog.dismiss();
        });
        builder.setCancelable(false);
        builder.show();
    }
    private void mostrarDialogResumoMensal() {
        String msg = "Resumo do mês:\n\n" +
                "Saldo atual: R$ " + String.format("%.2f", jogador.getSaldo()) + "\n" +
                "Pontuação acumulada: " + String.format("%.2f", pontuacao);

        new AlertDialog.Builder(this)
                .setTitle("Fim do mês " + mesAtual)
                .setMessage(msg)
                .setPositiveButton("Continuar", (d, w) -> d.dismiss())
                .show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonConfirmaInvestimento){
            processarOrcamento();
        }
    }
}