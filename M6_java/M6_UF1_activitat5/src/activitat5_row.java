import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class activitat5_row {

	public static void main(String[] args) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError, IOException, ParserConfigurationException, SAXException {
		Scanner sc = new Scanner(System.in);

		File file = new File("rows.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);

		Element nodeArrel = doc.getDocumentElement();

		int opcion = 0;
		while (opcion != 5) {
			System.out.println("Menu");
			System.out.println("1. Leer Fichero");
			System.out.println("2. Elimina Elemento");
			System.out.println("3. Modifica Elemento");
			System.out.println("4. Añade Elemento");
			System.out.println("-------------------------");
			System.out.println();
			System.out.println("5. Salir");

			opcion = sc.nextInt();
			sc.nextLine();

			if (opcion == 1) {
				leerNodos(nodeArrel);
			} else if (opcion == 2) {
				eliminar(sc, doc);
			} else if (opcion == 3) {
				modificar(doc, sc);
			} else if (opcion == 4) {
				afegir(sc, doc);
			} 
		}
	}

	
	private static void anadeAtributo(Scanner sc, Document doc) {
		// TODO Auto-generated method stub
		
	}

	private static void modificarAtributo(Scanner sc, Document doc) {
		// TODO Auto-generated method stub
		
	}

	private static void eliminarAtributo(Scanner sc, Document doc) {
		System.out.println("Introduzca el elemento a filtrar:");
		String elemento3 = sc.nextLine();
		System.out.println("Introduzca el atributo que quiere eliminar:");
		String atributo = sc.nextLine();
		
		NodeList items = doc.getElementsByTagName(elemento3);
		for (int ix = 0; ix < items.getLength(); ix++) {
			items.item(ix).hasAttributes();
		}
	}

	private static void afegir(Scanner sc, Document doc) throws IOException, ParserConfigurationException, SAXException, TransformerFactoryConfigurationError {
		
		Element nodeArrel = doc.getDocumentElement();
		
		try {
			  Element row = doc.createElement("row");
			  nodeArrel.appendChild(row);
			  			  
			  // atributos
			  Attr attr = doc.createAttribute("_id");
			  Attr attr1 = doc.createAttribute("_uuid");
			  Attr attr2 = doc.createAttribute("_position");
			  Attr attr3 = doc.createAttribute("_address");
			  
			  System.out.println("Introduce id:");
			  String teclado = sc.nextLine();
			  attr.setValue(teclado);
			  row.setAttributeNode(attr);
			  
			  System.out.println("Introduce uuid:");
			  teclado = sc.nextLine();
			  attr1.setValue(teclado);
			  row.setAttributeNode(attr1);
			  		
			  System.out.println("Introduce position:");
			  teclado = sc.nextLine();
			  attr2.setValue(teclado);
			  row.setAttributeNode(attr2);
			  
			  System.out.println("Introduce address:");
			  teclado = sc.nextLine();
			  attr3.setValue(teclado);
			  row.setAttributeNode(attr3);
			  
			  
			  
			  // Elementos
			  Element nodeAny = doc.createElement("any");
			  System.out.println("Introduce Año:");
			  teclado = sc.nextLine();
			  nodeAny.appendChild(doc.createTextNode(teclado));
			  row.appendChild(nodeAny);
			  
			  Element nodeUni = doc.createElement("universitat");
			  System.out.println("Introduce Universidad: ");
			  teclado = sc.nextLine();
			  nodeUni.appendChild(doc.createTextNode(teclado));
			  row.appendChild(nodeUni);
			 
			  Element nodeRecCaVia = doc.createElement("recursos_captats_via");
			  System.out.println("Introduce recursos captats via: ");
			  teclado = sc.nextLine();
			  nodeRecCaVia.appendChild(doc.createTextNode(teclado));
			  row.appendChild(nodeRecCaVia);
			  
			  Element nodeRecCaViaNo = doc.createElement("recursos_captats_via_no");
			  System.out.println("Introduce recursos captats via no: ");
			  teclado = sc.nextLine();
			  nodeRecCaViaNo.appendChild(doc.createTextNode(teclado));
			  row.appendChild(nodeRecCaViaNo);
			  
			  Element nodeRecCaTo = doc.createElement("recursos_captats_total");
			  System.out.println("Introduce recursos captats total: ");
			  teclado = sc.nextLine();
			  nodeRecCaTo.appendChild(doc.createTextNode(teclado));
			  row.appendChild(nodeRecCaTo);
			  
			  guardar(sc, doc);
			  
		} catch(Exception e) {
			   e.printStackTrace();
		}
	}
	
	private static void guardar(Scanner sc, Document doc )throws TransformerFactoryConfigurationError, TransformerException, TransformerConfigurationException {
		
		System.out.println("Guardar? si/NO");
		
		if(sc.next().equalsIgnoreCase("si")){
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(doc);
			  StreamResult result = new StreamResult(new File("rows.xml"));
			  transformer.transform(source, result);
		}
	}
	
	private static void eliminar(Scanner sc, Document doc) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {

		System.out.println("Introduce la id: ");
		String id = sc.nextLine();
		Element element = null;
		Node nodeArrel = doc.getFirstChild();
		NodeList list = nodeArrel.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
		    Node node = list.item(i);
		    
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
		        Element element2 = (Element) node;
		        if(element2.getAttribute("_id").equalsIgnoreCase(id)){
		        	element = element2;
		        }
		    }
		}
		
		if(element != null){
			leerNodos(element);
			element.getParentNode().removeChild(element);
			guardar(sc, doc);
			
		}
	}
	
	//Llegeix el xml
	public static void leerNodos(Element element) {
		NodeList nlist = element.getChildNodes();
		
		for (int i = 0; i < nlist.getLength(); i++) {
			if (nlist.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element esElement = (Element) nlist.item(i);
				
				if (esElement.hasAttributes()) {
					NamedNodeMap nodeMap = nlist.item(i).getAttributes();
					
					System.out.print(esElement.getNodeName() + " : ");
					for (int j = 0; j < nodeMap.getLength(); j++) {
						Node node = nodeMap.item(j);
						System.out.print("\n	" + node.getNodeName() + ": " + node.getNodeValue());
						
					}
					leerNodos(esElement);
					
				} else {
					System.out.println();
					System.out.print("\t\t"+esElement.getNodeName() + ": " );
					leerNodos(esElement);	
					
				}
			} else {
				System.out.print(nlist.item(i).getTextContent());
				
			}
		}
	}
	
	//Modifica els valors
	private static void modificar(Document doc, Scanner sc) throws TransformerFactoryConfigurationError, TransformerException, TransformerConfigurationException {

		System.out.println("Nombre Elemento: ");
		String element = sc.nextLine();
		System.out.println("Nombre Elemento Nuevo: ");
		String elementNuevo = sc.nextLine();

		NodeList items = doc.getElementsByTagName(element);
		for (int ix = 0; ix < items.getLength(); ix++) {
			doc.renameNode(items.item(ix), null, elementNuevo);
		}

		guardar(sc, doc);
	}

}
