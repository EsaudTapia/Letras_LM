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
public class Cancion {
    int idCancion;
    String nombre;
    String letra;
    String duracion;
    String link;

    public Cancion() {
    }

    public Cancion(int idCancion, String nombre, String letra, String duracion, String link) {
        this.idCancion = idCancion;
        this.nombre = nombre;
        this.letra = letra;
        this.duracion = duracion;
        this.link = link;
    }
    
    

    
    
    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

   


}
