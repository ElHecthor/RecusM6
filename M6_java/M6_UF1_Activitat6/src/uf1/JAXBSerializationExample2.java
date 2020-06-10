package uf1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class JAXBSerializationExample2 {

	static String ROWS_XML_FILE = "src/rows.xml";
	
	public static void main(String[] args) throws JAXBException, IOException  {
		
		Scanner scan = new Scanner(System.in);
		int respuesta = 0;
		
		while(respuesta != 3) {
			System.out.println();
			System.out.println();
			
			System.out.println("Menu: ");
			System.out.println("1. sacar xml de objeto java");
			System.out.println("2. sacar objeto java de xml");
			respuesta = scan.nextInt();
			
			if (respuesta == 1) {
				ompleRows();
				
			} else if (respuesta == 2) {
				ompleObjeto();
				
			}
		}
	}
	
	private static void ompleObjeto() throws JAXBException {
		System.out.println();
		JAXBContext jaxbContext = JAXBContext.newInstance(Rows.class);       
	    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	    Rows rows = (Rows) jaxbUnmarshaller.unmarshal(new File(ROWS_XML_FILE));
	    System.out.println(rows);
	    
	    Row[] rowsGuau = rows.getRows();
	    for (int i = 0; i < rowsGuau.length; i++) {
			System.out.println("Row "+i+": " + rowsGuau[i].get_id());
		}
	    
	    System.out.println();
	}
	

	private static void ompleRows() throws JAXBException, IOException {

		JAXBContext context = JAXBContext.newInstance(Rows.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		// Atributos
		String[] ids = {"kabnasdkjh" , "dfsdfghfgdh", "dfghdfgh"};
		String[] uuids = {"00000000-A836-0000-0BB5-F1A836CA7B2F" , "000A8360-0BB5-0000-0BB5-F1A836CA7B2F", "00000000-0000-0000-0BB5-F1A836CA7B2F"};
		String[] adresses = {"https://example.org/bell.php" , "http://example.com/bit", "https://www.example.org/angle/base"};
		int[] positions = {0, 0, 0};


		// Elementos
		int[] anys = {2019, 2019, 2020};
		String[] universitat = {"UAB" , "UB", "AB"};
		double[] recursos_captats_vies = {39453124.12, 16253124.12, 162182735.12};
		double[] recursos_captats_vies_no = {162182735.12, 1621234524.12, 39453124.12};
		double[] recursos_captats_total = {53124.12, 163124.12, 162135.12};


		Row[] ArrayRows = new Row[3];

		for(int i=0; i<3; i++){
			ArrayRows[i] = new Row();
			ArrayRows[i].set_id(ids[i]);
			ArrayRows[i].set_uuid(uuids[i]);
			ArrayRows[i].set_address(adresses[i]);
			ArrayRows[i].set_position(positions[i]);


			ArrayRows[i].setAny(anys[i]);
			ArrayRows[i].setUniversitat(universitat[i]);
			ArrayRows[i].setRecursos_captats_via(recursos_captats_vies[i]);
			ArrayRows[i].setRecursos_captats_via_no(recursos_captats_vies_no[i]);
			ArrayRows[i].setRecursos_captats_total(recursos_captats_total[i]);
		}

		Rows rows = new Rows();
		rows.setRows(ArrayRows);

		//Mostrem el document XML generat por la sortida estandard
		marshaller.marshal(rows, System.out);

		FileOutputStream fos = new FileOutputStream(ROWS_XML_FILE);
		//guardem l'objecte serializat en un document XML
		marshaller.marshal(rows, fos);
		fos.close();

		Unmarshaller unmarshaller = context.createUnmarshaller();
		//Deserialitzem a partir de un document XML
		Rows rowsAux = (Rows) unmarshaller.unmarshal(new File(ROWS_XML_FILE));
		System.out.println("********* Rows carregat desde fitxer XML***************");
		//Mostrem l'objeto Java obtingut
		marshaller.marshal(rowsAux, System.out);
	}
}
