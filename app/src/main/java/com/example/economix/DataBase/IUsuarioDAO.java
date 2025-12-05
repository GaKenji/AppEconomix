package com.example.economix.DataBase;

import android.content.ContentValues;

import com.example.economix.Util.Usuario;

public interface IUsuarioDAO {

    public long inserirUsuario(ContentValues ctv);
    public void deletarUsuario(int id);
    public Usuario buscarUsuario();
}
