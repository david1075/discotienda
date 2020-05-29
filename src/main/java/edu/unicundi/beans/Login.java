/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *Manages Bean que se encarga del control de inicio de sesion
 * @author David Jimenez - Anderson Torres
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable{

    
    private String localeCode;
    private static Map<String, Object> countries;// creamos un map donde esperamos una cadena y un objeto
    private String usuario;
    private String clave;
    /**
     * Creates a new instance of Login
     */
    public Login() {
    }
    /**
    *Metodo que regresa al inicio de sesion desde cualquier formulario
    */
    public String volver(){
        return  "login?faces-redirect=true";
    }
   
    /**
    *Metodo que valida el inicio de sesion exitoso
    */
    public String inicio() throws IOException{
        FacesMessage msg = null;
        String redireccion = null;
        if (usuario.equals("admin") && clave.equals("admin")) {
      msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", usuario);
     FacesContext context = FacesContext.getCurrentInstance();
     redireccion = "crearMusica?faces-redirect=true";
    }else if(usuario.equals("user") && clave.equals("user")){
      msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@", usuario);
     FacesContext context = FacesContext.getCurrentInstance();
     redireccion = "comprarMusica?faces-redirect=true";
    }else{
         msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                             "Credenciales no válidas");
    }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return redireccion;
    }
  

    


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
