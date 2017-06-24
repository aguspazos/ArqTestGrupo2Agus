/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cines.ws;

import com.cines.business.TicketBean;
import com.cines.business.TicketBeanLocal;
import com.cines.helpers.ApiError;
import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author fabianbozoglilalian
 */
@Path("tickets")
@RequestScoped
public class TicketsResource {

    private final Gson gson = new Gson();
    @Context
    private UriInfo context;
    
    @EJB
    private TicketBeanLocal ticketBean;

    /**
     * Creates a new instance of TicketsResource
     */
    public TicketsResource() {
    }

    /**
     * Retrieves representation of an instance of com.cines.ws.TicketsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@QueryParam("idcine") Long idCine, @QueryParam("idpelicula") Long idPelicula) {
        return Response
                    .ok(gson.toJson(ticketBean.getTickets(idCine, idPelicula)))
                    .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String ticketCreation) {
        try {
            TicketCreationDto dto = gson.fromJson(ticketCreation, TicketCreationDto.class);
            ticketBean.crearTicketsDisponibles(dto.getPelicula(), dto.getCine(), 
                    dto.getPrecio(), dto.getCantidad());
            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            ApiError error = new ApiError("missing.id", "No se encontro ningun cine con ese id");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
        }
    }
    
    /*@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(String ticketVenta) {
        try {
            TicketCreationDto dto = gson.fromJson(ticketCreation, TicketCreationDto.class);
            ticketBean.crearTicketsDisponibles(dto.getPelicula(), dto.getCine(), 
                    dto.getPrecio(), dto.getCantidad());
            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            ApiError error = new ApiError("missing.id", "No se encontro ningun cine con ese id");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
        }
    }*/
}
