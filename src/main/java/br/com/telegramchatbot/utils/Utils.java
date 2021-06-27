package br.com.telegramchatbot.utils;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * 
 * Classe com Metodos Utilitarios como para Pegar o Dia da Semana,
 * Validar o E-mail e Pegar Propriedade da Resources
 * 
 * */
public class Utils {
	
	private static Properties props;
	
	static {
		try {
			InputStream resource = Utils.class.getResourceAsStream("/config.properties");
			props = new Properties();
			
			props.load(resource);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getDiaSemana() {
		return Constantes.DIAS_SEMANA[LocalDateTime.now()
		                              	.getDayOfWeek()
		                              	.getValue()];
	}
	
	public static String getSaudacao() {
		Integer hora = LocalDateTime.now().getHour();
		
		if(hora >= 18 && hora < 24) {
			return "Boa Noite";
		} else if(hora >= 12 && hora < 18) {
			return "Boa Tarde";
		} else {
			return "Bom Dia";
		}
	}
	
	public static String get(String key) {
		return props.getProperty(key);
	}
	
	public static Boolean validarEmail(String email) {
		if(email == null || email.equals("")) {
			return false;
		}
		
		if(Pattern.compile(Constantes.REGEX_EMAIL).matcher(email).matches()) {
			return true;
		} else {
			return false;
		}
	}
}