package org.starking.geotools.exemplo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/**
 * @author pedroRhamon
 */
public class ExportKML {

	public static void main(String[] args) throws IOException {

		Kml kml = new Kml();

		Placemark placemark = kml.createAndSetPlacemark();
		placemark.withName("Local");
		placemark.withDescription("Descrição do local");

		// Definindo a posição (longitude e latitude)
		Coordinate coord = new Coordinate(10.0, 20.0, 0.0);
		placemark.createAndSetPoint().addToCoordinates(coord.toString());

		// Salvando o KML
		try {
			kml.marshal(new File("C:\\caminho\\para\\seu\\arquivo.kml"));
			System.out.println("Arquivo KML exportado com sucesso.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
