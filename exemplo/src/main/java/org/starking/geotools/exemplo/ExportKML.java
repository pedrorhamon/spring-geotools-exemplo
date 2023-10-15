package org.starking.geotools.exemplo;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import de.micromata.opengis.kml.v_2_2_0.*;

/**
 * @author pedroRhamon
 */
public class ExportKML {

	public static void main(String[] args) {

		Kml kml = new Kml();

		Placemark placemark = kml.createAndSetPlacemark();
		placemark.withName("Local");
		placemark.withDescription("Descrição do local");
		
		// Definindo a posição (longitude e latitude)
        Coordinate coord = new Coordinate(10.0, 20.0, 0.0);
        placemark.createAndSetPoint().addToCoordinates(coord);

		try {


		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
