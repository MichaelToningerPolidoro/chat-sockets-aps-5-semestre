package br.com.servidor.nomadesvirtuais.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.servidor.nomadesvirtuais.models.Client;

public class Login {
	
	private Connection connection;
	
	public Login() {
		connection = DatabaseConnector.getConnection();
	}

	public Client basicLogin(String code, String password) {
		Client newClient = null;
		String codeSQL = "SELECT u.id, u.nome_completo FROM usuarios u WHERE codigo = ? "
				+ "AND (SELECT senha FROM senhas WHERE id_usuario = u.id) = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(codeSQL);
			stmt.setString(1, code);
			stmt.setString(2, password);
			
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				int id = result.getInt(1);
				String name = result.getString(2);
				
				newClient = new Client(id, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return newClient;
	}
}
