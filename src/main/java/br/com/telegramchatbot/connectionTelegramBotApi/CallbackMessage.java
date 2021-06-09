package br.com.telegramchatbot.connectionTelegramBotApi;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import br.com.telegramchatbot.helper.Classification;
import br.com.telegramchatbot.properties.PropertiesLoader;

/**
 * Clásse responsavel por receber a mensagem e realizar a manipulação das informações
 * recebidas pelo Telegram
 * 
 * GetUpdatesResponse - metodo encarregado de receber as mensagens
 * SendResponse - metodo encarregado de gerenciar o envio das respostas
 * BaseResponse - metodo encarregado de gerencias as ações do chat
 * 
 * @author alcoimbra*/
public class CallbackMessage {
	
	private static final Logger log = LogManager.getLogger(CallbackMessage.class.getName());
	private static Integer offSet = 0;
	private final Properties prop = PropertiesLoader.getProp();
	private String token;
	private GetUpdatesResponse getPedingMessages;
	
	/**
	 * Metodo Responsavel pelas Atualizações das Mensagens Atualizadas pelo Usuario
	 * 
	 * */
	public static GetUpdatesResponse offSet(TelegramBot bot) {
		return bot.execute(new GetUpdates().limit(100).offset(offSet));
	}
	
	/**
	 * Resposavel pela Exceução dos Comandos no Telegram, onde obtem as Mensagens Pendentes
	 * 
	 * */
	public void receivedMessages() {
		TelegramBot bot = botAdapterToken();
		
		try {
			while(true) {
				getPedingMessages = offSet(bot);
				Optional<List<Update>> updates = Optional.ofNullable(getPedingMessages.updates());
				
				if(updates.isPresent()) {
					updates.get().forEach(up -> {
						offSet = up.updateId() + 1;
						
						Classification classification = Classification.findClassification(up.message().text());
						log.info("Recebendo Mensagem " + classification.name() + ": " + up.message().text());
						String msg = String.valueOf(up.message().text());
						SendMessage.send(updates, bot, classification, msg);
					});
				}
			}
		} catch(NullPointerException e) {
			e.printStackTrace();
			log.error("ERRO AO PROCESSAR A MENSAGEM!!!");
		} catch(Exception ex) {
			ex.printStackTrace();
			log.error("ERRO AO PROCESSAR A MENSAGEM!!!");
		} finally {
			log.info("WHILE RODANDO");
		}
	}
	
	
	private TelegramBot botAdapterToken() {
		setToken(String.valueOf(prop.getProperty("prop.telegramToken")));
		TelegramBot bot = TelegramBotAdapter.build(getToken());
		
		return bot;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}