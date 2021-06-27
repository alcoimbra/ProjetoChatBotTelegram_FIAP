package br.com.telegramchatbot.comando.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.comando.Comando;
import br.com.telegramchatbot.modelo.ChatFiap;
import br.com.telegramchatbot.utils.Utils;

/**
 * 
 * Metodo Responsavel por Retornar a Data e a Hora para o Usuario
 * 
 * */
public class ComandoDataHora implements Comando {
	
	@Override
	public SendResponse processar(TelegramBot bot, ChatFiap chat) throws Exception {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append(Utils.getDiaSemana());
		mensagem.append(", ");
		mensagem.append(
				LocalDateTime.now()
					.format(
							DateTimeFormatter.ofPattern(
									"dd/MM/yyyy HH:mm:ss")));
		
		chat.setCommand(null);
		
		return bot.execute(
				new SendMessage(chat.getChatId(), mensagem.toString()));
	}
}