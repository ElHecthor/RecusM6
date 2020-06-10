package m6_UF2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio2 {

	public Ejercicio2() throws ClassNotFoundException, SQLException {
		Connection con = null;
        Statement stm = null;
        
		Scanner scan = new Scanner(System.in);
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver Cargado");
        System.out.println("Conectando al servidor...");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbdm6", "usuari", "usuari");
        System.out.println("Conectado");
        
        
        try {
        	con.setAutoCommit(false);
        	stm = con.createStatement();
        	stm.execute("DELETE FROM poblacio WHERE poblacio.codiPostal = 2");
        	stm.execute("DELETE FROM poblacio WHERE poblacio.codiPostal = 2");
        	stm.execute("INSERT INTO poblacio VALUES (2, 'Tarragona')");
        	stm.execute("INSERT INTO poblacio VALUES (10, 'Reus')");
        	con.commit();
        	con.setAutoCommit(true);
        }
        catch (SQLException e) {
        	con.rollback();
        	System.out.println("Error en la transaccion");
        	System.out.println(e);
        } finally {
        	if (stm!=null && !stm.isClosed()) { 
        		System.out.println("Transaccion terminada");
        		stm.close();
        	}
        }
	}
}
