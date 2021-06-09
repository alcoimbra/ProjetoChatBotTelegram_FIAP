package br.com.telegramchatbot.newsApi;

import java.io.IOException;
import java.util.Properties;

import com.google.gson.Gson;
import com.pengrad.telegrambot.TelegramBot;

import br.com.telegramchatbot.dto.ObjectApiDTO;
import br.com.telegramchatbot.properties.PropertiesLoader;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Classe Responsavel por Consumir a NewsApi de acordo com a busca do Usuario
 * 
 * @author alcoimbra
 * 
 * */
public class NewsApi {
	
	private static Properties prop = PropertiesLoader.getProp();
	private static OkHttpClient client = new OkHttpClient();
	private static Response resp;
	
	public static ObjectApiDTO callApi(TelegramBot bot, String text) {
		String baseUrl = prop.getProperty("prop.newsApi");
		ObjectApiDTO news = null;
		
		HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
		urlBuilder.addQueryParameter("q", text);
		urlBuilder.addQueryParameter("apiKey", prop.getProperty("prop.keyNewsApi"));
		urlBuilder.addQueryParameter("pageSize", "1");
		urlBuilder.addQueryParameter("sortBy", "relevancy");
		urlBuilder.addQueryParameter("language", "pt");
		
		String url = urlBuilder.build().toString();
		
		Request req = new Request.Builder().url(url).build();
		
		try {
			resp = client.newCall(req).execute();
			news = new Gson().fromJson(resp.body().charStream(), ObjectApiDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return news;
	}
}