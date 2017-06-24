/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cines.ws;

import com.cines.domain.Cine;
import com.cines.domain.Pelicula;

/**
 *
 * @author fabianbozoglilalian
 */
public class TicketCreationDto {
    private Pelicula pelicula;
    private Cine cine;
    private Double precio;
    private int cantidad;

    public TicketCreationDto(Pelicula pelicula, Cine cine, Double precio, int cantidad) {
        this.pelicula = pelicula;
        this.cine = cine;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
