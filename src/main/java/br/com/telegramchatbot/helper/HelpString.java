package br.com.telegramchatbot.helper;

import java.util.Optional;

/**
 * Classe Auxiliar para Manipulação de Contexto de Conversa*/
public class HelpString {
	
	/**
	 * Adiciona Link para saber mais sobre a noticia
	 * 
	 * @param description
	 * @param url
	 * @return
	 * 
	 * */
	public static String addLinkNoticia(String description, String url) {
		return description.concat("\n\n" + "**" + "Para saber mais acesse" + "**" + ": " + "\n\n" + url);
	}
	
	/**
	 * Obtendo a Primeira Letra da String
	 * 
	 * @param letter
	 * @return
	 * 
	 * */
	public static String firstLetterToUpperCase(String letter) {
		return letter.substring(0, 1).toUpperCase() + letter.substring(1);
	}
	
	public static String cleanTagNews(String text) {
		Optional<String> puzzle = Classification.NEWS.getList().stream()
																.filter(a -> text.contains(a))
																.findAny();
		
		if(puzzle.isPresent()) {
			return text.replace(puzzle.get(), "").trim();
		}
		
		return text;
	}
}