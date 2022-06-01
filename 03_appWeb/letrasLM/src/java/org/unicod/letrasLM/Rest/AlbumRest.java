/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicod.letrasLM.Rest;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.unicod.letrasLM.controller.ControllerAlbum;

import org.unicod.letrasLM.model.AlbumEmbebido;

/**
 *
 * @author PC
 */
@Path("album")
public class AlbumRest extends Application {

    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@FormParam("datoAlbum") @DefaultValue("{​​}​​") String datoAlbum) {

        String out = "";

        AlbumEmbebido v = null;
        ControllerAlbum ca = new ControllerAlbum();

        try {
            v = new Gson().fromJson(datoAlbum, AlbumEmbebido.class);
            ca.insert(v);
            out = "{​​\"result\":" + "good" + "}​​";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{​​\"exception\":\"" + e.toString() + "\"}​​";
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }
    
    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("filtro") @DefaultValue("") String filtro)
    {
        String out = "";
        ControllerAlbum ci = new ControllerAlbum();
        List albumes = new ArrayList<>();
        try
        {
            albumes = ci.getAll(filtro);
                                    
            out = new Gson().toJson(albumes);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            
            out = "{\"exception\":\"" + e.toString() + "\"}";
        }
                
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("searchdetalle")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("palabra") @DefaultValue("") String palabra) {
        String out = "";
        ControllerAlbum ci = new ControllerAlbum();
        List<AlbumEmbebido> cancionem = new ArrayList<>();
        try {
            cancionem = ci.buscarCanciones(palabra);

            out = new Gson().toJson(cancionem);
        } catch (Exception e) {
            e.printStackTrace();

            out = "{\"exception\":\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("searchdNombre")
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response searchdprecio(@QueryParam("palabra") @DefaultValue("") String palabra) {
        String out = "";
        ControllerAlbum ci = new ControllerAlbum();
        List<AlbumEmbebido> cancionem = new ArrayList<>();
        try {
            cancionem = ci.buscarNombre(palabra);

            out = new Gson().toJson(cancionem);
        } catch (Exception e) {
            e.printStackTrace();

            out = "{\"exception\":\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }
}


 


