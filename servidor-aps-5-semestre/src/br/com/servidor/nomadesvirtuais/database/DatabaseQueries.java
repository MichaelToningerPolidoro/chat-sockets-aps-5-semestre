package br.com.servidor.nomadesvirtuais.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		// query que traz a última mensagem salva
		// TODO: Mudar para trazer horário também, assim da para montar um
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
		// TODO: implementar método de login no projeto
		// Realiza a busca do usuário no DB e retorna
		// true se as informações forem válidas.
		
		return true;
	}
	
}
