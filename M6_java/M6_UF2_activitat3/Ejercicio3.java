package m6_UF2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejercicio3 {
	private String usuari;
	private String contrasenya;
	private Connection con = null;
    private Statement stm = null;
	
	public Ejercicio3(String usuari, String contrasenya) {
		this.usuari = usuari;
		this.contrasenya = contrasenya;
	}
	
	public String getMensajeStatement() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbdm6", "usuari", "usuari");
			try {
	        	stm = con.createStatement();
	        	Boolean rs = stm.execute("SELECT usuari FROM usuari WHERE usuari = '"+usuari+"' AND contrasenya = '"+contrasenya+"'");
	        	
	        	if (rs) {
	        		stm.close();
	        		return "login correcto";
	        	}
	        }
	        catch (SQLException e) {
	        	return "login incorrecto";
	        }
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login incorrecto";
	}
	
	public String getMensajePreparedStatement() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testbdm6", "usuari", "usuari");
			try {
				PreparedStatement sentencia = con.prepareStatement("SELECT usuari FROM usuari WHERE usuari = ? AND contrasenya = ?");
				sentencia.setString(1, usuari);
				sentencia.setString(2, contrasenya);
				ResultSet rs = sentencia.executeQuery();
				
				if (rs.next()) {
					stm.close();
	        		return "login correcto";
				}
				stm.close();
	        }
	        catch (SQLException e) {
	        	return "login incorrecto";
	        } 
		} catch (Exception e) {
			return e.getMessage();
		}
		return "login incorrecto";
	}
}
