/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hector
 */
import ElsMeusBeans.Comanda;
import ElsMeusBeans.Producte;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebas
 */
public class ProvaJavaLibrary1 {
    public static void main(String[] args) {
        //Afegir productes
        Producte producte1 = new Producte(1, "Portable MSI USB 3.0", 10, 3, 50);
        Producte producte2 = new Producte(2, "HD Seagate Barracuda 250GB", 40, 10, 18);
        Producte producte3 = new Producte(3, "Tarjeta ASUS GeForce EN210", 5, 2, 120);
        Producte producte4 = new Producte(4, "Micro Intel Dual Core G620", 7, 3, 70);
        Producte producte5 = new Producte(5, "Memoria DDR3 Kingston HyperX 4GB", 14, 5, 14);
        
        //Canviar l'stock de algunes
      
        ferComanda(producte1,2);
        ferComanda(producte2,32);
        ferComanda(producte3,1);
        
        //Mostrar per cadascu l'stock mínim
        List<Producte> list = new ArrayList<>();
        list.add(producte1);
        list.add(producte2);
        list.add(producte3);
        list.add(producte4);
        list.add(producte5);
        
        System.out.println();
        System.out.println("Stock actual de cada producte:");
        for(Producte p : list){
            System.out.println("" + p.getDescripcio() + "= " + p.getStockactual());
        }
        
    }
    
    public static void ferComanda(Producte producte, int stocknou){
     Comanda comanda = new Comanda();
        producte.addPropertyChangeListener(comanda);
         producte.setStockactual(stocknou);
        if(comanda.isDemana()){
            System.out.println("Fer comanda en producte: "
                +producte.getDescripcio());
        }
        else {
            System.out.println("No és necessari fer la comanda en producte: "
                +producte.getDescripcio());
        }
    }
}
