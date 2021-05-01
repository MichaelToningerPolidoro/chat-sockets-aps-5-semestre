package br.com.servidor.nomadesvirtuais.database;

public class DatabaseQueries {

	/*
	 * Classe responsável pelas queries realizadas no banco de dados
	 * Apenas essa classe acessa a DatabaseConnector
	 * 
	 * Ela realiza a abstração das queries para uma utilização mais facil
	 * das chamadas SLQ, apenas passando alguns parâmetros
	 * */
	
	private DatabaseConnector dbConnector;
	
	public DatabaseQueries() {
		// TODO construtor dessa classe
	}
	
	private void stopDatabaseConnection() {
		// TODO realizar a parada da conexão com o DB

	}
	
	private void saveMessage() {
		// TODO realizar uma query que salva uma mensagem
		// provávelmente chamando a procedure/function

	}
	
	private void getProcessedMessage() {
		// TODO obter um retorno com a mensagem tratada

	}
}
