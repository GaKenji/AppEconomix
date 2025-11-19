package com.example.economix.Model;

public class ReservaEmergencia implements Investimento {
	private double quantia;
	
	public ReservaEmergencia() {
		this.quantia = 0.0;
	}
	@Override
	public double calculaInvestimento() {
		return getQuantia() * 0.01;//Rende 1% ao mês, simulando um ativo de renda fixa
	}
	
	public void setQuantia(double valor){
		this.quantia = getQuantia() + valor;
	}
	
	public double getQuantia() {
		return this.quantia;
	}
	
	
}
