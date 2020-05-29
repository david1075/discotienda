/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.beans;

import edu.unicundi.model.Cancion;
import edu.unicundi.model.Album;
import edu.unicundi.model.Artista;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
/**
 *Managed Bean la creacion de artistas, canciones, y albumes para la venta
 * @author David jimenez - Anderson Torres
 */
@Named
@RequestScoped
public class CrearMusica{

    private Artista artista = new Artista();
    private Album album = new Album();
    private Cancion cancion = new Cancion();
    private List<Artista> artistas = new ArrayList();
    private List<Album> albumes = new ArrayList();
    private List<Cancion> canciones = new ArrayList();
    private List<String> listaNomArti = new ArrayList();
    private List<String> listaNomAlbu = new ArrayList();
     private Date dateDe, dateTimeDe;
    
    
    public CrearMusica() {
         
    }
    /**
    *Metodo que carga las listas con los datos provenientes de los archivos
    */
    @PostConstruct
    public void cargarArchivo (){
     // carga del calendario
     dateDe = GregorianCalendar.getInstance().getTime();
     dateTimeDe = GregorianCalendar.getInstance().getTime();
    //cargando artistas
    ObjectInputStream ois;
        File miFile = new File("Artistas.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        try{
            entrada = new FileInputStream(miFile);
            reader = new ObjectInputStream(entrada);
            artistas = (List<Artista>)reader.readObject();
            
        }catch(IOException | ClassNotFoundException ex){
            System.err.println(ex.getMessage());
        }
        //cargando albumes
         ObjectInputStream ois2;
        File miFile2 = new File("Albumes.txt");
        FileOutputStream salida2 = null;
        FileInputStream entrada2 = null;
        ObjectOutputStream writer2 = null;
        ObjectInputStream reader2 = null;
        try{
            entrada2 = new FileInputStream(miFile2);
            reader2 = new ObjectInputStream(entrada2);
            albumes = (List<Album>)reader2.readObject();
            
        }catch(IOException | ClassNotFoundException ex){
            System.err.println(ex.getMessage());
        }
        //cargando canciones
         ObjectInputStream ois3;
        File miFile3 = new File("Canciones.txt");
        FileOutputStream salida3 = null;
        FileInputStream entrada3 = null;
        ObjectOutputStream writer3 = null;
        ObjectInputStream reader3 = null;
        try{
            entrada3 = new FileInputStream(miFile3);
            reader3 = new ObjectInputStream(entrada3);
            canciones = (List<Cancion>)reader3.readObject();
            
        }catch(IOException | ClassNotFoundException ex){
            System.err.println(ex.getMessage());
        }
        cargarListaNomArti();
        cargarListaNomAlbum();
    }
    
   
    /**
    *LLena una lista con el nombre de los artistas para el componente menuArtistas
    */
    public void cargarListaNomArti(){
        listaNomArti.clear();
        for(Artista arti : artistas){
            listaNomArti.add(arti.getNombre());
        }
    }
     /**
    *LLena una lista con el nombre de los albumes para el componente menuAlbumes
    */
    public void cargarListaNomAlbum(){
        listaNomAlbu.clear();
        for(Album albu : albumes){
            listaNomAlbu.add(albu.getNombre());
        }
    } 
    /**
    *Metodo que elimina un artista de la lista de artistas
     * @param arti
    */
     public void eliminarArtista(Artista arti){
         this.artistas.remove(arti);
         ObjectInputStream ois;
        File miFile = new File("Artistas.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
           try{
               miFile.createNewFile();
               salida = new FileOutputStream(miFile);
               writer = new ObjectOutputStream(salida);
               writer.writeObject(artistas);
           }catch(IOException ex){
               System.err.println(ex.getMessage());
           }finally{
               if(salida != null){
                   try{
                       salida.close();
                       if(writer != null){
                           writer.close();
                       
                        }
                   }catch(IOException ex){
                       System.err.println(ex.getMessage());
                   }
               }
           }
            FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Artista Eliminado!:",  artista.getNombre()));
        cargarListaNomArti();
     }
     /**
    *Metodo para eliminar un album de la lista albumes
     * @param albu
    */
    public void eliminarAlbum(Album albu){
       this.albumes.remove(albu);
        ObjectInputStream ois;
        File miFile2 = new File("Albumes.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
       
        try{
               miFile2.createNewFile();
               salida = new FileOutputStream(miFile2);
               writer = new ObjectOutputStream(salida);
               writer.writeObject(albumes);
           }catch(IOException ex){
               System.err.println(ex.getMessage());
           }finally{
               if(salida != null){
                   try{
                       salida.close();
                       if(writer != null){
                           writer.close();
                       
                        }
                   }catch(IOException ex){
                       System.err.println(ex.getMessage());
                   }
               }
           }
         FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Album Eliminado!:",  album.getNombre()));
        cargarListaNomAlbum();
    }
     /**
    *Metodo para eliminar una cancion de la lista canciones
     * @param canc
    */
      public void eliminarCancion(Cancion canc){
          this.canciones.remove(canc);
        ObjectInputStream ois;
        File miFile3 = new File("Canciones.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
          try{
               miFile3.createNewFile();
               salida = new FileOutputStream(miFile3);
               writer = new ObjectOutputStream(salida);
               writer.writeObject(canciones);
           }catch(IOException ex){
               System.err.println(ex.getMessage());
           }finally{
               if(salida != null){
                   try{
                       salida.close();
                       if(writer != null){
                           writer.close();
                       
                        }
                   }catch(IOException ex){
                       System.err.println(ex.getMessage());
                   }
               }
           }
           FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Cancion eliminada!:",  cancion.getNombre()));
      }
    /**
    *Metodo que edita los datos de el objeto artista seleccionado
     * @param event
    */
   public void editarArtista(RowEditEvent event){
        ObjectInputStream ois;
        File miFile = new File("Artistas.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
           try{
               miFile.createNewFile();
               salida = new FileOutputStream(miFile);
               writer = new ObjectOutputStream(salida);
               writer.writeObject(this.artistas);
           }catch(IOException ex){
               System.err.println(ex.getMessage());
           }finally{
               if(salida != null){
                   try{
                       salida.close();
                       if(writer != null){
                           writer.close();
                       
                        }
                   }catch(IOException ex){
                       System.err.println(ex.getMessage());
                   }
               }
           }
   }
    /**
    *Metodo que edita los datos de el objeto album seleccionado
     * @param event
    */
   public void editarAlbum(RowEditEvent event){
       System.out.println("estoy editando album");
        ObjectInputStream ois;
        File miFile2 = new File("Albumes.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
       
        try{
               miFile2.createNewFile();
               salida = new FileOutputStream(miFile2);
               writer = new ObjectOutputStream(salida);
               writer.writeObject(albumes);
           }catch(IOException ex){
               System.err.println(ex.getMessage());
           }finally{
               if(salida != null){
                   try{
                       salida.close();
                       if(writer != null){
                           writer.close();
                       
                        }
                   }catch(IOException ex){
                       System.err.println(ex.getMessage());
                   }
               }
           }
   }
    /**
    *Metodo que edita los datos de el objeto cancion seleccionado
     * @param event
    */
   public void editarCancion(RowEditEvent event){
       ObjectInputStream ois;
        File miFile3 = new File("Canciones.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
          try{
               miFile3.createNewFile();
               salida = new FileOutputStream(miFile3);
               writer = new ObjectOutputStream(salida);
               writer.writeObject(canciones);
           }catch(IOException ex){
               System.err.println(ex.getMessage());
           }finally{
               if(salida != null){
                   try{
                       salida.close();
                       if(writer != null){
                           writer.close();
                       
                        }
                   }catch(IOException ex){
                       System.err.println(ex.getMessage());
                   }
               }
           }
   }
   /**
    *Metodo que agrega un nuevo artista a la lista artistas y lo guarda en el archivo
    */
   public void agregarArtista(){
        ObjectInputStream ois;
        File miFile = new File("Artistas.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        try{
            entrada = new FileInputStream(miFile);
            reader = new ObjectInputStream(entrada);
            artistas = (List<Artista>)reader.readObject();
            
        }catch(IOException | ClassNotFoundException ex){
            System.err.println(ex.getMessage());
        }
           try{
               miFile.createNewFile();
               salida = new FileOutputStream(miFile);
               writer = new ObjectOutputStream(salida);
               artistas.add(artista);
               writer.writeObject(artistas);
           }catch(IOException ex){
               System.err.println(ex.getMessage());
           }finally{
               if(salida != null){
                   try{
                       salida.close();
                       if(writer != null){
                           writer.close();
                       
                        }
                   }catch(IOException ex){
                       System.err.println(ex.getMessage());
                   }
               }
           }
           FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Artista creado!:",  artista.getNombre()));
        cargarListaNomArti();
    }
   /**
    *Metodo que agrega un nuevo album a la lista albumes y lo guarda en el archivo
    */
    public void agregarAlbum(){
        ObjectInputStream ois;
        File miFile2 = new File("Albumes.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        try{
            entrada = new FileInputStream(miFile2);
            reader = new ObjectInputStream(entrada);
            albumes = (List<Album>)reader.readObject();
            
        }catch(IOException | ClassNotFoundException ex){
            System.err.println(ex.getMessage());
        }
           try{
               miFile2.createNewFile();
               salida = new FileOutputStream(miFile2);
               writer = new ObjectOutputStream(salida);
               albumes.add(album);
               writer.writeObject(albumes);
           }catch(IOException ex){
               System.err.println(ex.getMessage());
           }finally{
               if(salida != null){
                   try{
                       salida.close();
                       if(writer != null){
                           writer.close();
                       
                        }
                   }catch(IOException ex){
                       System.err.println(ex.getMessage());
                   }
               }
           }
           FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Album creado!:",  album.getNombre()));
        cargarListaNomAlbum();
    }
    /**
    *Metodo que agrega una nueva cancion a la lista canciones y la guarda en el archivo
    */
    public void agregarCancion(){
        float acum = 0;
        ObjectInputStream ois;
        File miFile3 = new File("Canciones.txt");
        FileOutputStream salida = null;
        FileInputStream entrada = null;
        ObjectOutputStream writer = null;
        ObjectInputStream reader = null;
        try{
            entrada = new FileInputStream(miFile3);
            reader = new ObjectInputStream(entrada);
            canciones = (List<Cancion>)reader.readObject();
            
        }catch(IOException | ClassNotFoundException ex){
            System.err.println(ex.getMessage());
        }
           try{
               miFile3.createNewFile();
               salida = new FileOutputStream(miFile3);
               writer = new ObjectOutputStream(salida);
               canciones.add(cancion);
               writer.writeObject(canciones);
           }catch(IOException ex){
               System.err.println(ex.getMessage());
           }finally{
               if(salida != null){
                   try{
                       salida.close();
                       if(writer != null){
                           writer.close();
                       
                        }
                   }catch(IOException ex){
                       System.err.println(ex.getMessage());
                   }
               }
           }
           FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Cancion creada!:",  cancion.getNombre()));
       
            for(int i=0; i<canciones.size(); i++){
            if(canciones.get(i).getAlbum().equals(cancion.getAlbum())){
                    acum = acum + canciones.get(i).getPrecio();
                    System.out.println(canciones.get(i).getAlbum());
            }
                     
        }
         
        
        acum = (float) (acum - acum*0.1);
        album.setPrecio(acum);
        /*cargarArchivo();
        for(int i=0; i<albumes.size(); i++){
            if(albumes.get(i).getNombre().equals(album.getNombre())){
                albumes.get(i).setPrecio(acum);
            }
        }
        albumes.remove(album);
        agregarAlbum();  */
    }

    public Date getDateDe() {
        return dateDe;
    }

    public void setDateDe(Date dateDe) {
        this.dateDe = dateDe;
    }

    public Date getDateTimeDe() {
        return dateTimeDe;
    }

    public void setDateTimeDe(Date dateTimeDe) {
        this.dateTimeDe = dateTimeDe;
    }
    
    
    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
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

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public  List<Album> getAlbumes() {
        return albumes;
    }

    public  void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public  List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public List<String> getListaNomArti() {
        return listaNomArti;
    }

    public void setListaNomArti(List<String> listaNomArti) {
        this.listaNomArti = listaNomArti;
    }

    public List<String> getListaNomAlbu() {
        return listaNomAlbu;
    }

    public void setListaNomAlbu(List<String> listaNomAlbu) {
        this.listaNomAlbu = listaNomAlbu;
    }

   
  
}
