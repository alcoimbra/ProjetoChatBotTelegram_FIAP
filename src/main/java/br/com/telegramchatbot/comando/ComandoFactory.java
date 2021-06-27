package br.com.telegramchatbot.comando;

import br.com.telegramchatbot.comando.impl.ComandoAjuda;
import br.com.telegramchatbot.comando.impl.ComandoClimaTempo;
import br.com.telegramchatbot.comando.impl.ComandoDataHora;
import br.com.telegramchatbot.comando.impl.ComandoDefault;
import br.com.telegramchatbot.comando.impl.ComandoEmail;
import br.com.telegramchatbot.comando.impl.ComandoSobre;
import br.com.telegramchatbot.comando.impl.ComandoStart;
import br.com.telegramchatbot.modelo.ChatFiap;

/**
 * 
 * Classe Responsavel por fazer o Switch do Evento que o Usuario
 * escolheu, Dependendo do Comando ele Envia para o Metodo Correto
 * 
 * */
public class ComandoFactory {
	
	public static Comando getComando(ChatFiap chat) {
		ComandoEnum comando = ComandoEnum.getPeloCodigo(chat.getMessage());
		
		if(comando == null) {
			comando = ComandoEnum.getPeloCodigo(
					chat.getCommand() != null ? chat.getCommand() : ComandoEnum.DEFAULT.getCodigo());
		}
		
		switch(comando) {
			case START: {
				return new ComandoStart();
			}
			case DATA_HORA: {
				return new ComandoDataHora();
			}
			case SOBRE: {
				return new ComandoSobre();
			}
			case CLIMATEMPO: {
				return new ComandoClimaTempo();
			}
			case VALIDAR_EMAIL: {
				return new ComandoEmail();
			}
			case AJUDA: {
				return new ComandoAjuda();
			}
			default: {
				return new ComandoDefault();
			}
		}
	}
}