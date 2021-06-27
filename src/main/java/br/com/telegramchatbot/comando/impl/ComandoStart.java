package br.com.telegramchatbot.comando.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.comando.Comando;
import br.com.telegramchatbot.modelo.ChatFiap;

/**
 * 
 * Metodo Responsavel pela Primeira Integração com o Usuario, Retorna as
 * Informações e Comandos*/
public class ComandoStart implements Comando{
	
	@Override
	public SendResponse processar(TelegramBot bot, ChatFiap chat) throws Exception {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("Bem Vindo ao ChatBot");
		mensagem.append("este projeto tem por finalidade a avaliação final da disciplina de java, com o professor Rafael Tsuji Matsuyama.\n");
		mensagem.append("Este bot contém os seguintes comandos disponíveis:");
		
		chat.setCommand(null);
		
		return bot.execute(
				new SendMessage(chat.getChatId(), mensagem.toString()));
	}
}