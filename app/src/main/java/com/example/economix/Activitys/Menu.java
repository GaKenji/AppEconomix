package com.example.economix.Activitys;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.economix.DataBase.PontuacaoDAO;
import com.example.economix.DataBase.UsuarioDAO;
import com.example.economix.R;
import com.example.economix.Util.Usuario;

public class Menu extends AppCompatActivity implements View.OnClickListener{
    private Button jogar;
    private Button sair;
    private Button excluirConta;
    private UsuarioDAO usuarioDAO;
    private PontuacaoDAO pontuacaoDAO;
    private TextView textRecord;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuarioDAO = new UsuarioDAO(this);
        pontuacaoDAO = new PontuacaoDAO(this);
        Usuario usuario = usuarioDAO.buscarUsuario();

        jogar = findViewById(R.id.buttonJogar);
        sair = findViewById(R.id.buttonSair);
        excluirConta = findViewById(R.id.buttonExcluirConta);
        textRecord = findViewById(R.id.textViewRecord);


        if(usuario != null){
            double recorde = pontuacaoDAO.buscarRecorde(usuario.getId());
            textRecord.setText(String.format("%.2f R$", recorde));
        }

        jogar.setOnClickListener(this);
        sair.setOnClickListener(this);
        excluirConta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonJogar){
            Intent intent = new Intent(Menu.this, CriadordePersonagem.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.buttonExcluirConta){
            excluiConta();
        }
        else if(v.getId() == R.id.buttonSair) finishAffinity();
    }

    private void excluiConta(){
        new AlertDialog.Builder(this)
                .setTitle("Excluir conta")
                .setMessage("Quer mesmo excluir sua conta?")
                .setPositiveButton("Sim, excluir", (dialog, which) -> {
                    Usuario usuario = usuarioDAO.buscarUsuario();
                    if(usuario != null) {
                        usuarioDAO.deletarUsuario(usuario.getId());
                        Toast.makeText(this, "Conta excluída com sucesso!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, TelaCadastro.class));
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Usuario usuario = usuarioDAO.buscarUsuario();

        if (usuario != null) {
            double recorde = pontuacaoDAO.buscarRecorde(usuario.getId());
            textRecord.setText(String.format("%.2f R$", recorde));
        }
    }
}