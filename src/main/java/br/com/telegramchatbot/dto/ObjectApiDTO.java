package br.com.telegramchatbot.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ObjectApiDTO {
	
	private String status;
	private Integer totalResults;
	private List<ArticleDTO> articles;
}