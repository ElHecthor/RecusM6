/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hector
 */
import java.util.ArrayList;

import java.sql.Connection;  
import java.sql.DriverManager;  

import ElsMeusBeans.Comanda;
import ElsMeusBeans.Venda;
import ElsMeusBeans.Producte;
import ElsMeusBeans.BaseDades;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
import javax.tools.JavaCompiler;

public class Exemple {
    
    static BaseDades bd;
    
    public static void main(String[] args) throws SQLException, Exception {
        /*     MYSQL*/
        // String urldb = "jdbc:mysql://localhost:3306/bdom6";
        /*     SQLite*/
        String urldb = "jdbc:sqlite:C:\\AAA_DB Browser for SQLite\\databases\\db1.db";
        /*     Oracle*/
        //String urldb = "jdbc:oracle:thin:@localhost:1521:XE"; 
        
        String usuari = "root";
        String contrasenya = "";
        String driver = "com.mysql.jdbc.Driver";
        
        Scanner scan = new Scanner(System.in);
        //Es crear un objecte BaseDades
        bd = new BaseDades(urldb, usuari, contrasenya, driver);
        bd.setCrearConnexio();//Es crea la connexió a la base de dades
        
        if (bd.getCrearConnexio()) {
            System.out.println("Connectat");
            
            System.out.println("======================================");
            System.out.println("LLISTA INICIAL DE PRODUCTES");
            VeureProductes(bd);
            
            int resp = 0;
            while(resp != 1){
                
                System.out.println("Menu: ");
                System.out.println("1. Hacer Venta");
                System.out.println("2. Añadir producto");
                resp = scan.nextInt();
                
                if (resp == 2) {
                    anadirProducto();
                }
            }
            
            //Crea una venda
            System.out.println("======================================");
            System.out.println("ES CREA VENDA DE ID 3 AMB QUANTITAT 2 ");
            
            System.out.println("Producte ID: ");
            int producteID = scan.nextInt();
            System.out.println("Quantitat: ");
            int quantitat = scan.nextInt();
            CrearVenda(bd, producteID, quantitat);//Si no hi ha estoc no es crea venda
            
            System.out.println("======================================");
            System.out.println("LLISTA DE PRODUCTES DESPRÉS DE CREAR VENDA");
            VeureProductes(bd);
            
            System.out.println("======================================");
            System.out.println("LLISTA DE VENDES");
            VeureVendes(bd);
            
            System.out.println("======================================");
            System.out.println("LLISTA DE COMANDES");
            VeureComandes(bd);
            
        } else {
            System.out.println("No connectat a res");
        }
        //Tancar connexio
        bd.tancarConnexio();
        
    }//Fi main
    
    //--------------------------------------------------------------------------
    //Mostrar els productes
    private static void VeureProductes (BaseDades bd) {
        ArrayList <Producte> llista = new ArrayList <Producte>();
        llista = bd.consultaPro("SELECT * FROM PRODUCTE");
        if (llista != null)
            for (int i = 0; i<llista.size(); i++) {
                Producte p = (Producte) llista.get(i);
                System.out.println("ID=>"+p.getIdproducte()+": "+p.getDescripcio()+"* Estoc: "+p.getStockactual()+"* Pvp: "+p.getPvp()+" Estoc Mínim: "+p.getStockminim());
            }
    }//Fi VeureProductes
    
    //--------------------------------------------------------------------------
    //S'insereix una venda
    private static void CrearVenda (BaseDades bd, int idproducte, int quantitat) {
        Producte prod = bd.consultarUnProducte(idproducte);
        java.sql.Date dataActual = getCurrentDate();//Data SQL
        if (prod != null) {
            if (bd.actualitzarStock(prod, quantitat, dataActual)>0) {//Hi ha estoc
                String taula = "VENDES";
                int idvenda = bd.obtenirUltimID(taula);
                Venda ven = new Venda(idvenda, prod.getIdproducte(), dataActual, quantitat);

                if (bd.inserirVenda(ven)>0)
                    System.out.println("VENDA INSERIDA...");
                    
            } else
                System.out.println("NO ES POT FER LA VENDA, NO HI HA ESTOC...");
                
        } else {
            System.out.println("NO HI HA EL PRODUCTE");
            
        }
    }//Fi CrearVenda
    
     //-------------------------------------------------------------------------
    //Veure comandes creades
    private static void VeureComandes (BaseDades bd) {
        ArrayList <Comanda> llista = new ArrayList <Comanda>();
        llista = bd.consultaCom("SELECT * FROM COMANDES");
        if (llista != null)
            for (int i = 0; i<llista.size(); i++) {
                Comanda c = (Comanda) llista.get(i);
                Producte prod = bd.consultarUnProducte(c.getIdproducte());
                 System.out.println("ID Comanda=>"+c.getNumcomanda()+"* Producte: "+prod.getDescripcio()+"* Quantitat: "+c.getQuantitat()+"* Data: "+c.getData());
            }
    }//Fi VeureComandes
    
     //-------------------------------------------------------------------------
    //Veure vendes creades
    private static void VeureVendes (BaseDades bd) {
        ArrayList <Venda> llista = new ArrayList <Venda>();
        llista = bd.consultaVen("SELECT * FROM VENDES");
        if (llista != null)
            for (int i = 0; i<llista.size(); i++) {
                Venda p = (Venda) llista.get(i);
                Producte prod = bd.consultarUnProducte(p.getIdproducte());
                System.out.println("ID Venda =>"+p.getNumvenda()+"* Producte: "+prod.getDescripcio()+"* Quantitat: "+p.getQuantitat()+"* Data: "+p.getDatavenda());
            }
    }//Fi VeureVendes
    
    //--------------------------------------------------------------------------
    //Obté la data actual
    private static java.sql.Date getCurrentDate() {
        java.util.Date avui = new java.util.Date();
        return new java.sql.Date(avui.getTime());
    }//Fi getCurrentDate

    private static void anadirProducto() throws SQLException, Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Id del producto: ");
        int idProd = scan.nextInt();
        scan.nextLine();
        
        System.out.print("Descripcion del producto: ");
        String desProd = scan.nextLine();
        
        System.out.print("StockActual del producto: ");
        int stockActProd = scan.nextInt();
        
        System.out.print("StockMinim del producto: ");
        int stockMinProd = scan.nextInt();
        
        System.out.print("Pvp del producto: ");
        float pvpProd = scan.nextFloat();
        
        Connection con = bd.getConnexio();
        java.sql.Statement st = con.createStatement();
        st.executeUpdate("INSERT INTO PRODUCTE (ID, DESCRIPCIO, STOCKACTUAL, STOCKMINIM, PVP)"
                + " VALUES (" + idProd + " , \"" + desProd +"\" , " + stockActProd +" , " + stockMinProd + " , " + pvpProd + ")");
        
        Producte producte = new Producte(idProd, desProd, stockActProd, stockMinProd, pvpProd);
        
        ProcessBuilder builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd \"C:\\AAA Actividades java\\JavaLibrary1\\src\" && javac ElsMeusBeans\\*.java");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
        
        builder = new ProcessBuilder(
            "cmd.exe", "/c", "cd \"C:\\AAA Actividades java\\JavaLibrary1\\src\" && jar cfm ElsMeusBeansActualizado.jar "
                    + "META_INF\\MANIFEST.MF ElsMeusBeans\\*.class");
        builder.redirectErrorStream(true);
        p = builder.start();
        r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }

    private BaseDades BaseDades() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}//Fi Exemple
