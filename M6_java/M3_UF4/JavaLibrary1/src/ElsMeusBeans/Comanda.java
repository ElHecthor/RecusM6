/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElsMeusBeans;

import java.beans.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Comanda implements Serializable, PropertyChangeListener {
    
    private int numcomanda;
    private int idproducte;
    private Date  data;
    private int quantitat;
    private boolean demana;
    
    public Comanda() { }
    
     public Comanda(int numcomanda, int idproducte, java.sql.Date data, int quantitat) {
         this.numcomanda = numcomanda;
         this.idproducte = idproducte;
         this.data = data;
         this.quantitat = quantitat;
     }
    
    @Override
    public void propertyChange (PropertyChangeEvent evt) {
        System.out.println("Valor anterior:"+ evt.getOldValue());
        System.out.println("Valor actual:"+ evt.getNewValue());
        setDemana(true); //S'ha de fer una comanda
    }

    public int getNumcomanda() {
        return numcomanda;
    }

    public void setNumcomanda(int numcomanda) {
        this.numcomanda = numcomanda;
    }

    public int getIdproducte() {
        return idproducte;
    }

    public void setIdproducte(int idproducte) {
        this.idproducte = idproducte;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public boolean isDemana() {
        return demana;
    }

    public void setDemana(boolean demana) {
        this.demana = demana;
    }
   
    
}
