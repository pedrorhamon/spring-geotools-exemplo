package org.starking.geotools.exemplo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

/**
 * @author pedroRhamon
 */
public class ExportKML {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		// Criando um objeto KML
		Kml kml = new Kml();

		// Criando uma lista de coordenadas
		List<Coordinate> coordinates = new ArrayList<>();
		coordinates.add(new Coordinate(-7.0356, -34.8339, 0.0)); // Cabedelo
		coordinates.add(new Coordinate(-7.2383, -35.9718, 0.0)); // Campina Grande
		coordinates.add(new Coordinate(-7.1250, -34.8733, 0.0)); // João Pessoa

		// Criando um documento KML
		Document document = kml.createAndSetDocument();

		// Adicionando os pontos ao documento KML
		for (int i = 0; i < coordinates.size(); i++) {
			Coordinate coordinate = coordinates.get(i);
			Placemark placemark = document.createAndAddPlacemark();
			placemark.withName("Ponto " + (i + 1));
			Point point = placemark.createAndSetPoint();
			point.addToCoordinates(coordinate.getLongitude(), coordinate.getLatitude());
		}

		// Salvando o KML
		kml.marshal(new File("C:\\Users\\pedro\\Downloads\\arquivo.kml"));

		System.out.println("Arquivo KML exportado com sucesso.");
	}
}
