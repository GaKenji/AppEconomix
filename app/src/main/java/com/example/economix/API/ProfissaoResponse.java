package com.example.economix.API;

public class ProfissaoResponse {
	
	private String nome;
	private double salarioMensal;
	
	public ProfissaoResponse(String nome, double salarioMensal) {
		this.nome = nome;
		this.salarioMensal = salarioMensal;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSalariomensal() {
		return salarioMensal;
	}
	
	public void setSalarioMensal(double salarioMensal) {
		this.salarioMensal = salarioMensal;
	}

}
