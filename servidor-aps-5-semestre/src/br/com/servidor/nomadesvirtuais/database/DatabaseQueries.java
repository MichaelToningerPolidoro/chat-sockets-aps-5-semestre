package br.com.servidor.nomadesvirtuais.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.servidor.nomadesvirtuais.models.Client;
import br.com.servidor.nomadesvirtuais.models.Message;

public class DatabaseQueries {

	/*
	 * Classe respons�vel pelas queries realizadas no banco de dados
	 * Apenas essa classe acessa a DatabaseConnector
	 * 
	 * Ela realiza a abstra��o das queries para uma utiliza��o mais facil
	 * das chamadas SLQ, apenas passando alguns par�metros
	 * */
	
	// Conex�o referente � conex�o do DatabaseConnector getconexao;
	private Connection connection;
	
	public DatabaseQueries() {
		connection = DatabaseConnector.getConnection();
	}
	
	public void saveMessage(String message, int userId) {
		try {
			String sql = "INSERT INTO mensagens(mensagem, id_usuario) VALUES (?, ?)";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, message);
			stmt.setInt(2, userId);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Message getLastProcessedMessage() {
		final int MENSAGEM_TABLE_INDEX = 1;
		final int ENVIADOEM_TABLE_INDEX = 2;
		String sql = "SELECT mensagem, enviado_em FROM mensagens ORDER BY id DESC LIMIT 1;";
		String message = null;
		String timeSent = null;
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				message = result.getString(MENSAGEM_TABLE_INDEX);
				timeSent = result.getTime(ENVIADOEM_TABLE_INDEX).toString(); //hh:mm:ss				
			}
			
			stmt.close();
			result.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Message(message, timeSent);
		
	}
	
	public String processMessage(Client client, String message) {
		saveMessage(message, client.getId());
		Message processedMessage = getLastProcessedMessage();
		processedMessage.setSender(client.getName());
		return processedMessage.toString();
	}
	
	public boolean login(String codigo, String password) {
		// TODO: implementar m�todo de login no projeto
		// Realiza a busca do usu�rio no DB e retorna
		// true se as informa��es forem v�lidas.
		
		return true;
	}
	
}
