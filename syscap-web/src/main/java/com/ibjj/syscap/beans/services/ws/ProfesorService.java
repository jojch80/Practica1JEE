/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.ibjj.syscap.beans.services.ws;

import com.ibjj.syscap.controllers.ProfesorFacade;
import com.ibjj.syscap.entities.Profesor;
import java.util.List;
import javax.inject.Inject;
import javax. jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author user1
 */
@WebService(serviceName = "ProfesorService")
public class ProfesorService {
    @Inject
    private ProfesorFacade adminProfesor;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "consultarProfesorPorId")
    public Profesor consultarPorId(@WebParam(name = "id") Integer id) {
        return adminProfesor.buscarPorId(id);
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "consultarTodos")
    public List<Profesor> consultarTodos() {
        return adminProfesor.buscarTodos();
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "guardarProfesor")
    public String guardarProfesor(@WebParam(name = "profesor") Profesor profesor) {
       try
       {
           adminProfesor.guardar(profesor);
           return "Profesor guardado";           
       }    
       catch(Exception e)
       {
           return "Error al guardar "+e.getMessage();
       }
    }


    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "actualizarProfesor")
    public String actalizarProfesor(@WebParam(name = "profesor") Profesor profesor) {
       try
       {
            Profesor v_profesor = adminProfesor.buscarPorId(profesor.getIdProfesor());
            if (v_profesor != null) {
                adminProfesor.actualizar(profesor);
                return "Profesor actualizado";                 
            }
            return "Profesor que desea actualizar no existe";                 
       }    
       catch(Exception e)
       {
           return "Error al actualizar "+e.getMessage();
       }       
    }
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "eliminarProfesor")
    public String eliminarProfesor(@WebParam(name = "id") Integer id) {
       try
       {
            Profesor v_profesor = adminProfesor.buscarPorId(id);
            if (v_profesor != null) {
                adminProfesor.eliminar(v_profesor);
                return "Profesor eliminado";                 
            }
            return "Profesor que desea eliminar no existe";                 
       }    
       catch(Exception e)
       {
           return "Error al eliminar "+e.getMessage();
       }       
    }
}
