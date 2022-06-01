/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unicod.letrasLM.model;

/**
 *
 * @author PC
 */
public class AlbumEmbebido {
    Album album;
    Cantante cantante;

    public AlbumEmbebido() {
    }

    public AlbumEmbebido(Album album, Cantante cantante) {
        this.album = album;
        this.cantante = cantante;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Cantante getCantante() {
        return cantante;
    }

    public void setCantante(Cantante cantante) {
        this.cantante = cantante;
    }
    
    
}
