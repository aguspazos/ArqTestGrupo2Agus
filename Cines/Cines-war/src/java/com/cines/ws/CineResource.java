/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cines.ws;

import com.cines.business.CinemaBean;
import com.cines.domain.Cine;
import com.cines.helpers.ApiError;
import com.google.gson.Gson;
import javax.ejb.EJB;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cinemas")
public class CineResource {

    private final Gson gson = new Gson();

    @Context
    private UriInfo context;
    
    @EJB
    private CinemaBean cinemaBean;

    public CineResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCinemas(@QueryParam("nombre") String nombre) {
        if (nombre == null) {
            return Response
                    .ok(gson.toJson(cinemaBean.getAllCinemas()))
                    .build();
        } else {
            return getCinemaFromName(nombre);
        }
    }
    
    private Response getCinemaFromName(String nombre) {
        Cine cineSelected = cinemaBean.getCineFromName(nombre);

        if (cineSelected == null) {
            ApiError error = new ApiError("missing.name", "No se encontro ningun cine con ese nombre");
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson(error))
                    .build();
        } else {
            return Response
                    .ok(gson.toJson(cineSelected))
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCinema(String cineJson) {
        Cine cine = cinemaBean.addCinema(gson.fromJson(cineJson, Cine.class));
        return Response
                .ok(cine.getCodigoDeAcceso())
                .build();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCinemaById(@PathParam("id") Long idCine) {
        Cine cineSelected = cinemaBean.getCinemaById(idCine);

        if (cineSelected == null) {
            ApiError error = new ApiError("missing.id", "No se encontro ningun cine con ese id");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
        } else {
            return Response.ok(gson.toJson(cineSelected)).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(@PathParam("id") Long idCine, String cineJson) {
        try {
            Cine cine = cinemaBean.updateCinema(idCine, gson.fromJson(cineJson, Cine.class));
            return Response.ok(gson.toJson(cine)).build();
        } catch (EntityNotFoundException e) {
            ApiError error = new ApiError("missing.id", "No se encontro ningun cine con ese id");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
        }
    }
    
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long idCine) {
        try {
            cinemaBean.deleteCinema(idCine);
            return Response.ok().build();
        } catch (EntityNotFoundException e) {
            ApiError error = new ApiError("missing.id", "No se encontro ningun cine con ese id");
            return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
        }
    }
}
