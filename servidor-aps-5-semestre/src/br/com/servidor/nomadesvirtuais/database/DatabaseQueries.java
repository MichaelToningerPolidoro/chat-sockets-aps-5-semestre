package br.com.servidor.nomadesvirtuais.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.servidor.nomadesvirtuais.models.Client;
import br.com.servidor.nomadesvirtuais.models.Message;
import br.com.servidor.nomadesvirtuais.utils.MessageBuilder;

public class DatabaseQueries {

	/*
	 * Classe responsável pelas queries realizadas no banco de dados
	 * Apenas essa classe acessa a DatabaseConnector
	 * 
	 * Ela realiza a abstração das queries para uma utilização mais facil
	 * das chamadas SLQ, apenas passando alguns parâmetros
	 * */
	
	// Conexão referente à conexão do DatabaseConnector getconexao;
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
		String sql = "SELECT mensagem, enviado_em FROM mensagens ORDER BY id DESC LIMIT 1;";
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			Message buildedMessage = MessageBuilder.buildMessage(result);
			
			stmt.close();
			result.close();
			
			return buildedMessage;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public String processMessage(Client client, String message) {
		saveMessage(message, client.getId());
		Message processedMessage = getLastProcessedMessage();
		processedMessage.setSender(client.getName());
		return processedMessage.toString();
	}
	
	public List<String> getMessagesFrom10HoursAgo() {
		String sql = "SELECT m.mensagem, m.enviado_em, u.nome_completo FROM mensagens m, usuarios u"
				+ " WHERE m.id_usuario = u.id AND m.enviado_em BETWEEN DATE_SUB(NOW(), INTERVAL 15 minute) AND NOW()"
				+ " ORDER BY m.enviado_em ASC LIMIT 20;";
		
		List<String> msgsList = new ArrayList<>();
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				String message = result.getString(1);
				String timeSent = result.getTime(2).toString();
				String sender = result.getString(3);
				
				Message tempMsg = new Message(message, timeSent);
				tempMsg.setSender(sender);
				
				msgsList.add(tempMsg.toString());
			}
			
			stmt.close();
			result.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return msgsList;
	}
}
