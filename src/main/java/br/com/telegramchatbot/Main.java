package br.com.telegramchatbot;

import br.com.telegramchatbot.bot.Bot;
import br.com.telegramchatbot.utils.Utils;

/**
 * 
 * Metodo Main, Recebe o Parametro do Token vindo do Resources*/
public class Main {
	public static void main(String[] args) {
		Bot bot = new Bot(Utils.get("telegram.bot.token"));
		bot.executar();
	}
}