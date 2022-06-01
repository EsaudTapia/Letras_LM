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
public class Album {
    int idAlbum;
    String nombre;
    String genero;
    int precio;
    String fechaPublicacion;
    String discografica;
    String foto;

    public Album() {
    }

    public Album(int idAlbum, String nombre, String genero, int precio, String fechaPublicacion, String discografica, String foto) {
        this.idAlbum = idAlbum;
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
        this.fechaPublicacion = fechaPublicacion;
        this.discografica = discografica;
        this.foto = foto;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getDiscografica() {
        return discografica;
    }

    public void setDiscografica(String dicografica) {
        this.discografica = dicografica;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    

  
    
    
}
