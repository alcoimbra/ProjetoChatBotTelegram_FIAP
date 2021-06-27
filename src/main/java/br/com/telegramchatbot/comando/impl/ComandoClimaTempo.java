package br.com.telegramchatbot.comando.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import br.com.telegramchatbot.comando.Comando;
import br.com.telegramchatbot.modelo.ChatFiap;
import br.com.telegramchatbot.utils.Constantes;
import br.com.telegramchatbot.utils.Utils;

/**
 * 
 * Metodo Responsavel pela Integração com a API do Clima Tempo e 
 * Resposta para o Usuario
 * 
 * */
public class ComandoClimaTempo implements Comando{
	
	@Override
	public SendResponse processar(TelegramBot bot, ChatFiap chat) throws Exception {
		StringBuilder mensagem = new StringBuilder();
		
		try {
			String url = Constantes.CLIMA_TEMPO_URL + Utils.get("climatempo.token");
			ResponseEntity<String> response = new RestTemplate().exchange(url, HttpMethod.GET, null, String.class);
			JSONObject jsonResponse = new JSONObject(response.getBody());
			
			if(response.getStatusCode() == HttpStatus.OK) {
				mensagem.append(jsonResponse.get("name"))
					.append(" - ")
					.append(jsonResponse.get("state"))
					.append("\n\n");
				
				JSONArray array = jsonResponse.getJSONArray("data");
				
				JSONObject jsonHoje = new JSONObject(array.getJSONObject(0).get("temperature").toString());
				
				mensagem.append("Hoje")
					.append(" - ")
					.append(array.getJSONObject(0).get("date_br"))
					.append("\n");
				
				mensagem.append("Temperatura: min. ")
					.append(jsonHoje.get("min"))
					.append(" ºC ");
				
				mensagem.append("máx. ")
					.append(jsonHoje.get("max"))
					.append(" ºC \n\n");
				
				JSONObject jsonAmanha = new JSONObject(array.getJSONObject(1).get("temperature").toString());
				
				mensagem.append("Amanhã")
					.append(" - ")
					.append(array.getJSONObject(1).get("date_br"))
					.append("\n");
				
				mensagem.append("Temperatura: min. ")
					.append(jsonAmanha.get("min"))
					.append(" °C ");
				
				mensagem.append("máx. ").append(jsonAmanha.get("max")).append(" °C \n\n");
			} else {
				throw new Exception();
			}
		} catch(Exception e) {
			mensagem.append("Não é Possivel acessar o ClimaTempo. \nTente Novamente mais Tarde.");
		}
		
		chat.setCommand(null);
		
		return bot.execute(new SendMessage(chat.getChatId(), mensagem.toString()));
	}
}