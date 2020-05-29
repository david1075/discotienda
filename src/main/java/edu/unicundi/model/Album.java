/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author David Jimenez - Anderson Torres
 */
public class Album implements Serializable{
    private String nombre;
    private String genero;
    private String artista;
    private Date Año;
    private float precio;

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

    public String getArtista() {
        return artista;
    }

    public void setArtista(String Artista) {
        this.artista = Artista;
    }

    public Date getAño() {
        return Año;
    }

    public void setAño(Date Año) {
        this.Año = Año;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
}
