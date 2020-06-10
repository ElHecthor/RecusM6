/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OBJECTDB;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Hector
 */
@Entity
public class Vehiculo {
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue private Long id;
    private String marca;
    private String modelo;
    //@OneToMany private Long propietario;
    private Long propietario;
    private Date fechaInicio;
    private String operacion;
    private boolean seguro;
    private int precioOperacion;

    public Vehiculo(String marca, String modelo, Long propietario, Date fechaInicio, String operacion, boolean seguro, int precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.propietario = propietario;
        this.fechaInicio = fechaInicio;
        this.operacion = operacion;
        this.seguro = seguro;
        this.precioOperacion = precio;
    }

    public boolean isSeguro() {
        return seguro;
    }

    public void setSeguro(boolean seguro) {
        this.seguro = seguro;
    }

    public int getPrecioOperacion() {
        return precioOperacion;
    }

    public void setPrecioOperacion(int precioOperacion) {
        this.precioOperacion = precioOperacion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getPropietario() {
        return propietario;
    }

    public void setPropietario(Long propietario) {
        this.propietario = propietario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public boolean isOperacionTerminada() {
        return seguro;
    }

    public void setOperacionTerminada(boolean operacionTerminada) {
        this.seguro = operacionTerminada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
