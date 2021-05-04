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
	
	public void saveMessage(String message) {
		// TODO realizar uma query que salva uma mensagem
		// provávelmente chamando a procedure/function
		System.out.println("Salvando mensagem no db ...");

	}
	
	public String getProcessedMessage() {
		// TODO obter um retorno com a mensagem tratada
		// através da function criada pela Raquel
		System.out.println("Obtendo mensagem processada ...");
		return "";
	}
	
	public boolean login(String user, String password) {
		// TODO: implementar método de login no projeto
		// Realiza a busca do usuário no DB e retorna
		// true se as informações forem válidas.
		
		return true;
	}
	
}
