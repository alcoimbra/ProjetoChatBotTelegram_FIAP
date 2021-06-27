package br.com.telegramchatbot.comando.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.comando.Comando;
import br.com.telegramchatbot.comando.ComandoEnum;
import br.com.telegramchatbot.modelo.ChatFiap;

/**
 * 
 * Responsavel por Exibir as Mensagens de Ajuda para os Comandos
 * 
 * */
public class ComandoAjuda implements Comando {
	
	@Override
	public SendResponse processar(TelegramBot bot, ChatFiap chat) throws Exception {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("Este Chat Contem os Seguintes Comandos Disponiveis: ");
		mensagem.append(ComandoEnum.exibirComando());
		
		chat.setCommand(null);
		
		return bot.execute(
				new SendMessage(
						chat.getChatId(), mensagem.toString()));
	}
}