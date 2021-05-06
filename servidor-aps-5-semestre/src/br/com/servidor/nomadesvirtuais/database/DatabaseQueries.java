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
	
	// TODO: Mudar para retornar um MEssage
	public String getLastProcessedMessage() {
		// query que traz a �ltima mensagem salva
		// TODO: Mudar para trazer hor�rio tamb�m, assim da para montar um
		// obj message e assim retornar para o processMessage();
		String sql = "SELECT mensagem FROM mensagens ORDER BY id DESC LIMIT 1;";
		PreparedStatement stmt = null;
		String processedMessage = null;
		
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				processedMessage = result.getString(1);
				//result.getTime("enviado_em").toString();
			}
			
			stmt.close();
			result.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return processedMessage;
	}
	
	// TODO: Mudar para retornar um obj Message
	public String processMessage(String message, int userId) {
		saveMessage(message, userId);
		message = getLastProcessedMessage();
		return message;
	}
	
	public boolean login(String user, String password) {
		// TODO: implementar m�todo de login no projeto
		// Realiza a busca do usu�rio no DB e retorna
		// true se as informa��es forem v�lidas.
		
		return true;
	}
	
}
