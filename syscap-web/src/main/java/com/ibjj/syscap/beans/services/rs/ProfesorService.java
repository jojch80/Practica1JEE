/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.beans.services.rs;

import com.ibjj.syscap.controllers.ProfesorFacade;
import com.ibjj.syscap.entities.Profesor;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author user1
 */
@Path("/profesor")
@Consumes(value= MediaType.APPLICATION_JSON)
@Produces(value= MediaType.APPLICATION_JSON)
public class ProfesorService {
    @Inject
    private ProfesorFacade adminProfesor;
    
    @GET
    @Path("/{id}")
    public Profesor consultaPorId(@PathParam(value="id") int idProfesor){
       return adminProfesor.buscarPorId(idProfesor);
    }
    
    @GET
    @Path("/consultarTodos")
    public List<Profesor> consultaPorId(){
       return adminProfesor.buscarTodos();
    }
    
    @POST
    public String guardar(Profesor profesor)
    {
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

    
    @PUT
    public String actualizar(Profesor profesor)
    {
       try
       {
            Profesor v_profesor = adminProfesor.buscarPorId(profesor.getIdProfesor());
            if (v_profesor != null) {
                adminProfesor.actualizar(profesor);
                return "Profesor eliminado";                 
            }
            return "Profesor que desea actualizar no existe";                 
       }    
       catch(Exception e)
       {
           return "Error al actualizar "+e.getMessage();
       }       
    }
    
    @DELETE
    @Path("/{id}")
    public String eliminar(@PathParam(value="id") int idProfesor){
       try
       {
            Profesor v_profesor = adminProfesor.buscarPorId(idProfesor);
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
