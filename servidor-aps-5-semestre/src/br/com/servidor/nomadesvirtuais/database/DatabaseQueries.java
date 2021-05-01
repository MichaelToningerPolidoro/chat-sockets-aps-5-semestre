package br.com.servidor.nomadesvirtuais.database;

import java.sql.Connection;

public class DatabaseQueries {

	/*
	 * Classe responsável pelas queries realizadas no banco de dados
	 * Apenas essa classe acessa a DatabaseConnector
	 * 
	 * Ela realiza a abstração das queries para uma utilização mais facil
	 * das chamadas SLQ, apenas passando alguns parâmetros
	 * */
	
	// Conexão referente à conexão do DatabaseConnector get conexao;
	private Connection connection;
	
	public DatabaseQueries() {
		// TODO construtor dessa classe
		connection = DatabaseConnector.getConnection();
	}
	
	private void saveMessage(String message) {
		// TODO realizar uma query que salva uma mensagem
		// provávelmente chamando a procedure/function

	}
	
	private String getProcessedMessage() {
		// TODO obter um retorno com a mensagem tratada
		return "";
	}
}
