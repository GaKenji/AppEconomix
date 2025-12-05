package com.example.economix.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.economix.Util.Usuario;

public class UsuarioDAO implements IUsuarioDAO{

    private SQLiteDatabase leitura;
    private SQLiteDatabase escrita;
    private DbHelper dbHelper;

    public UsuarioDAO(Context context){
        this.dbHelper = new DbHelper(context);
        this.leitura = dbHelper.getReadableDatabase();
        this.escrita = dbHelper.getWritableDatabase();
    }

    @Override
    public long inserirUsuario(ContentValues ctv) {
        return this.escrita.insert("usuario", null, ctv);
    }

    @Override
    public void deletarUsuario(int id) {
        String where = "id=?";
        String [] whereArgs = new String[]{String.valueOf(id)};
        this.escrita.delete("usuario", where, whereArgs);
    }

    @Override
    @SuppressLint("Range")
    public Usuario buscarUsuario() {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario LIMIT 1";
        Cursor cursor = leitura.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            usuario.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));
        }
        cursor.close();
        return usuario;
    }
}
