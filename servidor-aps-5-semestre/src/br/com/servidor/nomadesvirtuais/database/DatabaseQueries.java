package br.com.servidor.nomadesvirtuais.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		// query que traz a �ltima mensagem salva
		String sql = "SELECT mensagem FROM mensagens ORDER BY id DESC LIMIT 1;";
		PreparedStatement stmt = null;
		String processedMessage = null;
		
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				processedMessage = result.getString(1);				
			}
			
			stmt.close();
			result.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return processedMessage;
	}
	
	public String processMessage(String message) {
		try {
			String sql = "INSERT INTO mensagens(mensagem, enviado_em, id_usuario) VALUES (?, ?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, message);
			stmt.setDate(2, null);
			stmt.setInt(3, 1);
			stmt.execute();
			stmt.close();
			
			message = getProcessedMessage();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	public boolean login(String user, String password) {
		// TODO: implementar m�todo de login no projeto
		// Realiza a busca do usu�rio no DB e retorna
		// true se as informa��es forem v�lidas.
		
		return true;
	}
	
}
