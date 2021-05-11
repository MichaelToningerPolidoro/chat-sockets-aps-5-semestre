package br.com.servidor.nomadesvirtuais;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.servidor.nomadesvirtuais.database.DatabaseConnector;
import br.com.servidor.nomadesvirtuais.database.Login;
import br.com.servidor.nomadesvirtuais.models.Client;


public class Server {

	private static final int SERVER_PORT = 3000;
	private static ServerSocket servidor = null;

	public static void main(String[] args) {

		try {
			startServer();
			DatabaseConnector.openConnection();
			waitNewConections();
			stopServer();
		} catch (Exception e) {
			stopServer();
			DatabaseConnector.closeConnection();
			System.err.println("Em main() classe Servidor: " + e.getCause());
			e.printStackTrace();
		} finally {
			stopServer();
			DatabaseConnector.closeConnection();
		}
	}

	private static void waitNewConections() {
		Login login = new Login();
		int i = 0;
		try {
			while (true) {
				Socket newClientSocket = servidor.accept();
				
				OutputStream os = newClientSocket.getOutputStream();
				PrintWriter writer = new PrintWriter(new OutputStreamWriter(os), true);
				InputStream is = newClientSocket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				
				String[] credentials = br.readLine().split(";");
				String code = credentials[0];
				String password = credentials[1];
				
				Client newClient = login.basicLogin(code, password);
				
				if (newClient != null) {
					newClient.setSocket(newClientSocket);
					newClient.setReader();
					newClient.setWriter();
					
					writer.println("ok");
					
					new ServerManager(newClient);
				} else {
					System.err.println("Tentativa de login com credenciais inválidas");
					writer.println("");
				}
			}
		} catch (Exception e) {
			System.err.println("Em waitNewConnections(): " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	private static void startServer() throws IOException {
		System.out.println("Iniciando servidor ...");
		servidor = new ServerSocket(SERVER_PORT);
		System.out.println("Servidor iniciado com SUCESSO! | PORT=" + SERVER_PORT);
		System.out.println("Aguardando conexões ...");
	}

	private static void stopServer() {
		try {
			System.out.println("Tentando fechar servidor ...");
			servidor.close();
			System.out.println("Servidor fechado com sucesso!!");
		} catch (Exception e) {
			System.err.println("Em stopServer(): " + e.getCause());
			e.printStackTrace();
		}
	}
}
