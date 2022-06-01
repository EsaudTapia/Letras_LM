/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicod.letrasLM.model;

import java.util.List;

/**
 *
 * @author PC
 */
public class CancionEmbebida {
    Album album;   
    Cancion cancion;

    public CancionEmbebida() {
    }
    
    

    public CancionEmbebida(Album album, Cancion cancion) {
        this.album = album;
        this.cancion = cancion;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }

   

    

   

   

 

   
  
   
}
