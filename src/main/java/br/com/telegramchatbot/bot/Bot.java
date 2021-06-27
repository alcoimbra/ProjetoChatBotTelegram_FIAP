package br.com.telegramchatbot.bot;

import java.util.HashMap;
import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.comando.Comando;
import br.com.telegramchatbot.comando.ComandoFactory;
import br.com.telegramchatbot.modelo.ChatFiap;

/**
 * 
 * Classe Responsavel pela Criação do Bot e seus Metodos
 * 
 * */
public class Bot {
	
	private TelegramBot bot;
	private Integer lastUpdateId = 0;
	private HashMap<Long, ChatFiap> chats = new HashMap<Long, ChatFiap>();
	
	public Bot(String token) {
		System.out.println("Ïniciando Bot");
		
		if(token == null || token.isEmpty()) {
			throw new NullPointerException("Token do Telegram Precisa ser Preenchido /resources/config.properties");
		}
		
		this.bot = new TelegramBot(token);
	}
	
	public void executar() {
		System.out.println("Bot Iniciando...");
		
		//Loop Infinito pode ser Alterado por algum timer de intervalo curto.
		while(true) {
			//Executa Comando no Telegram para Obter as Mensagens Pendentes a partir um
			//off-set (Limite Inicial)
			List<Update> updates = this.bot.execute(
					new GetUpdates().limit(100).offset(
							this.lastUpdateId)).updates();
			
			if(updates == null || updates.isEmpty()) {
				continue;
			}
			
			//Analise de cada Ação da Mensagem
			for(Update update : updates) {
				setLastUpdateId(update.updateId());
				
				//Recebendo Mensagem
				String mensagem = update.message().text();
				System.out.println("Recebendo Mensagem: " + mensagem);
				
				//Envio de "Escrevendo" antes de Enviar a Resposta
				this.getUpdates(update.message().chat().id());
				
				try {
					enviarMensagem(getChat(update));
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void enviarMensagem(ChatFiap chat) throws Exception {
		Comando comando = ComandoFactory.getComando(chat);
		SendResponse sendoResponse = comando.processar(this.bot, chat);
		
		//Verificação de Mensagem Enviada com Sucesso
		System.out.println("Mensagem Enviada? " + sendoResponse.isOk());
	}
	
	private void getUpdates(Long chatId) {
		BaseResponse baseResponse = this.bot.execute(
				new SendChatAction(
						chatId, ChatAction.typing.name()));
		
		//Verificação de Ação de Chat foi Enviada com Sucesso
		System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());
	}
	
	private void setLastUpdateId(Integer updateId) {
		this.lastUpdateId = updateId + 1;
	}
	
	private ChatFiap getChat(Update update) {
		Long chatId = update.message().chat().id();
		
		if(!this.chats.containsKey(chatId)) {
			this.chats.put(chatId, new ChatFiap(update));
		} else {
			this.chats.get(chatId).setMessage(
					update.message()
						.text()
						.toLowerCase()
						.trim()
						.replaceAll("\\s+", ""));
		}
		
		return this.chats.get(chatId);
	}
}