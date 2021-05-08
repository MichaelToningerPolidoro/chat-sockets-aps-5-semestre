package br.com.cliente.nomadesvirtuais.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Reader extends Thread {

	private static BufferedReader reader;
	
	public Reader(Socket socket) {
		
		try {
			InputStream is = ConnectionHandle.getConnection().getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String mensagem = reader.readLine().trim();
				System.out.println(mensagem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
