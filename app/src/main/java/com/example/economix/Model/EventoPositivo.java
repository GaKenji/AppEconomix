package com.example.economix.Model;

public class EventoPositivo extends Evento{
	private double valorGanho;
	
	public EventoPositivo(String descricao, double valorGanho) {
		super(descricao);
		this.valorGanho = valorGanho;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void aplicarEvento(int indice, Jogador jogador) {
		 jogador.setSaldo(jogador.getSaldo() + valorGanho);
	     System.out.println("Evento positivo ocorrido: " + descricao + ". Ganhou R$" + valorGanho);
	}
	
	public double getValorGanho() {
		return this.valorGanho;
	}
	
	public String evento() {
		return "Descrição: "+descricao+"\n"+"Valor do ganho: "+valorGanho;
	}
}
