package br.com.servidor.nomadesvirtuais.database;

public class DatabaseQueries {

	/*
	 * Classe respons�vel pelas queries realizadas no banco de dados
	 * Apenas essa classe acessa a DatabaseConnector
	 * 
	 * Ela realiza a abstra��o das queries para uma utiliza��o mais facil
	 * das chamadas SLQ, apenas passando alguns par�metros
	 * */
	
	private DatabaseConnector dbConnector;
	
	public DatabaseQueries() {
		// TODO construtor dessa classe
	}
	
	private void stopDatabaseConnection() {
		// TODO realizar a parada da conex�o com o DB

	}
	
	private void saveMessage() {
		// TODO realizar uma query que salva uma mensagem
		// prov�velmente chamando a procedure/function

	}
	
	private void getProcessedMessage() {
		// TODO obter um retorno com a mensagem tratada

	}
}
