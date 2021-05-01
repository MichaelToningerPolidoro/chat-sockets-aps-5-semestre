package br.com.servidor.nomadesvirtuais;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.servidor.nomadesvirtuais.utils.PropertiesReader;

public class DataBaseManager {
	
	public static void main(String[] args) {
		try {
			Properties props = PropertiesReader.getProperties();
			
			String url = props.getProperty("db.url");
			String user = props.getProperty("db.user");
			String password = props.getProperty("db.password");
			
			Connection connection = DriverManager.getConnection(url, user, password);
			
			connection.close();
			System.out.println("Fim, sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
