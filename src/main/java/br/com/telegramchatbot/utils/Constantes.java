package br.com.telegramchatbot.utils;

/**
 * 
 * Classe Contendo as Constantes da URL do Clima Tempo, Dias da Semana,
 * as Entradas de Saudação Usando Regex e Validação de E-mail
 * 
 * */
public class Constantes {
	
	public static final String CLIMA_TEMPO_URL = "http://apiadvisor.climatempo.com.br/api/v1/forecast/locale/3477/days/15?token=";
	
	/*Dias da Semana*/
	public static String[] DIAS_SEMANA = {
			"Domingo", 
			"Segunda-Feira", 
			"Terça-Feira", 
			"Quarta-Feira", 
			"Quinta-Feira", 
			"Sexta-Feira", 
			"Sabado"};
	
	/*Entrada*/
	public static final String ENTRADA_SAUDACOES = "o+(l(a|á)|i)|h+(i|ello)|bo+(a|m)+\\\\s+(dia|tarde|noite)";
	public static final String REGEX_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
}