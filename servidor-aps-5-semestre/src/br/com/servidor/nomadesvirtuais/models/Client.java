package br.com.servidor.nomadesvirtuais.models;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private int id;
	private String name;
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	
	public Client(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void setReader() {
		try {
			InputStream is = this.getSocket().getInputStream();
			this.reader = new BufferedReader(new InputStreamReader(is));
		} catch (Exception e) {
			System.out.println("Em setReader(): " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setWriter() {
		try {
			OutputStream os = this.getSocket().getOutputStream();
			// parâmetro true é o shuffle, que realiza o envio automático da mensagem;
			this.writer = new PrintWriter(new OutputStreamWriter(os), true);
		} catch (Exception e) {
			System.out.println("Em setWriter(): " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return String.format("Nome: %s | id: %d", name, id);
	}
	
	public String getName() {
		return name;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public BufferedReader getReader() {
		return reader;
	}
	
	public PrintWriter getWriter() {
		return writer;
	}

	public int getId() {
		return id;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
