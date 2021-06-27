package br.com.telegramchatbot.comando;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.modelo.ChatFiap;

/**
 * 
 * Interface para Criação do Metodo que recebe o Bot e o Chat para Responder*/
public interface Comando {
	
	SendResponse processar(TelegramBot bot, ChatFiap chat) throws Exception;
}