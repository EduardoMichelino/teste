package com.br.altran.jokenpo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.br.altran.jokenpo.model.JogadorModel;
import com.br.altran.jokenpo.service.IJokenpoService;

@Service
public class JokenpoService implements IJokenpoService{

	List<JogadorModel> jogadores = new ArrayList<JogadorModel>();
	String[] jogadasPoss = {"pedra","papel","tesoura","spock","lagarto"};
	Integer qtdjogadores = 0;
	
	@Override
	public String instrucoes() {
		StringBuilder sb = new StringBuilder();
		sb.append("Seja bem vindo ao Jokenpo\n\n");
		sb.append("*****Instruções*****\n\n");
		sb.append("Para inserir um jogador e uma jogada, basta acessar a url exemplo: http://localhost:8080/addPlayer/jogador1/jogada \n");
		sb.append("Onde, \"jogador1\" pode ser mudado para o nome que desejar e a \"jogada\" deverá ser uma das seguintes alternativas: \n");
		sb.append("\"pedra\",\"papel\",\"tesoura\",\"spock\",\"lagarto\"\n\n");
		sb.append("Poderá ser inserido quantos jogadores e jogadas quiser, ao menos dois jogadores são necessários.\n\n");
		sb.append("Após inserir os jogadores, deve-se acessar a url: http://localhost:8080/jogar para que o jogo seja executado e o ganhador seja exibido.\n");
		sb.append("O jogo definirá o ganhador dos dois primeiros jogadores, e após a definição do ganhador, se houver mais jogadores haverá a disputa ");
		sb.append("entre o próximo jogador e o vencedor sucessivamente.\n\n");
		sb.append("Para reiniciar os jogador/jogada a qualquer momento antes da execução do jogo a url http://localhost:8080/novoJogo poderá ser executada.\n\n");
		sb.append("Para consultar os jogador/jogada a qualquer momento antes da execução do jogo a url http://localhost:8080/consultaJogadores poderá ser executada.\n\n");
		sb.append("Boa sorte!");
		return sb.toString();
	}
	
	@Override
	public String addPlayer(String pJogador, String pJogada) {
		Boolean jogadaOk = false;
		if (pJogador == null) return "Insira o nome do jogador"; 
		for(int i = 0 ; i < jogadasPoss.length; i++) {
			if(jogadasPoss[i].contains(pJogada)) {
				jogadaOk = true;
			}
		}
		if (!jogadaOk) return "Favor inserir uma das jogadas possíveis" ; 
		JogadorModel jogador = new JogadorModel();
		jogador.setNome(pJogador);
		jogador.setJogada(pJogada);
		jogadores.add(jogador);
		
		System.out.println("Jogador adicionado: " + jogador.getNome());
		System.out.println("Jogada adicionada: " + jogador.getJogada());
		
		return jogadores.toString();
	}

	@Override
	public String jogar() {
		if(jogadores.size() <2) return "É necessário que tenha ao menos dois jogadores!";
		String resultado = "";
		List<JogadorModel> vencedor = executaJogadores(jogadores);
		
		if(vencedor.size()>1) {
			resultado = "Resultado: Ouve empate entre " + vencedor.toString();
		}else {
			resultado = "Resultado: " + vencedor.get(0).getNome() + " venceu!";
		}
		jogadores.clear();
		return resultado;
	}

	@Override
	public String novoJogo() {
		jogadores.clear();
		System.out.println(jogadores.size());
		return "Um novo jogo pode ser iniciado!";
	}

	@Override
	public String consultaJogadores() {
		String retorno = jogadores.toString() + " \nQuantidade de Jogadores: " + jogadores.size();
		return retorno; 
	}
	
	private List<JogadorModel> executaJogadores(List<JogadorModel> jogadores ) {
		JogadorModel jogador1 = new JogadorModel();
		JogadorModel jogador2 = new JogadorModel();
		List<JogadorModel> empate = new ArrayList<JogadorModel>();
		List<JogadorModel> vencedores = new ArrayList<JogadorModel>();
		
		int contador = 0;
		for(JogadorModel jogador : jogadores) {
			if(jogador1.getJogada() == null || jogador1.getNome() == null) {
				jogador1= jogador;
				if(contador == 0) continue;
			}
			if(jogador2.getJogada() == null || jogador2.getNome() == null) {
				jogador2 = jogador;
			}
			
			System.out.println("p1 " + jogador1);
			System.out.println("p2 " + jogador2);
			regra(jogador1,jogador2);
			if(jogador1.getStatus() == "empate" && jogador2.getStatus() == "empate") {
				JogadorModel jogadorEmapate1 = new JogadorModel();
				JogadorModel jogadorEmapate2 = new JogadorModel();
				jogadorEmapate1.setNome(jogador1.getNome());
				jogadorEmapate1.setJogada(jogador1.getJogada());
				jogadorEmapate1.setStatus(jogador1.getStatus());
				jogadorEmapate2.setNome(jogador2.getNome());
				jogadorEmapate2.setJogada(jogador2.getJogada());
				jogadorEmapate2.setStatus(jogador2.getStatus());
				empate.add(jogadorEmapate1);
				empate.add(jogadorEmapate2);
				jogador1.setNome(null);
				jogador1.setJogada(null);
				jogador1.setStatus(null);
				jogador2.setNome(null);
				jogador2.setJogada(null);
				jogador2.setStatus(null);
			}else if(jogador1.getStatus() == "venceu") {
				jogador2.setNome(null);
				jogador2.setJogada(null);
				jogador2.setStatus(null);
			}else {
				jogador1.setNome(null);
				jogador1.setJogada(null);
				jogador1.setStatus(null);
			}
			contador ++;
		}
		
		if(jogador1.getStatus() == "venceu") {
			vencedores.add(jogador1);
		}else if(jogador2.getStatus() == "venceu"){
			vencedores.add(jogador2);
		}else {
			vencedores.addAll(empate);
		}
	
		return vencedores;
	}
	
	private void regra(JogadorModel p1, JogadorModel p2) {
		if(p1.getJogada().equals(p2.getJogada())) {
			p1.setStatus("empate");
			p2.setStatus("empate");
		}else {
			switch(p1.getJogada()) {
				case "spock":
					if(p2.getJogada().contains("tesoura") || p2.getJogada().contains("papel")) {
						p1.setStatus("venceu");
						p2.setStatus("perdeu");
					}else {
						p1.setStatus("perdeu");
						p2.setStatus("venceu");
					}
					break;
				case"tesoura":
					if(p2.getJogada().contains("lagarto") || p2.getJogada().contains("papel")) {
						p1.setStatus("venceu");
						p2.setStatus("perdeu");
					}else {
						p1.setStatus("perdeu");
						p2.setStatus("venceu");
					}
					break;
				case"papel":
					if(p2.getJogada().contains("pedra") || p2.getJogada().contains("spock")) {
						p1.setStatus("venceu");
						p2.setStatus("perdeu");
					}else {
						p1.setStatus("perdeu");
						p2.setStatus("venceu");
					}
					break;
				case"pedra":
					if(p2.getJogada().contains("tesoura") || p2.getJogada().contains("lagarto")) {
						p1.setStatus("venceu");
						p2.setStatus("perdeu");
					}else {
						p1.setStatus("perdeu");
						p2.setStatus("venceu");
					}
					break;
				case"lagarto":
					if(p2.getJogada().contains("spock") || p2.getJogada().contains("papel")) {
						p1.setStatus("venceu");
						p2.setStatus("perdeu");
					}else {
						p1.setStatus("perdeu");
						p2.setStatus("venceu");
					}
					break;
			}
		}
	}

}
