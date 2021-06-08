package br.com.telegramchatbot.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Classe responsável por recuperar os atributos setados no properties
 * 
 * @author alcoimbra*/
public class PropertiesLoader {
	
	private static final Logger log = LogManager.getLogger(PropertiesLoader.class.getName());
	private static final String path = "application.properties";
	private static Properties props = loadProps();
	
	
	/**
	 * Propriedades da Aplicação
	 * Retorna o objeto Singleton com as propriedades
	 * @return
	 * */
	public static Properties getProp() {
		if(props == null) {
			props = loadProps();
		}
		
		return props;
	}
	
	
	/**
	 * Carrega os valores das Propriedades
	 * @return
	 * */
	private static Properties loadProps() {
		Properties props = new Properties();
		
		try {
			InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			props.load(input);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("Houve um Problema ao Recuperar o " + path);
		} catch (NullPointerException ex) {
			ex.printStackTrace();
			log.error("Houve um Problema ao Recuperar o " + path);
		}
		
		return props;
	}
}