package com.example.economix.Model;

public class EventoNegativo extends Evento{
	private double valorPerda;
	
	public EventoNegativo(String descricao, double valorPerda) {
		super(descricao);
		this.valorPerda = valorPerda;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void aplicarEvento(int indice, Jogador jogador) {
		jogador.setSaldo(jogador.getSaldo() - valorPerda);
	    System.out.println("Evento negativo ocorrido: " + descricao + ". Perda de R$" + valorPerda);
	}
	
	public double getValorPerda() {
		return this.valorPerda;
	}
	
	public String evento() {
		return "Descrição: "+descricao+"  "+"Valor da perda: "+valorPerda;
	}
}
