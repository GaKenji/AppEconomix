package com.example.economix.Model;

public class Acoes implements Investimento{
	private double quantia;
	
	public Acoes() {
		this.quantia = 0.0;
	}
	@Override
	public double calculaInvestimento() {
		return getQuantia() * (Math.random() * 0.1 - 0.05);//Rende entre -5% e 5% ao mes. Simulando um ativo de renda variável
		//Ativos de renda variávl podem ser mais rentáveis, porém há mais riscos.
	}
	public void setQuantia(double valor) {
		this.quantia = getQuantia() + valor;
	}
	
	public double getQuantia() {
		return this.quantia;
	}
 
}
