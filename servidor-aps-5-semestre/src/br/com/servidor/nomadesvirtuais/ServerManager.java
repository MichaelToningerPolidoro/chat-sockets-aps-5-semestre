package br.com.servidor.nomadesvirtuais;

import java.net.SocketException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.servidor.nomadesvirtuais.database.DatabaseConnector;
import br.com.servidor.nomadesvirtuais.models.Client;

public class ServerManager extends Thread {

	private Client client;
	private static final List<ServerManager> connectedClients = 
			new ArrayList<>();
	
	private static final Connection connection = 
			DatabaseConnector.getConnection();
	
	// criar atributo que guarda um databaseQueries
	
	public ServerManager(Client client) {
		this.client = client;
		connectedClients.add(this);
		System.out.println(String.format("Usuário conectado -> %s", this.client));
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
//		Alterar recebimento desse método para String message
//		chama o metodo que processa mensagem no BD
//			passando (String message, int client.getId())
//		Com isso é retornado um objeto de Message, contendo a mensagem e o horário.
//		por fim é chamado o setSender(client.getName()) para definir quem está mandando e então
//		realiza a iteração mandando para os demais clientes
		
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
