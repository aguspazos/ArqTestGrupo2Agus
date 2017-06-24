
package com.cines.domain;

import java.io.Serializable;

public class Pelicula implements Serializable {
    private Long id;
    private String nombre;
    private String genero;
    private Boolean enCartelera;

    public Pelicula(Long id, String nombre, String genero, Boolean enCartelera) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.enCartelera = enCartelera;
    }

    public Pelicula(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Boolean getEnCartelera() {
        return enCartelera;
    }

    public void setEnCartelera(Boolean enCartelera) {
        this.enCartelera = enCartelera;
    }
    
}
