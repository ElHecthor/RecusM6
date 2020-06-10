/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElsMeusBeans;

import java.beans.*;
import java.io.Serializable;
import java.sql.Date;

public class Venda implements Serializable {
    
   private static final long serialVersionUID = 1L;
   private int numvenda;
   private int idproducte;
   private java.sql.Date datavenda;
   private int quantitat;
   
    
    public Venda() { }
    
    public Venda(int numvenda, int idproducte, java.sql.Date datavenda, int quantitat) { 
        this.numvenda = numvenda;
        this.idproducte = idproducte;
        this.datavenda = datavenda;
        this.quantitat = quantitat;
    }

    public int getNumvenda() {
        return numvenda;
    }

    public void setNumvenda(int numvenda) {
        this.numvenda = numvenda;
    }

    public int getIdproducte() {
        return idproducte;
    }

    public void setIdproducte(int idproducte) {
        this.idproducte = idproducte;
    }

    public Date getDatavenda() {
        return datavenda;
    }

    public void setDatavenda(Date datavenda) {
        this.datavenda = datavenda;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }
}
