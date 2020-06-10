/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OBJECTDB;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Hector
 */
@Entity
public class Cliente {
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue @ManyToOne private Long id;
    private String nom;
    private String correo;
    private String telefono;
    
    public Cliente(String nom, String correo, String telefono) {
        this.nom = nom;
        this.correo = correo;
        this.telefono = telefono;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
