package com.example.economix.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jogoEconomix";
    private static final int DATABASE_VERSION = 1;

    private final String CREATE_TABLE_USUARIO = "CREATE TABLE usuario("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            "nome TEXT NOT NULL,"+
            "idade INTEGER NOT NULL" +
            ");";

    private final String CREATE_TABLE_PONTUACAO =
            "CREATE TABLE pontuacao (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "recorde REAL NOT NULL, " +
                    "usuario_id INTEGER NOT NULL, " +
                    "FOREIGN KEY(usuario_id) REFERENCES usuario(id) ON DELETE CASCADE" +
                    ");";


    public DbHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USUARIO);
        db.execSQL(CREATE_TABLE_PONTUACAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS pontuacao");
        onCreate(db);
    }

    public void onConfigure(SQLiteDatabase db){
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
