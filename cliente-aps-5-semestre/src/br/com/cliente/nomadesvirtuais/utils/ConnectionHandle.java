package br.com.cliente.nomadesvirtuais.utils;

import java.net.Socket;

public class ConnectionHandle {

	private static Socket connection;
	
	public static void setConnection(Socket socketConnection) {
		ConnectionHandle.connection = socketConnection;
	}
	
	public static Socket getConnection() {
		return connection;
	}
}
