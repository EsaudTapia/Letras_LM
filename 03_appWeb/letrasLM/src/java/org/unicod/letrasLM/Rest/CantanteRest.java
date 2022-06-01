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
import org.unicod.letrasLM.controller.ControllerCantante;
import org.unicod.letrasLM.model.CancionEmbebida;
import org.unicod.letrasLM.model.Cantante;

/**
 *
 * @author PC
 */
@Path("cantante")
public class CantanteRest extends Application {

    @GET
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws Exception {
        ControllerCantante cs = new ControllerCantante();
        String out = "";
        try {
            List<Cantante> Cantante = cs.mostrarCantante();
            Gson gson = new Gson();
            out = gson.toJson(Cantante);
        } catch (Exception e) {
            out = "{\"error\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("search")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarClientes(@FormParam("nombre") String nombre) {
        try {
            ControllerCantante cc = new ControllerCantante();
            Cantante e = new Cantante();
            e.setNombre(nombre);//gracias
            List<Cantante> a = cc.listByNombre(e);
            String json = new Gson().toJson(a);

            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + e.toString()).build();
        }
    }

    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@FormParam("idCantante") @DefaultValue("0") int idCantante,
            @FormParam("nombre") @DefaultValue("") String nombre,
            @FormParam("apellidoPaterno") @DefaultValue("") String apellidoPaterno,
            @FormParam("apellidoMaterno") @DefaultValue("") String apellidoMaterno,
            @FormParam("fechaNacimiento") @DefaultValue("") String fechaNacimiento,
            @FormParam("sexo") @DefaultValue("") String sexo,
            @FormParam("nacionalidad") @DefaultValue("") String nacionalidad,
            @FormParam("foto") @DefaultValue("") String foto) {
        String out = "";

        Cantante c = new Cantante();
        ControllerCantante ca = new ControllerCantante();

        // Llenamos los datos del objeto de tipo Persona:
        c.setNombre(nombre);
        c.setApellidoPaterno(apellidoPaterno);
        c.setApellidoMaterno(apellidoMaterno);
        c.setFechaNacimiento(fechaNacimiento);
        c.setSexo(sexo);
        c.setNacionalidad(nacionalidad);
        c.setFoto(foto);
        c.setIdCantante(idCantante);

        try {
            // Revisamos si el Persona NO tiene un ID:

            ca.insert(c); //Insertamos la cliente en la BD

            out = new Gson().toJson(c);

        } catch (Exception e) {
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
        ControllerCantante ci = new ControllerCantante();
        List<CancionEmbebida> cancionem = new ArrayList<>();
        try {
            cancionem = ci.buscarAlbum(palabra);

            out = new Gson().toJson(cancionem);
        } catch (Exception e) {
            e.printStackTrace();

            out = "{\"exception\":\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("searchdprecio")
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response searchdprecio(@QueryParam("palabra") @DefaultValue("") String palabra) {
        String out = "";
        ControllerCantante ci = new ControllerCantante();
        List<CancionEmbebida> cancionem = new ArrayList<>();
        try {
            cancionem = ci.buscarPrecio(palabra);

            out = new Gson().toJson(cancionem);
        } catch (Exception e) {
            e.printStackTrace();

            out = "{\"exception\":\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

}
