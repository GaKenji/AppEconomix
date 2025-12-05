package com.example.economix.Activitys;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class TelaCadastro extends AppCompatActivity implements View.OnClickListener {
    private Button btCadastro;
    private EditText editNome;
    private EditText editIdade;

    private UsuarioDAO usuarioDAO;
    private PontuacaoDAO pontuacaoDAO;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telacadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        usuarioDAO = new UsuarioDAO(this);
        pontuacaoDAO = new PontuacaoDAO(this);
        Usuario usuario = usuarioDAO.buscarUsuario();

        if(usuario != null){
            startActivity(new Intent(this, Menu.class));
            finish();
            return;
        }
        btCadastro = findViewById(R.id.buttonCadastro);
        editNome = findViewById(R.id.EditNomeCadastro);
        editIdade = findViewById(R.id.EditIdadeCadastro);

        btCadastro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonCadastro){
            cadastrarUsuario();
        }
    }

    private void cadastrarUsuario(){
        String nome = editNome.getText().toString().trim();
        String idade = editIdade.getText().toString().trim();

        if(nome.isEmpty() || idade.isEmpty()){
            Toast.makeText(this, "Preencha os campos para o cadastro!", Toast.LENGTH_SHORT).show();
        }
        else{
            try{
                int idad = Integer.parseInt(idade);
                ContentValues ctv = new ContentValues();
                ctv.put("nome", nome);
                ctv.put("idade", idad);
                long idUsuario = usuarioDAO.inserirUsuario(ctv);

                if(idUsuario > 0){
                    pontuacaoDAO.inserirRecorde((int) idUsuario, 0);
                    Toast.makeText(this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, Menu.class));
                    finish();
                }
                else Toast.makeText(this, "Erro no cadastro!", Toast.LENGTH_SHORT).show();
            }catch (NumberFormatException e){
                Toast.makeText(this, "Idade inválida", Toast.LENGTH_SHORT).show();
            }
        }
    }
}