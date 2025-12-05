package com.example.economix.Model;
import com.example.economix.API.ProfissaoResponse;

import java.util.ArrayList;
import java.util.Scanner;

public class CriadorDePersonagem {
	private ArrayList <ProfissaoResponse> profissoes = new ArrayList <ProfissaoResponse> ();
	private ArrayList <Casa> casas = new ArrayList <Casa> ();
	
	public CriadorDePersonagem() {
		ProfissaoResponse atendente = new ProfissaoResponse("Atendente", 2000.00);
		ProfissaoResponse enfermeiro = new ProfissaoResponse("Enfermeiro", 6000.00);
		ProfissaoResponse programador = new ProfissaoResponse("Programador", 10000.00);
		Casa kitnet = new Casa("Kitnet", 700.00);
		Casa casa = new Casa("Casa", 1500.00);
		Casa mansao = new Casa("Mansão", 4000.00);
		profissoes.add(atendente);
		profissoes.add(enfermeiro);
		profissoes.add(programador);
		casas.add(kitnet);
		casas.add(casa);
		casas.add(mansao);
	}
	
	public Jogador criarJogador() {
        Scanner in = new Scanner(System.in);

        System.out.println("Digite o nome do seu personagem:");
        String nome = in.nextLine();

        System.out.println("\nEscolha sua profissão:");
        for (int i = 0; i < profissoes.size(); i++) {
            System.out.println((i + 1) + "- " + profissoes.get(i).getNome() +
                    " (R$ " + profissoes.get(i).getSalariomensal() + ")");
        }
        int escolhaProf = in.nextInt() - 1;
        ProfissaoResponse profEscolhida = profissoes.get(escolhaProf);

        System.out.println("\nEscolha sua casa:");
        for (int i = 0; i < casas.size(); i++) {
            System.out.println((i + 1) + "- " + casas.get(i).getNome() +
                    " (R$ " + casas.get(i).getAluguel() + ")");
        }
        int escolhaCasa = in.nextInt() - 1;
        Casa casaEscolhida = casas.get(escolhaCasa);

        return new Jogador(nome, profEscolhida, casaEscolhida);
    }
	
}
