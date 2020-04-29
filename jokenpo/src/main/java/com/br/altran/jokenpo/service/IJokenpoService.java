package com.br.altran.jokenpo.service;

public interface IJokenpoService {
	public String instrucoes();
	public String addPlayer(String pJogador, String pJogada);
	public String jogar();
	public String novoJogo();
	public String consultaJogadores();
}
