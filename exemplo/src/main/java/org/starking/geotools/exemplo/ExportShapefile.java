package org.starking.geotools.exemplo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.geotools.api.feature.simple.SimpleFeature;
import org.geotools.api.feature.simple.SimpleFeatureType;
import org.geotools.api.referencing.FactoryException;
import org.geotools.api.referencing.NoSuchAuthorityCodeException;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

/**
 * @author pedroRhamon
 */
public class ExportShapefile {

	public static void main(String[] args) throws IOException, NoSuchAuthorityCodeException, FactoryException {
		// Criar um novo Shapefile
		File newShapefile = new File("caminho/do/novo/shapefile.shp");

		// Configurar o tipo de dado para o novo Shapefile
		SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
		builder.setName("MeuShapefile");
		builder.setCRS(CRS.decode("EPSG:4326")); // Define o sistema de coordenadas, substitua com o CRS desejado
//        builder.add("geometry", com.vividsolutions.jts.geom.Geometry.class);
		builder.add("geometry", org.locationtech.jts.geom.Geometry.class);
		builder.add("atributo1", String.class);
		builder.add("atributo2", Integer.class);

		SimpleFeatureType featureType = builder.buildFeatureType();

		// Criar o DataStore para o novo Shapefile
		Map<String, Serializable> params = new HashMap<>();
		params.put("url", newShapefile.toURI().toURL());
		params.put("create spatial index", Boolean.TRUE);
		ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
		org.geotools.data.shapefile.ShapefileDataStore newDataStore = (org.geotools.data.shapefile.ShapefileDataStore) dataStoreFactory
				.createNewDataStore(params);

		newDataStore.createSchema(featureType);

		// Adicionar Features ao Shapefile (substitua isso com suas próprias features)
		SimpleFeatureBuilder featureBuilder = new SimpleFeatureBuilder(featureType);
		SimpleFeatureCollection collection = newDataStore.getFeatureSource().getFeatures();

		// Criar uma Geometry (por exemplo, um ponto)
		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate coordinate = new Coordinate(10.0, 20.0); // Latitude e longitude de exemplo
		Point ponto = geometryFactory.createPoint(coordinate);

		// Adicionar a Geometry à feature
		featureBuilder.add(ponto);

		// Agora, a feature contém a Geometry
		SimpleFeature featureGeometry = featureBuilder.buildFeature(null);

		featureBuilder.add(featureGeometry); // Substitua com sua geometria
		featureBuilder.add("Valor1"); // Substitua com seus próprios valores
		featureBuilder.add(123); // Substitua com seus próprios valores
		SimpleFeature feature = featureBuilder.buildFeature(null);
		((SimpleFeatureBuilder) collection).add(feature);

		// Salvar as alterações no Shapefile
		newDataStore.dispose();
	}
}
