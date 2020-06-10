/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElsMeusBeans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BaseDades {

    //Objecte Connection per connectar amb la base de dades
    private static Connection connexio;
    private String urldb;//Url per a accedir a la base de dades
    private String usuari;//Nom d'usuari
    private String contrasenya;//Constrasenya d'usuari
    private String driver;//Driver per a la connexio a la base de dades
    private  boolean crearConnexio;//Atribut per saber si la connexio esta creada
    
    //Constructors
    public BaseDades() {}//Constructor per defecte
    
    public BaseDades(String urldb, String usuari, String contrasenya, String driver) {
        this.urldb = urldb;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.driver = driver;
    }
    //Carrega el driver i estableix la connexió amb la base de dades
    public void setCrearConnexio() {
        crearConnexio = false;
        try {
            Class.forName(driver);
            connexio = DriverManager.getConnection(urldb, usuari, contrasenya);
            crearConnexio = true;
        } catch (Exception e) {
            System.out.println("PROBLEMA A LA CONNEXIÓ...");
            e.printStackTrace();
        }
    }
    

    //Torna el valor de l'atribut crearConnexio
    public boolean getCrearConnexio() { return crearConnexio; }
    

    //Torna l'objecte connexió
    public Connection getConnexio() { return connexio; }
    

    //Tanca la connexió de la base de dades
    public void tancarConnexio() {
        try {
            connexio.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//Fi tancarConnexio


    //--------------------------------------------------------------------------
    //Torna una llista de productes consultats
    public ArrayList<Producte> consultaPro (String consulta) {
        ArrayList<Producte> llista = new ArrayList<Producte>();
        try {
            Statement sentencia = getConnexio().createStatement();
            ResultSet resul = sentencia.executeQuery(consulta);
            
            while (resul.next()) {
                Producte p = new Producte(resul.getInt(1), resul.getString(2), resul.getInt(3), resul.getInt(4), resul.getInt(5));
                llista.add(p);
            }
            resul.close();
            sentencia.close();
        } catch (SQLException e) {
            System.out.println("PROBLEMES EN CONSULTAR PRODUCTES...");
            e.printStackTrace();
        }
        return llista;
    }//Fi consultaPro
    

     //-------------------------------------------------------------------------
    //Obté la llista de comandes
    public ArrayList<Comanda> consultaCom (String consulta) {
         ArrayList<Comanda> llista = new ArrayList<Comanda>();
        try {
            Statement sentencia = getConnexio().createStatement();
            ResultSet resul = sentencia.executeQuery(consulta);
            
            while (resul.next()) {
                Comanda p = new Comanda(resul.getInt(1), resul.getInt(2), resul.getDate(3), resul.getInt(4));
                llista.add(p);
            }
            resul.close();
            sentencia.close();
        } catch (SQLException e) {
            System.out.println("PROBLEMES EN CONSULTAR COMANDA...");
            e.printStackTrace();
        }
        return llista;
    }//Fi consultaCom
    

    //--------------------------------------------------------------------------
    //Obté la llista de vendes
    public ArrayList<Venda> consultaVen (String consulta) {
         ArrayList<Venda> llista = new ArrayList<Venda>();
        try {
            Statement sentencia = getConnexio().createStatement();
            ResultSet resul = sentencia.executeQuery(consulta);
            
            while (resul.next()) {
                Venda p = new Venda(resul.getInt(1), resul.getInt(2), resul.getDate(3), resul.getInt(4));
                llista.add(p);
            }
            resul.close();
            sentencia.close();
        } catch (SQLException e) {
            System.out.println("PROBLEMES EN CONSULTAR VENDES...");
            e.printStackTrace();
        }
        return llista;
    }//Fi consultaVen
    

    //--------------------------------------------------------------------------
    //Obté l'últim identificador de la taula que rep
    public int obtenirUltimID (String taula) {
        int id = 0;
        String consulta = "SELECT MAX(ID) FROM "+taula;
        try {
            Statement sentencia = getConnexio().createStatement();
            ResultSet resul = sentencia.executeQuery(consulta);
            resul.next();
            id = resul.getInt(1)+1;
            resul.close();
            sentencia.close();
        } catch (SQLException e) {
            System.out.println("PROBLEMES A L'OBTENIR MÀXIM ID EN TAULA "+taula);
            e.printStackTrace();
        }
        return id;
    }//Fi obtenirUltimID
    

     //-------------------------------------------------------------------------
    //Inserir una venda
    public int inserirVenda (Venda ven) {
        int files = 0;
        String sql = "INSERT INTO VENDES VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement sentencia = getConnexio().prepareStatement(sql);
            sentencia.setInt(1, ven.getNumvenda());//Núm. venda
            sentencia.setInt(2, ven.getIdproducte());//ID producte
            sentencia.setDate(3, (Date) ven.getDatavenda());//Data venda
            sentencia.setInt(4, ven.getQuantitat());//Quantitat
            files = sentencia.executeUpdate();//Files afectades
            sentencia.close();
        } catch (SQLException e) {
            System.out.println("ERROR EN INSERIR VENDA");
            e.printStackTrace();
        }
        return files;
    }//Fi inserirVenda
    

     //-------------------------------------------------------------------------
    //Actualitzar estoc i generar comanda
    public int actualitzarStock (Producte producte, int quantitat, java.sql.Date dataActual) {
        Comanda comanda = new Comanda();
        producte.addPropertyChangeListener(comanda);//Afegir l'oient
        int noustock = producte.getStockactual() - quantitat;//Càlcul d'estoc
        
        producte.setStockactual(noustock);//Es canvia l'estoc actual
        
        int files = 0;
        String sql = "";
        PreparedStatement sentencia = null;
        try {
            if (comanda.isDemana()) {//NO s'actualitza l'estoc a la base de dades
               System.out.println("FER COMANDA EN PRODUCTE: "+producte.getDescripcio());
               String taula = "COMANDES";
               int numComanda = obtenirUltimID(taula);
               comanda.setQuantitat(quantitat);
               comanda.setIdproducte(producte.getIdproducte());
               comanda.setNumcomanda(numComanda);
               comanda.setData(dataActual);
               sql = "INSERT INTO COMANDES VALUES (?, ?, ?, ?)";
               sentencia = getConnexio().prepareStatement(sql);
               sentencia.setInt(1, comanda.getNumcomanda());//Num. comanda
               sentencia.setInt(2, comanda.getIdproducte());//idproducte
               sentencia.setDate(3, (Date) comanda.getData());//Data
               sentencia.setInt(4, comanda.getQuantitat());//Quantitat
               files = sentencia.executeUpdate();//Files afectades
               System.out.println("COMANDA "+numComanda+" GENERAT...");
               files = -1;//NO es pot fer la venda
               sentencia.close();
               
            } else {//S'actualitza l'etoc a la base de dades
                sql = "UPDATE PRODUCTE SET STOCKACTUAL = ? WHERE ID = ?";
                sentencia = getConnexio().prepareStatement(sql);
                sentencia.setInt(1, producte.getStockactual());//Estoc
                sentencia.setInt(2, producte.getIdproducte());//idproducte
                files = sentencia.executeUpdate();//Files afectades
                System.out.println("ESTOC ACTUALITZAT...");
                sentencia.close();
                
            }
        } catch (SQLException e) {
                System.out.println("PROBLEMES EN ACTUALITZAT L'ESTOC...");
                files = -1;//NO es pot fer la venda
        }
        return files;
        
    }//Fi actualitzarStock
    

     //-------------------------------------------------------------------------
    //Obté un producte a partir del seu ID
    public Producte consultarUnProducte (int idproducte) {
        Producte p = null;
        String consulta = "SELECT * FROM PRODUCTE WHERE ID = ?";
        try {
             PreparedStatement sentencia = getConnexio().prepareStatement(consulta);
             sentencia.setInt(1, idproducte);
             ResultSet resul = sentencia.executeQuery();
             resul.next();
             p = new Producte(resul.getInt(1), resul.getString(2), resul.getInt(3), resul.getInt(4), resul.getFloat(5));
             resul.close();
             sentencia.close();
        } catch (SQLException e) {
            System.out.println("PROBLEMES EN OBTENIR EL PRODUCTE AMB ID: "+idproducte);
            e.printStackTrace();
        }
        return p; //Torna el producte
    }//Fi consultarUnProducte
    
}//Fi classe BaseDades
