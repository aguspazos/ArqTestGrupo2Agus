package com.cines.business;

import com.cines.datos.Base_Datos;
import com.cines.domain.Pelicula;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityNotFoundException;

@Stateless
@LocalBean
public class PeliculasBeans {
    
    @EJB
    private Base_Datos db;
    
    public Pelicula obtenerPelicula(long id) {
        return db.obtenerPeliculaPorId(id);
    }
    
    public List<Pelicula> obtenerPeliculas() {
        return db.obtenerPeliculas();
    }
    
    public boolean agregarPelicula(Pelicula pelicula) {
        if (validarPelicula(pelicula)) {
            return db.agregarPelicula(pelicula);
        }
        return false;
    }
    
    public void borrarPelicula(long id) {
        if (existePelicula(id)) {
            db.borrarPelicula(id);
        } else {
            throw new EntityNotFoundException();
        }
    }
    
    public Pelicula modificarPelicula(long id, Pelicula pelicula) {     
        if (existePelicula(id)) {
           if (validarPelicula(pelicula)) {
                return db.modificarPelicula(id, pelicula);
           }
        }
        
        return null;
    }

    private boolean validarPelicula(Pelicula pelicula) {
        return !(pelicula.getId() < 0
                || pelicula.getNombre().isEmpty()
                || pelicula.getGenero().isEmpty());
    }

    private boolean existePelicula(long id) {
        if (db.existePelicula(id)) {
            return true;
        } else {
            throw new EntityNotFoundException();
        }
    }
}
