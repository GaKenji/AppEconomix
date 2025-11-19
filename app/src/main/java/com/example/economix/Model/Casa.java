package com.example.economix.Model;

public class Casa {
	
	private String nome;
	private double aluguel;
	
	public Casa(String nome, double aluguel) {
		this.nome = nome;
		this.aluguel = aluguel;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getAluguel() {
		return aluguel;
	}
	
	public void setAluguel(double aluguel) {
		this.aluguel = aluguel;
	}
	

}
