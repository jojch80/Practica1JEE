/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.controllers;

import com.ibjj.syscap.entities.Curso;
import com.ibjj.syscap.entities.CursoEstudiante;
import com.ibjj.syscap.entities.Estudiante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jose J
 */
//No-view-interface
@Stateless //EJB Sin estado @Stateful con estado (shopping cart)
public class CursoEstudianteFacade extends AbstractFacade<CursoEstudiante>{

    //Identifico cual es la unidad de persistencia
    @PersistenceContext(unitName = "syscapPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoEstudianteFacade() {
        super(CursoEstudiante.class);
    }
    
    public List<Curso> buscarPorEstudiante(Estudiante idEstudiante) {
        TypedQuery<Curso> conHisNum = em.createQuery("Select ce from CursoEstudiante ce "
                + " where ce.idEstudiante = :numero", Curso.class);
        conHisNum.setParameter("numero", idEstudiante );
        return conHisNum.getResultList();
    }    
    
}
