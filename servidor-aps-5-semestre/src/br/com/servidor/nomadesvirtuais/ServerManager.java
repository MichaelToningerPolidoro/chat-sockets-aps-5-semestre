package br.com.servidor.nomadesvirtuais;

import java.net.SocketException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.servidor.nomadesvirtuais.database.DatabaseConnector;
import br.com.servidor.nomadesvirtuais.database.DatabaseQueries;
import br.com.servidor.nomadesvirtuais.models.Client;
import br.com.servidor.nomadesvirtuais.models.Message;

public class ServerManager extends Thread {

	private Client client;
	private static final List<ServerManager> connectedClients = 
			new ArrayList<>();
	
	private static final DatabaseQueries db =
			new DatabaseQueries();
	
	public ServerManager(Client client) {
		this.client = client;
		connectedClients.add(this);
		System.out.println(String.format("Usuário conectado -> %s", this.client));
		start();
	}
	
	// Thread que realiza leitura/chegada das mensagens
	@Override
	public void run() {
		try {
			while(true) {
				String message = client.getReader().readLine();
				sendMessageToAllConnectedClients(message);
			}
		} catch (SocketException e) {
			//tratamento da exceção connection reset
			System.out.println("Usuário " + this.client.getName() + " desconectado...");
		} catch (Exception e) {
			System.err.println("Em run(): " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void sendMessageToAllConnectedClients(String message) {
		message = db.processMessage(this.client, message);
		
		for (ServerManager connectedClient: connectedClients) {
			
			connectedClient
				.getClient()
				.getWriter()
				.println(message);
		}
	}

	public Client getClient() {
		return client;
	}
}
