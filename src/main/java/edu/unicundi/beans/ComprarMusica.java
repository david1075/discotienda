/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.beans;

import edu.unicundi.model.Cancion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *Managed Bean que controla la compra de canciones
 * @author David Jimenez - Anderson Torres
 */
@Named(value = "comprarMusica")
@ApplicationScoped
public class ComprarMusica {
   
    /**
    *Injeccion de la clase CrearMusica
    */
    @Inject
    private CrearMusica crearMusica;
    
    private List<Cancion> canciones;
    private List<Cancion> cancionesFiltradas;
    private static List<Cancion> cancionesCompradas = new ArrayList();
    
    public ComprarMusica() {
    
    }
    /**
    *Metodo que carga la lista de canciones con los datos guardados en los archivos
    */
    @PostConstruct
    public void cargar(){
        crearMusica.cargarArchivo();
        canciones = crearMusica.getCanciones();
    }
    /**
    *Filtro para la busqueda de canciones
     * @param value
     * @param filter
     * @param locale
    */
    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
    /**
    *Este metodo dispara un dialog que muestra la lista de canciones compradas
    */
    public void viewCompra() {
        String lista = "Tus canciones: \n";
        for(Cancion canc:cancionesCompradas){
            lista = lista + "\n -" + canc.getNombre();
        }
        lista = lista + "\n\n" +  Total();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Has comprado!", lista);
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
    /**
    *Metodo para eliminar una cancion de el carrito de compras
     * @param compras
    */
    public void eliminarCancion(Cancion compras){
        cancionesCompradas.remove(compras);
    }
    public String Total(){
        float acum = 0;
        for(Cancion can: cancionesCompradas){
            acum = acum + can.getPrecio();
        }
        return "Total: $ " + Float.toString(acum) + "      ";
    }
    /**
    *Metodo que agrega la cancion al carrito
     * @param cancion
    */
    public void comprar(Cancion cancion){
        int error = 0;
        for(Cancion canc: cancionesCompradas){
            if(canc.getNombre().equals(cancion.getNombre())){
                error = 1;
            }
        }
        if(error == 1){
            
            FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Ya tienes esta cancion en tu carro",  cancion.getNombre()));
        }else{
            this.cancionesCompradas.add(cancion);
            FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Se ha añadido a tu carro de compras:",  cancion.getNombre()));
        }
        
    }
    
   public CrearMusica getCrearMusica() {
        return crearMusica;
    }

    public void setCrearMusica(CrearMusica crearMusica) {
        this.crearMusica = crearMusica;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public List<Cancion> getCancionesFiltradas() {
        return cancionesFiltradas;
    }

    public void setCancionesFiltradas(List<Cancion> cancionesFiltradas) {
        this.cancionesFiltradas = cancionesFiltradas;
    }

    public List<Cancion> getCancionesCompradas() {
        return cancionesCompradas;
    }

    public void setCancionesCompradas(List<Cancion> cancionesCompradas) {
        this.cancionesCompradas = cancionesCompradas;
    }

   
  
    
}
