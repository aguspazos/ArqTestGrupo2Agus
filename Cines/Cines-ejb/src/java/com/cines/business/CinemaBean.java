/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cines.business;

import com.cines.datos.Base_Datos;
import com.cines.domain.Cine;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author fabianbozoglilalian
 */
@Stateless
@LocalBean
public class CinemaBean {
    
    @EJB
    private Base_Datos datos;

    public List<Cine> getAllCinemas() {
        return datos.obtenerCines();
    }
    
    public Cine getCineFromName(String nombre) {
        return datos.obtenerPorNombre(nombre);
    }

    public Cine addCinema(Cine cine) {
        cine.setCodigoDeAcceso(datos.createCodigoDeAcceso());
        datos.agregarCine(cine);
        return cine;
    }

    public Cine getCinemaById(Long idCine) {
        return datos.obtenerCinePorId(idCine);
    }

    public Cine updateCinema(Long idCine, Cine cine) {
        if (getCinemaById(idCine) == null) {
            throw new EntityNotFoundException("No se encuentra el cine");
        }
        datos.modificarCine(idCine, cine);
        return cine;
    }

    public void deleteCinema(Long idCine) {
        if (getCinemaById(idCine) == null) {
            throw new EntityNotFoundException("No se encuentra el cine");
        }
        datos.borrarCine(idCine);
    }
}
