package br.com.telegramchatbot.modelo;

import com.pengrad.telegrambot.model.Update;

/**
 * 
 * Classe Responsavel pela Criacao do Objeto de Chat
 * 
 * */
public class ChatFiap {
	
	private Long chatId;
	private String message;
	private String firstName;
	private String lastName;
	private String command;
	
	public ChatFiap(Update update) {
		this.chatId = update.message().chat().id();
		this.message = update.message().text().toLowerCase().trim().replaceAll("\\s+", " ");
		this.firstName = update.message().from().firstName();
		this.lastName = update.message().from().lastName();
		this.command = null;
	}
	
	public Long getChatId() {
		return chatId;
	}
	
	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getCommand() {
		return command;
	}
	
	public void setCommand(String command) {
		this.command = command;
	}
}