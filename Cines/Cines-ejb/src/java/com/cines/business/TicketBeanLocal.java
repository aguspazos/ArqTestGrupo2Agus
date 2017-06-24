/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cines.business;

import com.cines.domain.Pelicula;
import com.cines.domain.Cine;
import com.cines.domain.Ticket;
import java.util.ArrayList;
import javax.ejb.Local;


/**
 *
 * @author fabianbozoglilalian
 */
@Local
public interface TicketBeanLocal {

    void crearTicketsDisponibles(Pelicula pelicula, Cine cine, Double precio, int cantidad);

    void agregarTicket(Ticket ticket);

    public ArrayList<Ticket> getTickets(Long idCine, Long idPelicula);
    
}
