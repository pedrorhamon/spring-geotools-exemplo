package org.starking.geotools.exemplo;

import org.gdal.ogr.DataSource;
import org.gdal.ogr.ogr;

/**
 * @author pedroRhamon
 */
public class ExportShapefileGDAL {

	public static void main(String[] args) {
		ogr.RegisterAll();

		// Abre o shapefile
		String shapefilePath = "C:\\Users\\pedro\\Downloads\\shapefileGDAL\\shapefile.shp";
		DataSource ds = ogr.Open(shapefilePath);

		if (ds == null) {
			System.err.println("Erro ao abrir o shapefile.");
			System.exit(1);
		}
	}

}
