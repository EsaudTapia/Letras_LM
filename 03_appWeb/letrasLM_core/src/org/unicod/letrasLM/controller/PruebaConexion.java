/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicod.letrasLM.controller;

import com.mongodb.MongoClient;
import java.util.List;
import org.unicod.letrasLM.model.Cantante;

/**
 *
 * @author PC
 */
public class PruebaConexion {
  public static void main(String[] args) {
//        ControladorCantante cc = new ControladorCantante();
//        cc.mostrarCliente();
        System.out.println("Prueba conexión MongoDB");
        MongoClient mongo = crearConexion();
        Cantante  c = new Cantante();
        //Insertar Visita
        /*
        c.setIdCliente(1.0);
        c.setNombre("Edgar David");
        c.setApellidoPaterno("Hermosillo");
        c.setApellidoMaterno("Barbosa");
        c.setEdad(20);
        c.setCorreo("edgar_david00@hotmail.com");
        c.setNombreUsuario("Edgarsito2020");
        c.setPassword("Soyunacontraseña");
        c.setGenero("M");
        c.setToken("");
        c.setFoto("");
        c.setEstatus(1);*/
        //Las visitas no importan, verdad?
        //cc.modificarCliente(c);
        //Se tendrá que hacer de otra manera, déjame ver si cande lo tiene aquí
        if (mongo != null) {
            System.out.println("Lista de bases de datos: ");
            printDatabases(mongo);

        } else {
            System.out.println("Error: Conexión no establecida");
        }
    }

    private static MongoClient crearConexion() {
        MongoClient mongo = null;
        mongo = new MongoClient("localhost", 27017);

        return mongo;
    }

    private static void printDatabases(MongoClient mongo) {
        List dbs = mongo.getDatabaseNames();
        for (Object db : dbs) {
            System.out.println(" - " + db);
        }
    }
}