package br.com.telegramchatbot.connectionTelegramBotApi;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.dto.ObjectApiDTO;
import br.com.telegramchatbot.helper.Classification;
import br.com.telegramchatbot.helper.HelpString;
import br.com.telegramchatbot.newsApi.NewsApi;

/**
 * Classe Responsavel por Receber a Mensagem do Usuario e Encaminha-la
 * 
 * */
public class SendMessage {
	
	private static final Logger log = LogManager.getLogger(SendMessage.class.getName());

	/**
	 * Recebe a Mensagem do Usuario e Verifica o que Enviar
	 * 
	 * @param updates
	 * @param bot
	 * @param classification
	 * @param msg
	 * @return
	 * 
	 * */
	public static void send(Optional<List<Update>> updates, TelegramBot bot, 
			Classification classification, String msg) {
		
		if(updates.isPresent()) {
			updates.get().forEach(up -> {
				enviarEscrevendo(bot, up);
				
				try {
					switch(classification) {
						case NEWS:
							ObjectApiDTO article = NewsApi.callApi(bot, HelpString.cleanTagNews(up.message().text()));
							
							if(article.getTotalResults() == 0) {
								textPersonalizado(bot, up, "Infelizmente não Encontramos Resultados para sua Busca \n"
										+ "Tente Novamente, Digite /noticias e o que você deseja procurar");
							} else {
								article.getArticles().forEach(art -> {
									textPersonalido(bot, up, "Resultado da sua Busca: \n\n"
											+ HelpString.addLinkNoticia(art.getDescription(), art.getUrl()));
								});
							}
							break;
						case GREETINGS:
							String emojiSorrir = "\ud83d\ude00";
							textPersonalizado(bot, up, HelpString.firstLetterToUpperCase(up.message().text() + " " + up.message().from().forstName()
									+ ", Tudo bem?? " + emojiSorrir));
							break;
						case HELP:
							textPersonalizado(bot, up, "Digite Noticias [e o que você deseja saber] \n\n"
									+ "Exemplo: Noticias sobre Futebol");
							break;
						default:
							SendResponse sendResp = bot.execute(new SendMessage(up.message().chat().id(), "Não Entendi pode Digitar Novamente?? \n (Tente /ajuda ou /help)"));
							log.info("Mensagem Enviada?? " + sendResp.isOk());
							log.info("Recebendo Mensagem: " + up.message().text());
							break;
							
					}
				} catch(ArrayIndexOutOfBoundsException e) {
					log.error("HOUVE UM PROBLEMA AO RECEBER A LISTA DE MENSAGEM", e);
				} catch(Exception ex) {
					log.error("HOUVE UM PROBLEMA", ex);
				}
			});
		} else {
			log.info("Objeto Vazio");
		}
	}
	
	/**
	 * Mostra que esta Escrevendo
	 * 
	 * @param bot
	 * @param up
	 * @return Resposta de Envio da Mensagem do Bot
	 * 
	 * */
	public static BaseResponse enviarEscrevendo(TelegramBot bot, Update up) {
		return bot.execute(new SendChatAction(up.message().chat().id(), ChatAction.typing.name()));
	}
	
	
	/**
	 * Personaliza o Envio da Mensagem para o Cliente
	 * 
	 * @param bot
	 * @param up
	 * @return Resposta de Envio de Mensagem do Bot
	 * 
	 * */
	public static SendResponse textPersonalizado(TelegramBot bot, Update up, String msg) {
		return bot.execute(new SendMessage(up.message().chat().id(), msg));
	}
}