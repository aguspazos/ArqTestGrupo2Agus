package com.cines.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cine implements Serializable {
    private Long id;
    private String nombre;
    private String direccion;
    private String codigoDeAcceso;
    private List<Pelicula> peliculas;
    
    public Cine(Long id, String nombre, String direccion, String codigoDeAcceso) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.codigoDeAcceso = codigoDeAcceso;
        this.peliculas = new ArrayList<Pelicula>();
    }

    public Cine(Long idCine) {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoDeAcceso() {
        return codigoDeAcceso;
    }

    public void setCodigoDeAcceso(String codigoDeAcceso) {
        this.codigoDeAcceso = codigoDeAcceso;
    }
    
    
}
