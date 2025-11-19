package com.example.economix.Model;

public abstract class Evento {
	protected String descricao;

    public Evento(String descricao) {
        this.descricao = descricao;
    }

    public abstract void aplicarEvento(int indice, Jogador jogador);

    public String getDescricao() {
        return descricao;
    }

}
