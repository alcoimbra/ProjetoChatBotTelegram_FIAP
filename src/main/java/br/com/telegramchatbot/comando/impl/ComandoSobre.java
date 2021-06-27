package br.com.telegramchatbot.comando.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.comando.Comando;
import br.com.telegramchatbot.modelo.ChatFiap;

/**
 * 
 * Metodo Responsavel por trazer Informações sobre o Bot
 * 
 * */
public class ComandoSobre implements Comando {
	
	@Override
	public SendResponse processar(TelegramBot bot, ChatFiap chat) throws Exception {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("MBA em Full Stack Development - Design, Engineering & Deployment\n");
		mensagem.append("Java Platform - Rafael Tsuji Matsuyama\n");
		mensagem.append("Telegram Bot: Avaliação Total da Disciplina");
		
		chat.setCommand(null);
		
		return bot.execute(
				new SendMessage(chat.getChatId(), mensagem.toString()));
	}
}