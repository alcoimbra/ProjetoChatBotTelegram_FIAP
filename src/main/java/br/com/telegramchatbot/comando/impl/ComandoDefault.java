package br.com.telegramchatbot.comando.impl;import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.comando.Comando;
import br.com.telegramchatbot.modelo.ChatFiap;
import br.com.telegramchatbot.utils.Constantes;
import br.com.telegramchatbot.utils.Utils;

/**
 * 
 * Metodo Padrão, sempre que o Usuario Digitar algo que o Robo não Entender
 * ou for uma Interação de Saudação ira cair nesse Metodo.
 * 
 * */
public class ComandoDefault implements Comando{
	
	@Override
	public SendResponse processar(TelegramBot bot, ChatFiap chat) throws Exception {
		StringBuilder mensagem = new StringBuilder();
		
		if(chat.getMessage().matches(Constantes.ENTRADA_SAUDACOES)) {
			mensagem.append(String.format("Olá %s! %s, seja Bem Vindo ao nosso Chat", chat.getFirstName(), Utils.getSaudacao()));
			mensagem.append("\nGostaria de Pedir /ajuda?");
		} else {
			mensagem.append("Desculpe! Não Consegui Entender. \nGostaria de Pedir /ajuda?");
		}
		
		chat.setCommand(null);
		
		return bot.execute(
				new SendMessage(chat.getChatId(), mensagem.toString()));
	}
}