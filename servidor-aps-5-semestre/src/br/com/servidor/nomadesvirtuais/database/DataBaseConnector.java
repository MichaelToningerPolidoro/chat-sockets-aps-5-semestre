package br.com.servidor.nomadesvirtuais.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.servidor.nomadesvirtuais.utils.PropertiesReader;

public class DataBaseConnector {
	
	private Connection connection;
	
	public void openConnection() {		
		try {
			Properties props = PropertiesReader.getProperties();
			
			String url = props.getProperty("db.url");
			String user = props.getProperty("db.user");
			String password = props.getProperty("db.password");
			
			connection = DriverManager.getConnection(url, user, password);
			System.out.println("Conexão BD iniciada!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Conexão com o DB fechada!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			if (connection != null && !connection.isClosed()) {
				return connection;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
