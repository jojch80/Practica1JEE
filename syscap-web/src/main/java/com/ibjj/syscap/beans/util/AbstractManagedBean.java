/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.beans.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Jose J
 */
public abstract class AbstractManagedBean {

    /**
     * Método para añadir un mensaje de información
     * @param mensaje 
     */
    public void anadirInfo(String mensaje) {
        emitirMensaje(mensaje, FacesMessage.SEVERITY_INFO);
    }

    /**
     * Método para añadir un mensaje de error
     * @param mensaje 
     */
    public void anadirError(String mensaje) {
        emitirMensaje(mensaje, FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Método para generar y emitir el mensaje
     * @param mensaje mensaje a mostrar
     * @param severidad  tipo de mensaje
     */
    private void emitirMensaje(String mensaje, FacesMessage.Severity severidad) {
        FacesMessage mensajeJSF = new FacesMessage();
        mensajeJSF.setSummary(mensaje);
        mensajeJSF.setSeverity(severidad);
        FacesContext.getCurrentInstance().addMessage(null, mensajeJSF);
    }
}
