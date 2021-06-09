package br.com.telegramchatbot.interfaces;

import java.util.Objects;

/**
 * Mostra como a Classificação deve Agir */
public interface ClassificationKind {
	
	boolean apply(String message, String str);
	
	default ClassificationKind or(ClassificationKind otherKind) {
		return (message, str2) -> {
			Objects.requireNonNull(otherKind);
			
			return this.apply(message, str2) || otherKind.apply(message, str2);
		};
	}
	
	
	/**
	 * do nothing, just return false
	 * 
	 * */
	ClassificationKind dummy = (a, b) -> false;
	
	
	/**
	 * str1 equals str2*/
	ClassificationKind equalsIgnoreCase = (String str1, String str2) -> {
		if(str1 == null && str2 == null) {
			return true;
		} else if (str1 != null && str2 != null) {
			return str1.equalsIgnoreCase(str2);
		}
		
		return false;
	};
	
	ClassificationKind containsIgnoreCase = (String str1, String str2) -> {
		if(str1 == null && str2 == null) {
			return true;
		} else if (str1 != null && str2 != null) {
			return str1.toLowerCase().contains(str2.toLowerCase());
		}
		
		return false;
	};
}