package br.com.telegramchatbot.comando.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.comando.Comando;
import br.com.telegramchatbot.comando.ComandoEnum;
import br.com.telegramchatbot.modelo.ChatFiap;
import br.com.telegramchatbot.utils.Utils;

/**
 * 
 * Metodo Responsavel por Validar se um E-mail é Valido Utilizando Regex
 * 
 * */
public class ComandoEmail implements Comando{
	
	@Override
	public SendResponse processar(TelegramBot bot, ChatFiap chat) throws Exception {
		StringBuilder mensagem = new StringBuilder();
		
		if(chat.getCommand() != null && chat.getCommand().equals(ComandoEnum.VALIDAR_EMAIL.getCodigo())) {
			if(Utils.validarEmail(chat.getMessage())) {
				mensagem.append("Este é um E-mail Válido!");
			} else {
				mensagem.append("Este é um E-mail Inválido!");
			}
			
			chat.setCommand(null);
		} else {
			mensagem.append("Por favor, Informe um E-mail");
			chat.setCommand(ComandoEnum.VALIDAR_EMAIL.getCodigo());
		}
		
		return bot.execute(
				new SendMessage(chat.getChatId(), mensagem.toString()));
	}
}