/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.controllers;

import com.ibjj.syscap.entities.Estudiante;
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
public class EstudianteFacade extends AbstractFacade<Estudiante> {

    @PersistenceContext(unitName = "syscapPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteFacade() {
        super(Estudiante.class);
    }

    /**
     * Método para buscar un paciente por identificación o su apellido paterno
     *
     * @param identificacionApellido
     * @return
     */
    public List<Estudiante> buscarIdentificacionApellido(String identificacionApellido) {
        Query conEstu = em.createNativeQuery("select p.*\n"
                + " from estudiante p \n"
                + " where p.cedulaPas like ?1 or \n"
                + "      p.apellidos  like ?1 ", Estudiante.class);
        conEstu.setParameter(1, "%" + identificacionApellido + "%");
        return conEstu.getResultList();
    }

}
