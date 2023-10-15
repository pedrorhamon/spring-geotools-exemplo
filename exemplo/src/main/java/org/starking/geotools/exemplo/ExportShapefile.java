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
import org.geotools.api.referencing.crs.CoordinateReferenceSystem;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.feature.DefaultFeatureCollection;
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

	private static final String EPSG4326 = "GEOGCS[\"WGS 84\",DATUM[\"WGS_1984\","
			+ "SPHEROID[\"WGS 84\",6378137,298.257223563,AUTHORITY[\"EPSG\",\"7030\"]]"
			+ ",AUTHORITY[\"EPSG\",\"6326\"]],PRIMEM[\"Greenwich\",0,AUTHORITY[\"EPSG\","
			+ "\"8901\"]],UNIT[\"degree\",0.01745329251994328,AUTHORITY[\"EPSG\",\"9122\"]]"
			+ ",AUTHORITY[\"EPSG\",\"4326\"]]";
   
	public static void main(String[] args) throws IOException, NoSuchAuthorityCodeException, FactoryException {
		
		 CoordinateReferenceSystem worldCRS = CRS.parseWKT(EPSG4326);
		// Criar um novo Shapefile
		File newShapefile = new File("C:\\Users\\pedro\\Downloads\\shapefile\\shapefile.shp");
		
//		CoordinateReferenceSystem cabedeloCRS = CRS.decode("EPSG:4326");

		// Criar uma Geometry (por exemplo, um ponto)
		GeometryFactory geometryFactory = new GeometryFactory();
		Coordinate coordinate = new Coordinate(10.0, 20.0); // Latitude e longitude de exemplo
		Point ponto = geometryFactory.createPoint(coordinate);

		
		// Configurar o tipo de dado para o novo Shapefile
		SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
		builder.setName("MeuShapefile");
		builder.setCRS(worldCRS); // Define o sistema de coordenadas, substitua com o CRS desejado
//        builder.add("geometry", com.vividsolutions.jts.geom.Geometry.class);
//		builder.add("geometry", org.locationtech.jts.geom.Geometry.class);
		builder.add("atributo1", String.class);
		builder.add("atributo2", Integer.class);
		builder.add("geometry", Point.class);

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
		DefaultFeatureCollection collection = new DefaultFeatureCollection(null, featureType);

		// Adicionar a Geometry à feature
		featureBuilder.add(ponto);

		// Agora, a feature contém a Geometry
		SimpleFeature featureGeometry = featureBuilder.buildFeature(null);

		featureBuilder.add(featureGeometry); // Sua geometria
		featureBuilder.add("Valor1"); // Seus próprios valores
		featureBuilder.add(123); // Seus próprios valores
		SimpleFeature feature = featureBuilder.buildFeature(null);
		collection.add(feature);

		// Salvar as alterações no Shapefile
		newDataStore.dispose();
		
		System.out.println("Arquivo exportado");
	}
}
