/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase controladora de vista que permitirá gestionar la pantalla de
 * index.xhtml
 *
 * @author Jose J
 */
@Named //Se define que la clase IndexBean sera un controlador de vista
@SessionScoped //Alcance del Bean
public class IndexBean implements Serializable {

    //Campos para ingresar información
    @Getter
    @Setter
    private String txtNomUsu;
    @Getter
    @Setter
    private String txtClaUsu;
    //Campo dinámico
    @Getter
    @Setter
    private boolean banError;
    @Getter
    @Setter
    private String estiloBoton;

    public IndexBean() {
        this.txtNomUsu = "admin";
        this.estiloBoton = "margin-left: 40%;";
    }

    /**
     * Método para validar el usuario
     *
     * @return
     */
    public String validarUsuario() {

        if (txtNomUsu.equals("admin") && txtClaUsu.equals("1234")) {
            banError = false;
            /*
             * Se redirigue a la página de principal qu está configurada como
             * regla de navegación en el archivo faces-config.xml
             */
            return "principal";
        } else {
            //Mensaje de Error
            FacesMessage mensajeJSF = new FacesMessage();
            mensajeJSF.setSummary("Credenciales Incorrectas");
            mensajeJSF.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, mensajeJSF);
            banError = true;
            return null;
        }
    }

    public void cambiarEtiqueta() {

        if (txtNomUsu.equals("admin")) {
            estiloBoton = "margin-left: 40%; color:white; background-color:red";
        } else {
            this.estiloBoton = "margin-left: 40%;";
        }
    }

}
