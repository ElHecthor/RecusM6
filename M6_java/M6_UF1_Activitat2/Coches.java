package m9;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Coches {
	//Declaració del fitxer
	static File fitxer = new File("coches.txt");
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner scan = new Scanner(System.in);
		FileOutputStream fileout = new FileOutputStream(fitxer); //Crea el flux de sortida
		ObjectOutputStream dataOuComarq = new ObjectOutputStream(fileout); //Connectar el flux de bytes al flux de dades
		
		Coche coche = new Coche("BMW", "Serie 3", 1998, "4824HYL");
		escribirFichero(coche, fileout, dataOuComarq);
		coche = new Coche("BMW", "Serie 3", 2000, "SEY789A");
		escribirFichero(coche, fileout, dataOuComarq);
		coche = new Coche("Audi", "A3", 2004, "A9F8T62");
		escribirFichero(coche, fileout, dataOuComarq);
		coche = new Coche("Clio", "Ci", 1998, "A7T149J");
		escribirFichero(coche, fileout, dataOuComarq);
		
		while(true) {
			System.out.println("Menu:");
			System.out.println("  1. Crear Coche");
			System.out.println("  2. Buscar Coche segun...");
			System.out.println("  3. Mirar todos los coches");
			String respuesta = scan.nextLine();
			int respuestaNum = Integer.parseInt(respuesta);
			
			if (respuesta.equals("1")) {
				System.out.print("Marca: ");
				String marca = scan.nextLine();
				System.out.print("Modelo: ");
				String modelo = scan.nextLine();
				System.out.print("Año: ");
				int ano = scan.nextInt();
				scan.nextLine();
				System.out.print("Matricula: ");
				String matricula = scan.nextLine();
				
				Coche cocheCreado = new Coche(marca, modelo, ano, matricula);
				escribirFichero(cocheCreado, fileout, dataOuComarq);
				
			} else if (respuesta.equals("2")) {
				System.out.println("    1. Marca");
				System.out.println("    2. Modelo");
				System.out.println("    3. Año");
				System.out.println("    4. Matricula");
				respuesta = scan.nextLine();
				respuestaNum = Integer.parseInt(respuesta);
				String valor = null;
				
				if (respuesta.equals("1")) {
					System.out.print("Marca: ");
					valor = scan.nextLine();
					
				} else if (respuesta.equals("2")) {
					System.out.print("Modelo: ");
					valor = scan.nextLine();
					
				} else if (respuesta.equals("3")) {
					System.out.print("Año: ");
					valor = scan.nextLine();
					
				} else if (respuesta.equals("4")) {
					System.out.print("Matricula: ");
					valor = scan.nextLine();
					
				} else {
					System.out.println("ERROR");
					break;
				}
				leerFichero(respuestaNum, valor);
				
			} else if (respuesta.equals("3")) {
				leerFichero(0, "");
			} else {
				break;
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
		}
		
		dataOuComarq.close();//Tanca el stream de sortida
	}
	
	public static void escribirFichero(Coche coche, FileOutputStream fileout, ObjectOutputStream dataOuComarq) throws IOException {
		//Les dades per generar els objectes Comarca
		dataOuComarq.writeObject(coche);//L'escriu al fixer
	}
	
	public static void leerFichero(int modo, String respuesta) throws IOException, ClassNotFoundException {
		Coche coche = null;
		//Crea el flux d'entrada
		FileInputStream filein = new FileInputStream(fitxer);
		//Connectar el flux de bytes al flux de dades
		ObjectInputStream dataInComarq = new ObjectInputStream(filein);

		try {
			while (true){//Llegeix el fitxer
				coche = (Coche) dataInComarq.readObject();
				String año = coche.getAño() + "";
				if (modo == 1 && respuesta.equals(coche.getMarca())) {
					imprimirCoche(coche);
				} else if (modo == 2 && respuesta.equals(coche.getModelo())) {
					imprimirCoche(coche);}
				else if (modo == 3 && respuesta.equals(año)) {
					imprimirCoche(coche);}
				else if (modo == 4 && respuesta.equals(coche.getMatricula())) {
					imprimirCoche(coche);
				} else if (modo == 0) {
					imprimirCoche(coche);
				}
			}
		} catch (EOFException eo) {}
		dataInComarq.close();//Tanca el stream d'entrada
	}
	
	public static void imprimirCoche(Coche coche) {
		System.out.println("Marca: " + coche.getMarca() + "	|| Modelo: " + coche.getModelo() + "	|| Año: " + coche.getAño() + "	|| Matricula: " + coche.getMatricula());
	}
}

class Coche implements Serializable{
	String marca;
	String modelo;
	int año;
	String matricula;
	
	public Coche(String marca, String modelo, int año, String matricula) {
		this.marca = marca;
		this.modelo = modelo;
		this.año = año;
		this.matricula = matricula;
	}
	
	public void setMarca(String marca){this.marca = marca;}
	public void setModelo(String modelo){this.modelo = modelo;}
	public void setAño(int año){this.año = año;}
	public void setMatricula(String matricula){this.matricula = matricula;}
	
	public String getMarca(){return marca;}
	public String getModelo(){return modelo;}
	public int getAño(){return año;}
	public String getMatricula(){return matricula;}
}