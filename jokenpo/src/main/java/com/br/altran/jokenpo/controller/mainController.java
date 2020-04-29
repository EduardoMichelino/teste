package com.br.altran.jokenpo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.altran.jokenpo.service.IJokenpoService;

@RestController
public class mainController {
	
	@Autowired
	IJokenpoService jokenpoService;
	
	@RequestMapping("/")
	public String instrucoes() {
		return jokenpoService.instrucoes();
	}
	
	@RequestMapping(value="/addPlayer/{jogador}/{jogada}", method=RequestMethod.GET)
	public String addPlayer(@PathVariable("jogador") String pJogador, @PathVariable("jogada") String pJogada) {
		return jokenpoService.addPlayer(pJogador.toLowerCase(), pJogada.toLowerCase());
	}
	
	@RequestMapping(value="/jogar" , method=RequestMethod.GET)
	public String jogar() {
		return jokenpoService.jogar();
	}
	
	@RequestMapping(value="/consultaJogadores" , method=RequestMethod.GET)
	public String consultaJogador() {
		return jokenpoService.consultaJogadores();
	}

}
