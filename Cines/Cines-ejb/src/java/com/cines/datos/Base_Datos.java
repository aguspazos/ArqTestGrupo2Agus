/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cines.datos;

import com.cines.domain.Cine;
import com.cines.domain.Pelicula;
import com.cines.domain.Ticket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 *
 * @author mravera
 */

@LocalBean
@Singleton
public class Base_Datos {

    private ArrayList<Cine> listaCinesBD;
    private ArrayList<Pelicula> listaPeliculasBD;
    private ArrayList<Ticket> listaTicketsBD;
    
    private static long lastId = 1;

    public ArrayList<Cine> obtenerCines() {
        return listaCinesBD;
    }

    public ArrayList<Pelicula> obtenerPeliculas() {
        return listaPeliculasBD;
    }
    
    public String createCodigoDeAcceso() {
        return UUID.randomUUID().toString();
    }
    
    @PostConstruct
    public void Inicializar() {
        this.listaCinesBD = new ArrayList<>();
        this.listaPeliculasBD = new ArrayList<>();
        this.listaTicketsBD = new ArrayList<>();
        
        this.agregarCine(new Cine(Long.parseLong("0"), "Movie", "LALAL 1234", createCodigoDeAcceso()));
        this.agregarCine(new Cine(Long.parseLong("0"), "Hoyts", "LOLOLO 98765", createCodigoDeAcceso()));
        
        this.agregarPelicula(new Pelicula(Long.parseLong("0"), "Guardianes vol2", "Aventura", true));
        this.agregarPelicula(new Pelicula(Long.parseLong("0"), "Rapido y furioso 5", "Accion", false));
    }

    public boolean agregarPelicula(Pelicula p) {
        p.setId(lastId++);
        try {
            return this.listaPeliculasBD.add(p);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean agregarCine(Cine c) {
        c.setId(lastId++);

        try {
            return this.listaCinesBD.add(c);
        } catch (Exception e) {
            return false;
        }
    }

    public Pelicula obtenerPeliculaPorId(long id) {
        Pelicula p = null;
        for (int i = 0; i < this.listaPeliculasBD.size(); i++) {
            if (listaPeliculasBD.get(i).getId() == id) {
                p = listaPeliculasBD.get(i);
            }
        }
        return p;
    }

    public Cine obtenerCinePorId(long id) {
        Cine c = null;
        for (int i = 0; i < this.listaCinesBD.size(); i++) {
            if (listaCinesBD.get(i).getId() == id) {
                c = listaCinesBD.get(i);
            }
        }
        return c;
    }

    public Pelicula modificarPelicula(long id, Pelicula p) {
        Pelicula aModificar = null;
        for (int i = 0; i < this.listaPeliculasBD.size(); i++) {
            if (listaPeliculasBD.get(i).getId() == id) {
                aModificar = listaPeliculasBD.get(i);
                aModificar.setEnCartelera(p.getEnCartelera());
                aModificar.setGenero(p.getGenero());
                aModificar.setNombre(p.getNombre());
                return aModificar;
            }
        }
        return aModificar;
    }

    public Cine modificarCine(long id, Cine c) {
        Cine aModificar = null;
        for (int i = 0; i < this.listaCinesBD.size(); i++) {
            if (listaCinesBD.get(i).getId() == id) {
                aModificar = listaCinesBD.get(i);
                aModificar.setCodigoDeAcceso(c.getCodigoDeAcceso());
                aModificar.setDireccion(c.getDireccion());
                aModificar.setNombre(c.getNombre());
                return aModificar;
            }
        }
        return aModificar;
    }

    public void borrarPelicula(long id) {
        listaPeliculasBD.remove(new Pelicula(id));
    }

    public void borrarCine(Long idCine) {
        listaCinesBD.remove(new Cine(idCine));
    }

    public Cine obtenerPorNombre(String nombre) {
        for (Cine cine: listaCinesBD) {
            if (cine.getNombre().equals(nombre)) {
                return cine;
            }
        }
        return null;
    }

    public boolean existePelicula(long id) {
        return obtenerPeliculaPorId(id) != null;
    }

    public boolean agregarTicket(Ticket t) {
        t.setId(lastId++);
        try {
            return this.listaTicketsBD.add(t);
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Ticket> obtenerTickets(Long idCine, Long idPelicula) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < this.listaTicketsBD.size(); i++) {
            if (Objects.equals(listaTicketsBD.get(i).getPelicula().getId(), idPelicula)
                    && Objects.equals(listaTicketsBD.get(i).getCine().getId(), idCine)) {
                tickets.add(listaTicketsBD.get(i));
            }
        }
        return tickets;
    }
}
