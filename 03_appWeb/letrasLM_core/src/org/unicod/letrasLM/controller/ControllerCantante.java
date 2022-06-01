/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicod.letrasLM.controller;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import org.unicod.letrasLM.conexion.Conexion;
import static org.unicod.letrasLM.controller.ControllerAlbum.client;
import static org.unicod.letrasLM.controller.ControllerAlbum.collection;
import static org.unicod.letrasLM.controller.ControllerAlbum.database;
import org.unicod.letrasLM.model.Cantante;


/**
 *
 * @author PC
 */
public class ControllerCantante {
    BasicDBObject document = new BasicDBObject();
    
    public void insert(Cantante c) {
        Conexion con = new Conexion();
        con.conectar();
        con.setMongoClient(new MongoClient());
        con.setMongodb(con.getMongoClient().getDatabase("letrasLM"));
        Gson gson = new Gson();
        MongoCollection<Document> cantante = con.getMongodb().getCollection("cantante");
        
//        DBCollection colec = (DBCollection) con.getMongodb().getCollection("cantante");
      
       Document documento = new Document("idCantante",c.getIdCantante()).append("nombre",c.getNombre())
                                        .append("apellidoPaterno", c.getApellidoPaterno()).append("apellidoMaterno", c.getApellidoMaterno())
                                        .append("fechaNacimiento", c.getFechaNacimiento()).append("sexo",c.getSexo())
                                        .append("nacionalidad",c.getNacionalidad()).append("foto", c.getFoto())
                                        .append("estatus", 1);//Asi se agrega un nuevo documento
        System.out.println(documento);
     cantante.insertOne(documento);//Asi se inserta al servidor de mongo db el documento  
      
       
}
//        public static void main(String[] args) {
//       ControllerCantante cn=new ControllerCantante();
//       cn.insert();
//  }
    
     public List<Cantante> mostrarCantante() {
        Conexion c = new Conexion();
        c.setMongoClient(new MongoClient());
        c.setMongodb(c.getMongoClient().getDatabase("letrasLM"));
        Gson gson = new Gson();
        MongoCollection<Document> cantante = c.getMongodb().getCollection("cantante");//Segun yo eso se usa para un update 

        List<Cantante> list = new ArrayList<Cantante>();

        FindIterable<Document> iterable = c.getMongodb().getCollection("cantante").find(new Document("estatus", 1) {
        }); // según yo el filtro de que si contenia edgar es así, no? "/Edgar/i" si
        // Iterate the results and apply a block to each resulting document.
        // Iteramos los resultados y aplicacimos un bloque para cada documento.
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Cantante e = null;
                e = gson.fromJson(document.toJson(), Cantante.class);
                list.add(e);
                System.out.println(document);
            }
        });
        return list;
    }
     
      public List<Cantante> listByNombre(Cantante ca) {
        Conexion c = new Conexion();
        c.setMongoClient(new MongoClient());
        c.setMongodb(c.getMongoClient().getDatabase("letrasLM"));
        Gson gson = new Gson();
        MongoCollection<Document> clientes = c.getMongodb().getCollection("cantante");
        List<Cantante> list = new ArrayList<Cantante>();
        FindIterable<Document> iterable = c.getMongodb().getCollection("cantante").find(new Document("nombre", ca.getNombre()));
        // Iterate the results and apply a block to each resulting document.
        // Iteramos los resultados y aplicacimos un bloque para cada documento.
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Cantante e = null;
                e = gson.fromJson(document.toJson(), Cantante.class);
                list.add(e);
                System.out.println(document);
            }
        });
        return list;
    }
      
        public List<Cantante> obtenerId() {
        Conexion c = new Conexion();
        c.conectar();
        c.setMongoClient(new MongoClient());
        c.setMongodb(c.getMongoClient().getDatabase("letrasLM"));
        Gson gson = new Gson();
        MongoCollection<Document> Clientees = c.getMongodb().getCollection("cantante");
        List<Cantante> list = new ArrayList<Cantante>();
        Cantante e;
        FindIterable<Document> iterable = c.getMongodb().getCollection("cantante").find();
        // Iterate the results and apply a block to each resulting document.
        // Iteramos los resultados y aplicacimos un bloque para cada documento.
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Cantante s = null;
                s = gson.fromJson(document.toJson(), Cantante.class);
                list.add(s);
                System.out.println(document);
            }
        });
        return list;
    }
        
        public List buscarAlbum(String nombre) {

        client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
        database = client.getDB("letrasLM");
        collection = database.getCollection("album");
          int id =Integer.valueOf (nombre);
        DBObject query = new BasicDBObject("cantante.idCantante", id);
        DBObject query2 = new BasicDBObject("_id", 0);

        DBObject orderby = new BasicDBObject("IdAlbum", 1);
        DBCursor cursor = collection.find(query,query2).sort(orderby);
        List listaIngresos = cursor.toArray();
        return listaIngresos;
    }
        
          public List buscarPrecio(String nombre) {

        client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
        database = client.getDB("letrasLM");
        collection = database.getCollection("album");
          int precio =Integer.valueOf (nombre);
        DBObject query = new BasicDBObject("precio", precio);
        DBObject query2 = new BasicDBObject("_id", 0);

        DBObject orderby = new BasicDBObject("IdAlbum", 1);
        DBCursor cursor = collection.find(query,query2).sort(orderby);
        List listaIngresos = cursor.toArray();
        return listaIngresos;
    }
}
