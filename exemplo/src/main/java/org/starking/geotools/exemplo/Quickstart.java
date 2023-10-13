package org.starking.geotools.exemplo;

import java.util.logging.Logger;

/**
 * @author pedroRhamon
 */
public class Quickstart {
	
	private static final Logger LOGGER= org.geotools.util.logging.Logging.getLogger(Quickstart.class);
	
	public static void main(String[] args) {
		LOGGER.info("Iniciando...");
		LOGGER.config("Bem vindo");
		LOGGER.info("java.util.logging.config.file="+System.getProperty("java.util.logging.config.file"));
	}

}
