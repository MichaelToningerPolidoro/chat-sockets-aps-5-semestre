package br.com.servidor.nomadesvirtuais.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	public static Properties getProperties() throws IOException {
		Properties props = new Properties();
		String filePath = "/database.properties";
		props.load(PropertiesReader.class.getResourceAsStream(filePath));
		
		return props;
	}
}
