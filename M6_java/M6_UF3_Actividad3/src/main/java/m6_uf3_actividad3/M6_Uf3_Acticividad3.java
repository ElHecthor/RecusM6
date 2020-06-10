/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m6_uf3_actividad3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;


public class M6_Uf3_Acticividad3 {
	public static String driver = "org.exist.xmldb.DatabaseImpl";
	public static Collection col = null;
	public static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/Proves";
	public static String usu = "admin";
	public static String usuPass = "admin";
	
	public static void main(String[] args) throws XMLDBException {
		Scanner teclado = new Scanner(System.in);
		String driver = "org.exist.xmldb.DatabaseImpl";

		try {
			Class cl = Class.forName(driver);
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);
		} catch (Exception e) {
			System.out.println("Error en inicialitzar la base de datos");
			e.printStackTrace();
		} col = DatabaseManager.getCollection(URI, usu, usuPass);
		if (col==null){
			System.out.println("No existe la coleccion");
		}

		XPathQueryService servei =(XPathQueryService) col.getService("XPathQueryService", "1.0");

		int opcion=0;
		while(opcion != 4){
			System.out.println("Diga una opcion:");
			System.out.println("1: Mostrar los empleados de un departamento");
			System.out.println("2: insertar departamento");
			System.out.println("3: eliminar departamento");
			System.out.println("4: modificar departamento");
			System.out.println("5: Salir");

			opcion = teclado.nextInt();
			teclado.nextLine();
			if(opcion ==1){
				buscarEmpleadoDeUnDepartamento(teclado,servei);
			}else if(opcion==2){
				insereixdep(teclado,servei);
			}else if(opcion==3){
				esborradep(teclado,servei);
			}else if(opcion==4){
				modificaDep();;
			}
		}
		col.close();

	}

	private static void mostrarDepartamentos(XPathQueryService servei) throws XMLDBException{
		ResourceSet result0 = servei.query("for $dep in /departamentos/DEP_ROW\nreturn $dep");
		//Recórrer les dades del recurs
		ResourceIterator i;
		i = result0.getIterator();
		if (!i.hasMoreResources())
			System.out.println("No recibe nada");
		while (i.hasMoreResources()) {
			Resource r = i.nextResource();
			System.out.println((String)r.getContent());

		}
	}

	public static void buscarEmpleadoDeUnDepartamento(Scanner teclado,XPathQueryService servei) throws XMLDBException{
		System.out.println("Diga el nombre del departamento");
		String departamento = teclado.next();
		String sentencia = "for $num in /departamentos/DEP_ROW[DNOMBRE='"+departamento+"']/DEPT_NO" + " let $emp := /EMPLEADOS/EMP_ROW[DEPT_NO=$num] return $emp" ;

		ResourceSet result = servei.query(sentencia);
		ResourceIterator i;

		i = result.getIterator();
		if (!i.hasMoreResources())
			System.out.println("No retorna nada");
		while (i.hasMoreResources()) {
			Resource r = i.nextResource();
			System.out.println((String)r.getContent());

		}
	}

	private static void esborradep(Scanner teclado,XPathQueryService servei) throws XMLDBException {
		mostrarDepartamentos(servei);

		System.out.println("Diga el numero del departamento");
		int num = teclado.nextInt();
		ResourceSet result =
				servei.query("update delete /departamentos/DEP_ROW[DEPT_NO="+num+"]");
		System.out.println("Se ha Borrado con exito");
		mostrarDepartamentos(servei);
	}

	private static void insereixdep(Scanner teclado,XPathQueryService servei) throws XMLDBException {
		System.out.println("nombre del departamento");
		String departamento = teclado.next();
		System.out.println("numero del departamento");
		int num = teclado.nextInt();
		System.out.println("localizacion del departamento");
		String loc = teclado.next();

		ResourceSet result =
				servei.query("update insert\n" +"<DEP_ROW><DEPT_NO>"+num+"</DEPT_NO>"+ "<DNOMBRE>"+departamento+"</DNOMBRE>"
						+ "<LOC>"+loc+"</LOC></DEP_ROW> into /departamentos");
		
		System.out.println("Se ha insertado con exito");
		mostrarDepartamentos(servei);
	}

	public static void modificaDep()throws XMLDBException{
		Scanner respuesta1 = new Scanner(System.in);
		Scanner respuesta22 = new Scanner(System.in);
		System.out.println("numero del departamento a modificar:");
		int numDep = respuesta1.nextInt();
		
		System.out.println("numero del departamento nuevo:");
		int num = respuesta1.nextInt();
		
		System.out.println("nombre departamento:");
		String nom = respuesta22.nextLine();
		
		System.out.println("Lloc:");
		String loc = respuesta22.nextLine();
		
		String query = "update replace /departamentos/DEP_ROW[DEPT_NO ="+ numDep +"] with <DEP_ROW><DEPT_NO>" + num + "</DEPT_NO>" + "<DNOMBRE>" + nom + "</DNOMBRE><LOC>" + loc +"</LOC></DEP_ROW>";
		ferConsulta(query);
	}
	
	public static void ferConsulta(String query)throws XMLDBException {
		try {
			Class cl = Class.forName(driver);
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);
		} catch(Exception e) {
			System.out.println("Error en inicialitzar la base de dades eXist");
			e.printStackTrace();
		}

		col = DatabaseManager.getCollection(URI, usu, usuPass);
		if(col == null) 
			System.out.println("*** LA COLLECCIÓ NO EXISTEIX ***");

		XPathQueryService servei = (XPathQueryService) col.getService("XPathQueryService", "1.0");
		ResourceSet result = servei.query(query);
		
		ResourceIterator i;
		i = result.getIterator();
		if(!i.hasMoreResources())
			System.out.println("LA CONSULTA NO TORNA RES");
		while(i.hasMoreResources()) {
			Resource r = i.nextResource();
			System.out.println((String)r.getContent());
		}
		
		col.close();
	}

}
