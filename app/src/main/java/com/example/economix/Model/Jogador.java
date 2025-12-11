package com.example.economix.Model;
import com.example.economix.API.ProfissaoResponse;

import java.util.ArrayList;

public class Jogador{
	private String nome;
	private double saldo;
	private final double valorInicial;
	private ProfissaoResponse profissao;
	private Casa casa;
	private double gastos;
	private ReservaEmergencia reservaEmergencia;
	private Acoes acoes;
	private ArrayList <EventoNegativo> evNegativo;
	private ArrayList <EventoPositivo> evPositivo;
	
	public Jogador(String nome, ProfissaoResponse profissao, Casa casa) {
		this.nome = nome;
		this.saldo = profissao.getSalariomensal();
		this.valorInicial = profissao.getSalariomensal();
		this.profissao = profissao;
		this.casa = casa;
		this.gastos = 0;
		this.reservaEmergencia = new ReservaEmergencia();
		this.acoes = new Acoes();
		this.evNegativo = new ArrayList<EventoNegativo>();
		this.evPositivo = new ArrayList<EventoPositivo>();
		EventoNegativo geladeiraQuebra = new EventoNegativo("Sua geladeira quebrou, hora de concertar", 500.00);
		EventoNegativo ficarDoente = new EventoNegativo("Você ficou doente e teve que comprar remédios", 200.00);
		EventoNegativo assalto = new EventoNegativo("Você foi assaltado", 950.00);
		evNegativo.add(assalto);
		evNegativo.add(ficarDoente);
		evNegativo.add(geladeiraQuebra);
		EventoPositivo ganhouSorteio = new EventoPositivo("Você ganhou um sorteio", 350.00);
		EventoPositivo gratificacao = new EventoPositivo("Você ganhou uma gratificação no seu trabalho, parabéns!", 250.00);
		EventoPositivo ganhouDinheiro = new EventoPositivo("Alguém da sua família te deu dinheiro.", 100.00);
		evPositivo.add(ganhouSorteio);
		evPositivo.add(gratificacao);
		evPositivo.add(ganhouDinheiro);
	}

	public String getProfissaoNome(){
		return this.profissao.getNome();
	}
	public double getProfissaoSalario(){
		return this.profissao.getSalariomensal();
	}

	public String getCasaNome(){
		return this.casa.getNome();
	}
	public double getCasaAluguel(){
		return this.casa.getAluguel();
	}
	public void receberSalsario() {
		adicionaSaldo(this.profissao.getSalariomensal());
	}
	public void prejuizo() {
		if(getSaldo() < 0.0) 
			System.out.println("Você ficou endividado");
		else
			System.out.println("Você ficou com saldo positivo");
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public void adicionaSaldo(double valor){
		this.saldo += valor;
	}
	public void diminuiSaldo(double valor){
		this.saldo -= valor;
	}
	public double getSaldo() {
		return this.saldo;
	}
	public void setGastos(double valor) {
		this.gastos = gastos + valor;
	}
	public double getGastos() {
		return this.gastos;
	}
	public void pagarAluguel() {
		diminuiSaldo(this.casa.getAluguel());
		setGastos(this.casa.getAluguel());
	}
	public void comprarComida(double valor) {
		diminuiSaldo(valor);
		setGastos(valor);
	}
	public void contaDeLuz(double valor) {
		diminuiSaldo(valor);
		setGastos(valor);
	}
	public void contaDeAgua(double valor) {
		diminuiSaldo(valor);
		setGastos(valor);
	}
	public void contaDoGas(double valor) {
		diminuiSaldo(valor);
		setGastos(valor);
	}
	
	public void lazer(double valor) {
		diminuiSaldo(valor);
		setGastos(valor);
	}
	
	public void reservaDeEmergencia(double valor) {
		this.reservaEmergencia.setQuantia(valor);
		diminuiSaldo(valor);
		setGastos(valor);
	}
	public double proventosReservaEmergencia() {
		return this.reservaEmergencia.calculaInvestimento();
	}
	public void investeAcoes(double valor) {
		this.acoes.setQuantia(valor);
		diminuiSaldo(valor);
		setGastos(valor);
	}
	public double proventosAcoes() {
		return this.acoes.calculaInvestimento();
	}
	public String getCasa() {
		return this.casa.getNome();
	}
	
	public void aplicarEventoNegativo(int indice){
	    evNegativo.get(indice).aplicarEvento(indice, this);
	    setGastos(evNegativo.get(indice).getValorPerda());
	}
	
	public void aplicarEventoPositivo(int indice) {
		evPositivo.get(indice).aplicarEvento(indice, this);
	}
	
	
}
