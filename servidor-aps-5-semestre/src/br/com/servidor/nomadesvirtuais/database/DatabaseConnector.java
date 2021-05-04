package br.com.servidor.nomadesvirtuais.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.servidor.nomadesvirtuais.utils.PropertiesReader;

public class DatabaseConnector {
	
	/*
	 * Essa classe é responsável pela conexão com o banco de dados
	 * Ela possui apenas 1 instância do atributo connection
	 * assim sempre que for chamada, possui apenas essa conexão com o DB
	 * */
	
	private static Connection connection;
	
	private DatabaseConnector() { }
	
	public static void openConnection() {
		if (connection == null) {
			try {
				System.out.println("Criando uma nova conexão com o DB");
				System.out.println("Iniciando conexão com BD ...");
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
		} else {
			System.err.println("Conexão com o DB já existente");
		}
	}
	
	public static void closeConnection() {
		try {
			if (!connection.isClosed()) {
				connection.close();				
				System.out.println("Conexão com o DB fechada!");
			} else {
				System.err.println("A conexão com o banco de dados já está fechada!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			if (connection == null) {
				openConnection();
				return connection;
			} else if (!connection.isClosed()) {
				return connection;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
