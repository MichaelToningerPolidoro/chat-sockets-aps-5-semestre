package br.com.servidor.nomadesvirtuais.models;

public class Message {

	private String sender;
	private String message;
	private String timeSent;
	
	public Message(String sender, String message, String timeSent) {
		this.sender = sender;
		this.message = message;
		this.timeSent = timeSent;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append(sender)
				.append(" �s ")
				.append(timeSent)
				.append("\n")
				.append(message)
				.append("\n")
				.toString()
			;
	}

	public String getMessage() {
		return message;
	}

	public String getTimeSent() {
		return timeSent;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
}
