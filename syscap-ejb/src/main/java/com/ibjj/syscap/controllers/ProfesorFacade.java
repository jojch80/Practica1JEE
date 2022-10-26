/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.controllers;

/**
 *
 * @author user1
 */
import com.ibjj.syscap.entities.Profesor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jose J
 */
@Stateless
public class ProfesorFacade extends AbstractFacade<Profesor> {

    @PersistenceContext(unitName = "syscapPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesorFacade() {
        super(Profesor.class);
    }

    /**
     * Método para buscar un paciente por identificación o su apellido paterno
     *
     * @param identificacionApellido
     * @return
     */
    public List<Profesor> buscarIdentificacionApellido(String identificacionApellido) {
        Query conPac = em.createNativeQuery("select p.*\n"
                + "from profesor p \n"
                + "where p.cedula like ?1 or \n"
                + "      p.apellidos  like ?1   ", Profesor.class);
        conPac.setParameter(1, "%" + identificacionApellido + "%");
        return conPac.getResultList();
    }

}
