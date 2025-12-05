package com.example.economix.DataBase;

public interface IPontuacaoDAO {
    public double buscarRecorde(int usuarioId);
    public long inserirRecorde(int usuarioId, double recorde);
    public void atualizaRecorde(int usuarioId, double novoRecorde);
}
