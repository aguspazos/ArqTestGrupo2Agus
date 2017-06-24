/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cines.domain;

import java.io.Serializable;
import org.jboss.weld.util.LazyValueHolder;

/**
 *
 * @author fabianbozoglilalian
 */
public class Ticket  implements Serializable {
    private Long id;
    private Cine cine;
    private Pelicula pelicula;
    private String ciCliente;
    private Double precio;
    private boolean disponible;

    public Ticket() {
    }

    public Ticket(Long id, Cine cine, Pelicula pelicula, String ciCliente, Double precio, boolean disponible) {
        this.id = id;
        this.cine = cine;
        this.pelicula = pelicula;
        this.ciCliente = ciCliente;
        this.precio = precio;
        this.disponible = disponible;
    }

    public Ticket(Cine cine, Pelicula pelicula, Double precio, boolean disponible) {
        this.cine = cine;
        this.pelicula = pelicula;
        this.precio = precio;
        this.disponible = disponible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public String getCiCliente() {
        return ciCliente;
    }

    public void setCiCliente(String ciCliente) {
        this.ciCliente = ciCliente;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    
    
}
