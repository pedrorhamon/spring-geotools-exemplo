package org.starking.geotools.exemplo;

import java.io.File;
import java.util.logging.Logger;

import org.geotools.api.data.FileDataStore;
import org.geotools.api.data.FileDataStoreFinder;
import org.geotools.api.data.SimpleFeatureSource;
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 * @author pedroRhamon
 */
public class Quickstart {
	
	private static final Logger LOGGER= org.geotools.util.logging.Logging.getLogger(Quickstart.class);
	
	public static void main(String[] args) throws Exception {
		LOGGER.info("Iniciando...");
		LOGGER.config("Bem vindo");
		LOGGER.info("java.util.logging.config.file="+System.getProperty("java.util.logging.config.file"));
		File file = JFileDataStoreChooser.showOpenFile("shp", null);
		if(file == null) {
			return;
		}
		
		LOGGER.config("Arquivo selecionado: " + file);
		
		FileDataStore store = FileDataStoreFinder.getDataStore(file);
		SimpleFeatureSource featureSource = store.getFeatureSource();
	}

}
