package br.com.telegramchatbot.comando;

/*
 * 
 * Enum para Listagem dos Metodos Disponiveis para o Usuario com a
 * Respectiva String de Resposta*/
public enum ComandoEnum {
	
	START("/start", "Inicia o Chat com as Boas Vindas", true),
	SOBRE("/sobre", "Informa sobre o Chat", true),
	DATA_HORA("/datahora", "Informa a Data e Hora", true),
	CLIMATEMPO("/climatempo", "Informa a Temperatura de Hoje e Amanhã (Cidade de São Paulo)", true),
	VALIDAR_EMAIL("/validaremail", "Valida se um E-mail é Valido", true),
	AJUDA("/ajuda", "Informa os Comandos Disponiveis", true),
	DEFAULT("/default", "Enviar Mensagens caso não seja nenhum Comando Acima", false);
	
	private String codigo;
	private String descricao;
	private Boolean exibir;
	
	ComandoEnum(String codigo, String descricao, Boolean exibir){
		this.codigo = codigo;
		this.descricao = descricao;
		this.exibir = exibir;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public boolean isExibir() {
		return exibir;
	}
	
	public static ComandoEnum getPeloCodigo(String codigo) {
		for(ComandoEnum comando : ComandoEnum.values()) {
			if(comando.codigo.equals(codigo)) {
				return comando;
			}
		}
		
		return null;
	}
	
	public static String exibirComando() {
		StringBuilder comandos = new StringBuilder();
		
		for(ComandoEnum comando : ComandoEnum.values()) {
			if(!comando.exibir) {
				continue;
			}
			
			comandos.append("\n" + comando.codigo + " - " + comando.descricao);
		}
		
		return comandos.toString();
	}
}