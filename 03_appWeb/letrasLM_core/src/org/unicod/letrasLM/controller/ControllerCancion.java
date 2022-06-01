/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicod.letrasLM.controller;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import static java.util.Arrays.asList;
import java.util.List;
import org.bson.Document;
import org.unicod.letrasLM.conexion.Conexion;
import static org.unicod.letrasLM.controller.ControllerAlbum.client;
import static org.unicod.letrasLM.controller.ControllerAlbum.collection;
import static org.unicod.letrasLM.controller.ControllerAlbum.database;
import org.unicod.letrasLM.model.Cancion;
import org.unicod.letrasLM.model.CancionEmbebida;

/**
 *
 * @author PC
 */
public class ControllerCancion {

    public List buscarAlbum(String nombre) {

        client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
        database = client.getDB("letrasLM");
        collection = database.getCollection("album");
        DBObject query = new BasicDBObject("nombre", nombre);
        DBObject query2 = new BasicDBObject("_id", 0).append("cantante", 0);

        DBObject orderby = new BasicDBObject("IdAlbum", 1);
        DBCursor cursor = collection.find(query,query2).sort(orderby);
        List listaIngresos = cursor.toArray();
        return listaIngresos;
    }

    public void insert(CancionEmbebida ca) {
        Conexion con = new Conexion();
        con.conectar();
        con.setMongoClient(new MongoClient());
        con.setMongodb(con.getMongoClient().getDatabase("letrasLM"));
        Gson gson = new Gson();
        MongoCollection<Document> canciones = con.getMongodb().getCollection("cancion");
       
        
      
        
        BasicDBObject Documento1 = new BasicDBObject();
        Documento1.put("idAlbum", ca.getAlbum().getIdAlbum());
        Documento1.put("nombre", ca.getAlbum().getNombre());      
        Documento1.put("genero", ca.getAlbum().getGenero());
        Documento1.put("precio", ca.getAlbum().getPrecio());
        Documento1.put("fechaPublicacion", ca.getAlbum().getFechaPublicacion());
        Documento1.put("discografica", ca.getAlbum().getDiscografica());
        Documento1.put("foto", ca.getAlbum().getFoto());
                                   
        
        
       BasicDBObject cancion = new BasicDBObject();                      
       cancion.put("IdCancion", ca.getCancion().getIdCancion());
       cancion.put("nombre",ca.getCancion().getNombre() );
       cancion .put("letra",ca.getCancion().getLetra() );
       cancion .put("duracion",ca.getCancion().getDuracion());
       cancion .put("link",ca.getCancion().getLink());
       cancion.put("album",Documento1);         
       

    
       

        canciones.insertOne(getDocument(cancion));//Necesitamos un caste para que lo lea como Document comun

        System.out.println(cancion);

    }

    public static Document getDocument(BasicDBObject doc) {
        if (doc == null) {
            return null;
        }
        return new Document(doc.toMap());
    }
    
     public List buscarCancion(String nombre) {

        client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
        database = client.getDB("letrasLM");
        collection = database.getCollection("cancion");
          int id =Integer.valueOf (nombre);
        DBObject query = new BasicDBObject("IdCancion", id);      
        DBObject orderby = new BasicDBObject("IdAlbum", 1);
        DBCursor cursor = collection.find(query).sort(orderby);
        List listaIngresos = cursor.toArray();
        return listaIngresos;
    }
}


