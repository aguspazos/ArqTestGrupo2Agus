/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cines.business;

import com.cines.domain.Ticket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;



@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/Topic"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class TicketMDBean implements MessageListener {
    @EJB
    private TicketBeanLocal bean;
    
    public TicketMDBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage msg = (ObjectMessage) message;
            bean.agregarTicket((Ticket) msg.getObject());
        } catch (JMSException ex) {
            Logger.getLogger(TicketMDBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}