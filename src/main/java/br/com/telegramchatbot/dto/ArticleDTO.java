package br.com.telegramchatbot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticleDTO {
	
	private SourceDTO source;
	private String author;
	private String title;
	private String description;
	private String url;
	private String urlToImage;
	private String publishedAt;
	private String content;
}