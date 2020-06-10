/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElsMeusBeans;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Producte implements Serializable {
    
    private String descripcio;
    private int idproducte;
    private int stockactual;
    private int stockminim;
    private float pvp;
    
    private PropertyChangeSupport propertySupport;
    
    public Producte() {
        propertySupport = new PropertyChangeSupport(this);
    }
    
    public Producte (int idproducte, String descripcio, int stockactual, int stockminim, float pvp) {
        propertySupport = new PropertyChangeSupport (this);
        this.idproducte = idproducte;
        this.descripcio = descripcio;
        this.stockactual = stockactual;
        this.stockminim = stockminim;
        this.pvp = pvp;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getIdproducte() {
        return idproducte;
    }

    public void setIdproducte(int idproducte) {
        this.idproducte = idproducte;
    }

    public int getStockactual() {
        return stockactual;
    }

    public void setStockactual(int valorNou) {
        int valorAnterior = stockactual;
	stockactual = valorNou;
	if (stockactual < getStockminim()) //S'ha de fer la comanda
	  propertySupport.firePropertyChange("stockactual", valorAnterior, stockactual);
    }

    public int getStockminim() {
        return stockminim;
    }

    public void setStockminim(int stockminim) {
        this.stockminim = stockminim;
    }

    public float getPvp() {
        return pvp;
    }

    public void setPvp(float pvp) {
        this.pvp = pvp;
    }
    
    public void addPropertyChangeListener(PropertyChangeListener comanda) {
        propertySupport.addPropertyChangeListener(comanda);
    }
}
