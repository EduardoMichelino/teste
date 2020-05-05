package com.br.altran.jokenpo.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class JokenpoSerciceTest {

	@Autowired
	private JokenpoService joquenpo;
	

	@Test
	public void testeInserirJogador() {
		String jogador1 = "Eu";
		String jogada1 = "pedra";
		
		String addPlayer1 = joquenpo.addPlayer(jogador1, jogada1);
		assertEquals("[JogadorModel [nome=Eu, jogada=pedra]]", addPlayer1);
	}
	
	@Test
	public void testeJogarDoisJogadores() {
		String jogador1 = "Eu";
		String jogada1 = "pedra";
		String jogador2 = "ele";
		String jogada2 = "papel";
		
		joquenpo.addPlayer(jogador1, jogada1);
		joquenpo.addPlayer(jogador2, jogada2);
		
		String jogar = joquenpo.jogar();
		assertEquals("Resultado: ele venceu!", jogar);
	}
	
	@Test
	public void testeJogarTresJogadores() {
		String jogador1 = "eu";
		String jogada1 = "papel";
		String jogador2 = "ele";
		String jogada2 = "pedra";
		String jogador3 = "nos";
		String jogada3 = "tesoura";
		
		joquenpo.addPlayer(jogador1, jogada1);
		joquenpo.addPlayer(jogador2, jogada2);
		joquenpo.addPlayer(jogador3, jogada3);
		
		String jogar = joquenpo.jogar();
		assertEquals("Resultado: nos venceu!", jogar);
	}
	
	@Test
	public void testeEmpate() {
		String jogador1 = "Eu";
		String jogada1 = "pedra";
		String jogador2 = "ele";
		String jogada2 = "pedra";
		
		joquenpo.addPlayer(jogador1, jogada1);
		joquenpo.addPlayer(jogador2, jogada2);
	
		String jogar = joquenpo.jogar();
		assertEquals("Resultado: Ouve empate entre [JogadorModel [nome=Eu, jogada=pedra], JogadorModel [nome=ele, jogada=pedra]]", jogar);
		
	}
	
	@Test
	public void testeChato() {
		String jogador1 = "Eu";
		String jogada1 = "pedra";
		String jogador2 = "ele";
		String jogada2 = "pedra";
		String jogador3 = "nos";
		String jogada3 = "papel";
		String jogador4 = "aquele";
		String jogada4 = "tesoura";
		String jogador5 = "aquela";
		String jogada5 = "tesoura";
		String jogador6 = "Judth";
		String jogada6 = "pedra";
		
		joquenpo.addPlayer(jogador1, jogada1);
		joquenpo.addPlayer(jogador2, jogada2);
		joquenpo.addPlayer(jogador3, jogada3);
		joquenpo.addPlayer(jogador4, jogada4);
		joquenpo.addPlayer(jogador5, jogada5);
		joquenpo.addPlayer(jogador6, jogada6);
		
		String jogar = joquenpo.jogar();
		//assertEquals("Resultado: Ouve empate entre [JogadorModel [nome=aquele, jogada=tesoura], JogadorModel [nome=aquela, jogada=tesoura]]", jogar);
		assertEquals("Resultado: Judth venceu!", jogar);
		
	}
		
		
	

}
