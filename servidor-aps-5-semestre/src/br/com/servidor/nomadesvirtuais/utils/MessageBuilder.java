package br.com.servidor.nomadesvirtuais.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.servidor.nomadesvirtuais.models.Message;

public class MessageBuilder {
	
	private static final int MENSAGEM_TABLE_INDEX = 1;
	private static final int ENVIADOEM_TABLE_INDEX = 2;

	public static Message buildMessage(ResultSet result) throws SQLException {
		if (result.next()) {
			String message = result.getString(MENSAGEM_TABLE_INDEX);
			String timeSent = result.getTime(ENVIADOEM_TABLE_INDEX).toString();
			return new Message(message, timeSent);
		}
		
		throw new SQLException();
	}
}
