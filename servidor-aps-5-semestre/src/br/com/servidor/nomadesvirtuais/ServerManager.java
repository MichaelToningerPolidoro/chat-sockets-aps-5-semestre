package br.com.servidor.nomadesvirtuais;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import br.com.servidor.nomadesvirtuais.models.Client;

public class ServerManager extends Thread {

	private Client client;
	private static final List<ServerManager> connectedClients = 
			new ArrayList<>();
	
	public ServerManager(Client client) {
		this.client = client;
		System.out.println("Conexão aceita em ServerManager");
		connectedClients.add(this);
		System.out.println("Tamanho lista conectados: " + connectedClients.size());
		start();
	}
	
	// Thread que realiza leitura/chegada das mensagens
	// E distriui para os demais conectados
	@Override
	public void run() {
		try {
			while(true) {
				String sender  = client.getName();
				String message = client.getReader().readLine();
				sendMessageToAllConnectedClients(sender, message);
			}
		} catch (SocketException e) {
			//tratamento da exceção connection reset
			System.out.println("Usuário " + this.client.getName() + " desconectado...");
		} catch (Exception e) {
			System.err.println("Em run(): " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void sendMessageToAllConnectedClients(String sender, String message) {
		StringBuffer sb;
		
		for (ServerManager connectedClient: connectedClients) {
			sb = new StringBuffer()
				.append(sender)
				.append(": ")
				.append(message)
				.append("\n");
			
			connectedClient.getClient()
				.getWriter()
				.println(sb);
		}
	}

	public Client getClient() {
		return client;
	}
}
