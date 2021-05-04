package br.com.servidor.nomadesvirtuais.database;

public class Teste {

	public static void main(String[] args) {
		DatabaseQueries query = new DatabaseQueries();
		String teste = query.processMessage("Vamos INSERT novamente heehehe");		
		System.out.println(teste);
		
	}
}
