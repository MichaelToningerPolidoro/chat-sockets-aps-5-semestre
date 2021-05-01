package br.com.servidor.nomadesvirtuais;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
	
	public static void main(String[] args) {
		try {
			Connection connection = DriverManager.getConnection("");
			connection.close();
			System.out.println("Fim, sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
