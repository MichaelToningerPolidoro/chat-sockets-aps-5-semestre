package br.com.servidor.nomadesvirtuais.database;

import java.sql.Connection;

public class DatabaseQueries {

	/*
	 * Classe respons�vel pelas queries realizadas no banco de dados
	 * Apenas essa classe acessa a DatabaseConnector
	 * 
	 * Ela realiza a abstra��o das queries para uma utiliza��o mais facil
	 * das chamadas SLQ, apenas passando alguns par�metros
	 * */
	
	// Conex�o referente � conex�o do DatabaseConnector get conexao;
	private Connection connection;
	
	public DatabaseQueries() {
		// TODO construtor dessa classe
		connection = DatabaseConnector.getConnection();
	}
	
	public void saveMessage(String message) {
		// TODO realizar uma query que salva uma mensagem
		// prov�velmente chamando a procedure/function
		System.out.println("Salvando mensagem no db ...");

	}
	
	public String getProcessedMessage() {
		// TODO obter um retorno com a mensagem tratada
		// atrav�s da function criada pela Raquel
		System.out.println("Obtendo mensagem processada ...");
		return "";
	}
	
	public boolean login(String user, String password) {
		// TODO: implementar m�todo de login no projeto
		// Realiza a busca do usu�rio no DB e retorna
		// true se as informa��es forem v�lidas.
		
		return true;
	}
	
}
