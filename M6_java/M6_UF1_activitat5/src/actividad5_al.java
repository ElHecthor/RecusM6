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

public class actividad5_al {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		Scanner sc = new Scanner(System.in);
		
		File file = new File("alumnes.xml");
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

	private static void afegir(Scanner sc, Document doc) throws IOException, ParserConfigurationException, SAXException, TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		
		Element nodeArrel = doc.getDocumentElement();
		Element alumne = doc.createElement("alumne");
		nodeArrel.appendChild(alumne);

		Attr attr = doc.createAttribute("id");
		System.out.println("Introduce id:");
		String teclatUsuari = sc.nextLine();
		attr.setValue(teclatUsuari);
		alumne.setAttributeNode(attr);

		Element nodeNom = doc.createElement("nom");
		System.out.println("Introduce nombre:");
		teclatUsuari = sc.nextLine();
		nodeNom.appendChild(doc.createTextNode(teclatUsuari));
		alumne.appendChild(nodeNom);

		Element nodeCognom1 = doc.createElement("cognom1");
		System.out.println("Introduce Apellido: ");
		teclatUsuari = sc.nextLine();
		nodeCognom1.appendChild(doc.createTextNode(teclatUsuari));
		alumne.appendChild(nodeCognom1);

		Element nodeCognom2 = doc.createElement("cognom2");
		System.out.println("Introduce segundo apellido: ");
		teclatUsuari = sc.nextLine();
		nodeCognom2.appendChild(doc.createTextNode(teclatUsuari));
		alumne.appendChild(nodeCognom2);

		Element nodeNota = doc.createElement("notaFinal");
		System.out.println("Introduce la nota: ");
		teclatUsuari = sc.nextLine();
		nodeNota.appendChild(doc.createTextNode(teclatUsuari));
		alumne.appendChild(nodeNota);

		guardar(sc, doc);
	}
	
	private static void guardar(Scanner sc, Document doc )throws TransformerFactoryConfigurationError, TransformerException, TransformerConfigurationException {
		
		System.out.println("Guardar? si/NO");
		
		if(sc.next().equalsIgnoreCase("si")){
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(doc);
			  StreamResult result = new StreamResult(new File("alumnes.xml"));
			  transformer.transform(source, result);
		}
	}
	
	//Elimina nodes de l'arrel
	private static void eliminar(Scanner sc, Document doc) throws TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError {

		System.out.println("Introduce la id: ");
		int id = sc.nextInt();
		Element element = null;
		Node nodeArrel = doc.getFirstChild();
		NodeList list = nodeArrel.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
		    Node node = list.item(i);
		    
		    if (node.getNodeType() == Node.ELEMENT_NODE) {
		        Element element2 = (Element) node;
		        if(element2.getAttribute("id").equalsIgnoreCase(Integer.toString(id))){
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
