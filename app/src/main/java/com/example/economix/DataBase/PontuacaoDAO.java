package com.example.economix.DataBase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PontuacaoDAO implements IPontuacaoDAO{

    private SQLiteDatabase leitura;
    private SQLiteDatabase escrita;
    private DbHelper dbHelper;

    public PontuacaoDAO (Context context){
        this.dbHelper = new DbHelper(context);
        this.leitura = dbHelper.getReadableDatabase();
        this.escrita = dbHelper.getWritableDatabase();
    }


    @Override
    @SuppressLint("Range")
    public double buscarRecorde(int usuarioId) {
        double recorde = 0.0;
        String sql = "SELECT recorde FROM pontuacao WHERE usuario_id = ? LIMIT 1";
        Cursor cursor = leitura.rawQuery(sql, new String[]{String.valueOf(usuarioId)});

        if(cursor.moveToFirst()){
            recorde = cursor.getDouble(cursor.getColumnIndex("recorde"));
        }
        cursor.close();
        return recorde;
    }

    @Override
    public long inserirRecorde(int usuarioId, double recorde) {
        ContentValues ctv = new ContentValues();
        ctv.put("recorde", recorde);
        ctv.put("usuario_id", usuarioId);

        return escrita.insert("pontuacao", null, ctv);
    }

    @Override
    public void atualizaRecorde(int usuarioId, double novoRecorde) {
        ContentValues cv = new ContentValues();
        cv.put("recorde", novoRecorde);
        String where = "usuario_id=?";
        String[] args = { String.valueOf(usuarioId) };
        escrita.update("pontuacao", cv, where, args);
    }
}
