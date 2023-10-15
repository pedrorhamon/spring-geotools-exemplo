package org.starking.geotools.exemplo;

import org.gdal.ogr.DataSource;
import org.gdal.ogr.Driver;
import org.gdal.ogr.Feature;
import org.gdal.ogr.FieldDefn;
import org.gdal.ogr.Layer;
import org.gdal.ogr.ogr;

/**
 * @author pedroRhamon
 */
public class ExportShapefileGDAL {

	public static void main(String[] args) {
		ogr.RegisterAll();

		String nomeDoArquivo = "caminho/do/seu/arquivo.shp";
		String nomeDaCamada = "nome_da_camada";
		String formatoDeSaida = "ESRI Shapefile";
		String caminhoDeSaida = "caminho/do/seu/arquivo_de_saida.shp";

		DataSource fonteDeDados = ogr.Open(nomeDoArquivo, 0);
		if (fonteDeDados == null) {
			System.err.println("Não foi possível abrir a fonte de dados.");
			System.exit(1);
		}

		Driver driverSaida = ogr.GetDriverByName(formatoDeSaida);
		if (driverSaida == null) {
			System.err.println("Driver de saída não encontrado.");
			System.exit(1);
		}

		DataSource fonteDeDadosSaida = driverSaida.CreateDataSource(caminhoDeSaida);
		if (fonteDeDadosSaida == null) {
			System.err.println("Não foi possível criar a fonte de dados de saída.");
			System.exit(1);
		}

		Layer camada = fonteDeDados.GetLayerByName(nomeDaCamada);
		Layer novaCamada = fonteDeDadosSaida.CreateLayer(nomeDaCamada, null, camada.GetGeomType());

		// Adiciona os campos à nova camada
		for (int i = 0; i < camada.GetLayerDefn().GetFieldCount(); i++) {
			FieldDefn fieldDefn = camada.GetLayerDefn().GetFieldDefn(i);
			novaCamada.CreateField(fieldDefn);
		}

		Feature feature;
		while ((feature = camada.GetNextFeature()) != null) {
			novaCamada.CreateFeature(feature);
			feature.delete();
		}

		fonteDeDados.delete();
		fonteDeDadosSaida.delete();
	}
}
