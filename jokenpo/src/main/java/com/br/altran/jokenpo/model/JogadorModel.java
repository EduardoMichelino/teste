package com.br.altran.jokenpo.model;

public class JogadorModel {

	private String nome;
	private String jogada;
	private String status;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getJogada() {
		return jogada;
	}

	public void setJogada(String jogada) {
		this.jogada = jogada;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "JogadorModel [nome=" + nome + ", jogada=" + jogada + "]";
	}

}
