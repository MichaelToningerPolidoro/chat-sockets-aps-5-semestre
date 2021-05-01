package br.com.servidor.nomadesvirtuais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {

	private final String url = "jdbc:mysql://localhost";
	private final String user = "root";
	private final String password = "root123";
	
	public DataBaseManager() {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.println("Em construtor DataBaseManager(): SQLException");
			e.printStackTrace();
		}
	}
}
