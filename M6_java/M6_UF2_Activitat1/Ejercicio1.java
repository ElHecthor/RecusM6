package m6_UF2;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
 
public class Ejercicio1 {
    public static void main(String[] args) {
        Connection connection = null;
        Statement stmt = null;
        try
        {
        	Scanner scan = new Scanner(System.in);
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Cargado");
            System.out.println("Conectando al servidor...");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbdm6", "root", "");
            System.out.println("Conectado");
            stmt = connection.createStatement();
            
            /*
             * MENU:
             */
            while (true) {
            	System.out.println("Menu:");
                System.out.println("  1. Insertar");
                System.out.println("  2. Eliminar (SQL)");
                System.out.println("  3. Modificar (SQL)");
                String respuesta = scan.nextLine();
                
                if (respuesta.equals("1")) {
                	System.out.println("    Tabla: (alumne, poblacio)");
                	String tabla = scan.nextLine();
                	
                	if (tabla.equals("alumne")) { // Insertar Alumne
                		try {
                			System.out.println("    DNI: (9 caracteres)");
                        	String dni = scan.nextLine();
                        	System.out.println("    Nom: ");
                        	String nom = scan.nextLine();
                        	System.out.println("    Año de Nacimiento: (AAAA-MM-DD)");
                        	String fecha = scan.nextLine();
                        	System.out.println("    Adreça Postal:");
                        	String carrer = scan.nextLine(); 
                        	System.out.println("    Codi Postal:");
                        	String codiPostal = scan.nextLine();
//                        	System.out.println("    Poblacion:");
//                        	String poblacion = scan.nextLine();
                        	
                        	String valores = "'"+dni+"', '"+nom+"', '"+fecha+"' , '"+carrer+"', '"+codiPostal+"'";
                        	insertar(stmt, tabla, valores);
                		} catch (MySQLIntegrityConstraintViolationException e) {
                			System.out.println("¡¡¡¡¡¡¡¡No existe el codigo postal!!!!!!!!");
                		} catch (Exception e) {
							System.out.println("Error inesperado");
						}
                    	
                	} else if (tabla.equals("poblacio")) { // Insertar Poblacion
                		System.out.println("    Codi Postal:");
                    	String codePostal = scan.nextLine();
                    	System.out.println("    Nom: ");
                    	String nom = scan.nextLine();
                    	
                    	String valores = "'"+codePostal+"', '"+nom+"'";
                    	insertar(stmt, tabla, valores);
                	} else {
                		break;
                	}
                	
                } else if (respuesta.equals("2")) {
                	System.out.println("    Tabla: (alumne, poblacio)");
                	String tabla = scan.nextLine();
                	
                	if (tabla.equals("alumne") || tabla.equals("poblacio")) {
						System.out.println("DELETE FROM "+tabla+" WHERE... (inserte parametros):");
						String parametros = scan.nextLine();
						
						borrar(stmt, tabla, parametros);
					} else {
						break;
					}
                	
                } else if (respuesta.equals("3")) {
                	System.out.println("    Tabla: (alumne, poblacio)");
                	String tabla = scan.nextLine();
                	
                	if (tabla.equals("alumne") || tabla.equals("poblacio")) {
						System.out.println("UPDATE "+tabla+" SET... (inserte parametros) ...WHERE...:");
						String parametros1 = scan.nextLine();
						System.out.println("UPDATE "+tabla+" SET "+parametros1+" WHERE...(inserte parametros):");
						String parametros2 = scan.nextLine();
						
						modificar(stmt, tabla, parametros1, parametros2);
					} else {
						break;
					}
                	
                } else {
                	break;
                }
                
                System.out.println("\n\n\n\n");
            }
            //********************************************************/
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {   
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void insertar(Statement stmt, String tabla, String valores) throws SQLException {
    	stmt.execute("INSERT INTO "+tabla+" VALUES ("+valores+")");
    }
    
    public static void borrar(Statement stmt, String tabla, String parametros) throws SQLException {
    	stmt.execute("DELETE FROM "+tabla+" WHERE "+parametros);
    }
    
    public static void modificar(Statement stmt, String tabla, String parametros1, String parametros2) throws SQLException {
    	stmt.execute("UPDATE "+tabla+" SET "+parametros1+" WHERE "+parametros2);
    }
}