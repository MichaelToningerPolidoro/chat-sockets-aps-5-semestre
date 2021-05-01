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
	
	private void saveMessage(String message) {
		// TODO realizar uma query que salva uma mensagem
		// prov�velmente chamando a procedure/function

	}
	
	private String getProcessedMessage() {
		// TODO obter um retorno com a mensagem tratada
		return "";
	}
}
