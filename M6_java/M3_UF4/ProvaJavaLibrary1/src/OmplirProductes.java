/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hector
 */
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

import ElsMeusBeans.Producte;

public class OmplirProductes {
    public static void main (String[] args) {
        //Obrir la base de dades
        ODB odb = ODBFactory.open("Producte_com.BD");
        Producte p1 = new Producte(1, "Barcelona, Una biografia", 10, 3, 160);
        Producte p2 = new Producte(2, "La magia de l'ordre", 5, 2, 176);
        Producte p3 = new Producte(3, "Tot es al teu cap", 20, 5, 193);
        Producte p4 = new Producte(4, "Filosofía inacabada", 8, 3, 85);
        Producte p5 = new Producte(5, "La resistència íntima", 7, 1, 159);
        Producte p6 = new Producte(6, "El capital al segle xxi", 5, 2, 80);
        
        //S'emmagatzema els productes
        odb.store(p1);
        odb.store(p2);
        odb.store(p3);
        odb.store(p4);
        odb.store(p5);
        odb.store(p6);
        
        //Es tanca la base de dades
        odb.close();
    }
    
}
