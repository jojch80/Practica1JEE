/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.beans;

import com.ibjj.syscap.entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Jose J
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Getter
    @Setter
    private Usuario usuario;

    public LoginBean() {
        this.usuario = new Usuario(); //Registro
    }

    /**
     * Método para validar el usuario
     */
    public void validarUsuario() {
        FacesMessage mensajeJSF = new FacesMessage();

        if (usuario.getNombreUsu().equals("admin") && usuario.getClaveUsu().equals("1234")) {
            //Mensaje de Información
            mensajeJSF.setSummary("Usuario correcto, bienvenido!!");
            mensajeJSF.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext.getCurrentInstance().addMessage(null, mensajeJSF);
            try {
                //Redireccionar
                FacesContext.getCurrentInstance().getExternalContext().redirect("./pages/principal.mat");
            } catch (IOException ex) {
                mensajeJSF.setSummary("No se pudo redireccionar al menú principal:" + ex.getMessage());
                mensajeJSF.setSeverity(FacesMessage.SEVERITY_ERROR);
                FacesContext.getCurrentInstance().addMessage(null, mensajeJSF);
            }
        } else {
            //Mensaje de Error
            mensajeJSF.setSummary("Credenciales Incorrectas");
            mensajeJSF.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, mensajeJSF);
            //Redireccionar
        }
    }

}
