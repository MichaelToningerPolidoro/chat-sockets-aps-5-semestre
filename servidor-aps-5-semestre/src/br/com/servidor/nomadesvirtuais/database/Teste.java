package br.com.servidor.nomadesvirtuais.database;

public class Teste {

	public static void main(String[] args) {
		DatabaseQueries query = new DatabaseQueries();
		String teste = query.processMessage("Apenas tentando se trata o DELETE ", 2);		
		System.out.println(teste);
	}
}
