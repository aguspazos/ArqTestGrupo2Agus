/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cines.business;

import com.cines.datos.Base_Datos;
import javax.ejb.Stateless;
import com.cines.domain.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import static sun.security.krb5.Confounder.bytes;

/**
 *
 * @author fabianbozoglilalian
 */
@Stateless
public class TicketBean implements TicketBeanLocal {

    @Resource(lookup = "jms/ConnectionFactory")
    private ConnectionFactory cf;
    
    @Resource(lookup = "jms/Queue")
    private Queue queue;
    
    @Resource(lookup = "jms/Topic")
    private Topic topic;
    
    @EJB
    private Base_Datos datos;
    
    @Override
    public void crearTicketsDisponibles(Pelicula pelicula, Cine cine, Double precio, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Ticket t = new Ticket(cine, pelicula, precio, true);
            try {
                encolar(t);
            } catch (IOException ex) {
                Logger.getLogger(TicketBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void encolar(Ticket t) throws IOException {
        try {
            Connection connection = cf.createConnection();
            Session session = connection.createSession();
            ObjectMessage createObjectMessage = session.createObjectMessage(t);
            MessageProducer producer = session.createProducer(topic);
            producer.send(createObjectMessage);
            session.close();
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(TicketBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void agregarTicket(Ticket ticket) {
        datos.agregarTicket(ticket);
    }

    @Override
    public ArrayList<Ticket> getTickets(Long idCine, Long idPelicula) {
        return datos.obtenerTickets(idCine, idPelicula);
    }
    
}
