package br.com.servidor.nomadesvirtuais;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.servidor.nomadesvirtuais.database.DatabaseConnector;
import br.com.servidor.nomadesvirtuais.models.Client;

//import server.GerenciadorDeConexoes;
//import server.modelo.Cliente;

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
		int temp = 0;  // Utilizado apenas para testes
		try {
			while (true) {
				Socket newClientSocket = servidor.accept();
				Client newClient = new Client("nome" + temp++ , newClientSocket);
				new ServerManager(newClient);
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
