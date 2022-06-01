/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicod.letrasLM.controller;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.unicod.letrasLM.conexion.Conexion;
import org.unicod.letrasLM.model.AlbumEmbebido;

/**
 *
 * @author PC
 */
public class ControllerAlbum {

    public static MongoClient client;
    public static DB database;
    public static DBCollection collection;
    BasicDBObject document = new BasicDBObject();

    public void insert(AlbumEmbebido c) {
        Conexion con = new Conexion();
        con.conectar();
        con.setMongoClient(new MongoClient());
        con.setMongodb(con.getMongoClient().getDatabase("letrasLM"));
        Gson gson = new Gson();
        MongoCollection<Document> album = con.getMongodb().getCollection("album");

        BasicDBObject objeto = new BasicDBObject();//Creamos otro tipo de documento
        objeto.put("idCantante", c.getCantante().getIdCantante());//Clave y valor pero como sera subjson se para el otro documento
        objeto.put("nombre", c.getCantante().getNombre());
        objeto.put("apellidoPaterno", c.getCantante().getApellidoPaterno());
        objeto.put("apellidoMaterno", c.getCantante().getApellidoMaterno());
        objeto.put("fechaNacimiento", c.getCantante().getFechaNacimiento());
        objeto.put("sexo", c.getCantante().getSexo());
        objeto.put("nacionalidad", c.getCantante().getNacionalidad());
        objeto.put("foto", c.getCantante().getFoto());
        objeto.put("estatus", c.getCantante().getEstatus());

//Clave y valor comun
//        db.insertOne(getDocument(objeto));//Necesitamos un caste para que lo lea como Document comun
//        
//        DBCollection colec = (DBCollection) con.getMongodb().getCollection("cantante");
        BasicDBObject documento = new BasicDBObject();//Es para crear otro tipo de documento
        documento.put("idAlbum", c.getAlbum().getIdAlbum());
        documento.put("nombre", c.getAlbum().getNombre());//Un clave valor para el subjson
        documento.put("genero", c.getAlbum().getGenero());
        documento.put("precio", c.getAlbum().getPrecio());
        documento.put("fechaPublicacion", c.getAlbum().getFechaPublicacion());
        documento.put("discografica", c.getAlbum().getDiscografica());
        documento.put("foto", c.getAlbum().getFoto());
        documento.put("cantante", objeto);

        album.insertOne(getDocument(documento));//Necesitamos un caste para que lo lea como Document comun

//        
//       Document documento = new Document("idCantante",c.getCantante().getApellidoMaterno()).append("nombre",c.getNombre())
//                                        .append("apellidoPaterno", c.getApellidoPaterno()).append("apellidoMaterno", c.getApellidoMaterno())
//                                        .append("fechaNacimiento", c.getFechaNacimiento()).append("sexo",c.getSexo())
//                                        .append("nacionalidad",c.getNacionalidad()).append("foto", c.getFoto())
//                                        .append("estatus", 1);//Asi se agrega un nuevo documento
//        System.out.println(documento);
//     album.insertOne(documento);//Asi se inserta al servidor de mongo db el documento  
    }

    public static Document getDocument(BasicDBObject doc) {
        if (doc == null) {
            return null;
        }
        return new Document(doc.toMap());
    }

    public List<AlbumEmbebido> mostrarAlbum() {
        Conexion c = new Conexion();
        c.setMongoClient(new MongoClient());
        c.setMongodb(c.getMongoClient().getDatabase("letrasLM"));
        Gson gson = new Gson();
//        MongoCollection<Document> album = c.getMongodb().getCollection("album");//Segun yo eso se usa para un update 

        List<AlbumEmbebido> list = new ArrayList<AlbumEmbebido>();

        FindIterable<Document> iterable = c.getMongodb().getCollection("album").find(new Document() {
        }); // según yo el filtro de que si contenia edgar es así, no? "/Edgar/i" si
        // Iterate the results and apply a block to each resulting document.
        // Iteramos los resultados y aplicacimos un bloque para cada documento.
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                AlbumEmbebido a = null;
                a = gson.fromJson(document.toJson(), AlbumEmbebido.class);
                list.add(a);
                System.out.println(document);
            }
        });
        return list;
    }

    public List getAll(String filtro) {
//        Conexion c = new Conexion();
//        c.setMongoClient(new MongoClient());
//        c.setMongodb(c.getMongoClient().getDatabase("letrasLM"));
//        Gson gson = new Gson();
//        collection = database.getCollection("album");
//        DBObject query = new BasicDBObject();
//
//        DBCursor cursor = collection.find(query);
//        List album = cursor.toArray();
//        return album;

        client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
        database = client.getDB("letrasLM");
        collection = database.getCollection("album");
        DBObject query = new BasicDBObject();
        DBObject orderby = new BasicDBObject("IdAlbum", 1);
        DBCursor cursor = collection.find(query).sort(orderby);
        List listaIngresos = cursor.toArray();
        return listaIngresos;
    }

    public List buscarCanciones(String nombre) {

        client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
        database = client.getDB("letrasLM");
        collection = database.getCollection("cancion");
        int id = Integer.valueOf(nombre);
        DBObject query = new BasicDBObject("album.idAlbum", id);

        DBObject orderby = new BasicDBObject("IdCancion", 1);
        DBCursor cursor = collection.find(query).sort(orderby);
        List listaIngresos = cursor.toArray();
        return listaIngresos;
    }

    public List buscarNombre(String nombre) {

        client = new MongoClient(new MongoClientURI("mongodb://127.0.0.1:27017"));
        database = client.getDB("letrasLM");
        collection = database.getCollection("cancion");       
        DBObject query = new BasicDBObject("nombre", nombre);     
        DBObject orderby = new BasicDBObject("IdAlbum", 1);
        DBCursor cursor = collection.find(query).sort(orderby);
        List listaIngresos = cursor.toArray();
        return listaIngresos;
    }
}
