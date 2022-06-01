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
import org.unicod.letrasLM.controller.ControllerCancion;
import org.unicod.letrasLM.model.CancionEmbebida;

/**
 *
 * @author PC
 */
@Path("cancion")
public class CancionRest extends Application {
   @Path("search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("palabra") @DefaultValue("") String palabra)
    {
        String out = "";
        ControllerCancion ci = new ControllerCancion();
       List<CancionEmbebida> cancionem = new ArrayList<>();
        try
        {
            cancionem = ci.buscarAlbum(palabra);
                                    
            out = new Gson().toJson(cancionem);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            
            out = "{\"exception\":\"" + e.toString() + "\"}";
        }
                
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@FormParam("datoCancion") @DefaultValue("{}") String datoCancion) {
  
           
            
           
        
        String out = "";
        
        CancionEmbebida v = new CancionEmbebida();
        ControllerCancion ca = new ControllerCancion();
       
        
        try {
            v = new Gson().fromJson(datoCancion, CancionEmbebida.class);
            ca.insert(v);
            out = "{​​\"result\":" + "good" + "}​​";
        } catch (Exception e) {
            e.printStackTrace();
            out = "{​​\"exception\":\"" + e.toString() + "\"}​​";
        }
        return Response.status(Response.Status.OK).entity(out).build();

    }
    
    @Path("searchdetallecan")
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response searchdprecio(@QueryParam("palabra") @DefaultValue("") String palabra) {
        String out = "";
        ControllerCancion ci = new ControllerCancion();
        List<CancionEmbebida> cancionem = new ArrayList<>();
        try {
            cancionem = ci.buscarCancion(palabra);

            out = new Gson().toJson(cancionem);
        } catch (Exception e) {
            e.printStackTrace();

            out = "{\"exception\":\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }
    
}
