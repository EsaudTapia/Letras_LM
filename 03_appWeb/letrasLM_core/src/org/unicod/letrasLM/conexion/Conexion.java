/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicod.letrasLM.conexion;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author PC
 */
public class Conexion {
     Conexion c = null;

    public MongoDatabase getMongodb() {
        return mongodb;
    }

    public void setMongodb(MongoDatabase mongodb) {
        this.mongodb = mongodb;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static void setMongoClient(MongoClient aMongoClient) {
        mongoClient = aMongoClient;
    }

    private static MongoClient mongoClient;    // Java MongoDB client (Cliente Java MongoDB)

    private MongoDatabase mongodb;

    BasicDBObject document = new BasicDBObject();

    public void conectar() {
        try {

            setMongoClient(new MongoClient());
            setMongodb(getMongoClient().getDatabase("letrasLM"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
