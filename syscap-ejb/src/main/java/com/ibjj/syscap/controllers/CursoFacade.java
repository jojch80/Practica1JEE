/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.controllers;

import com.ibjj.syscap.entities.Curso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jose J
 */
//No-view-interface
@Stateless //EJB Sin estado @Stateful con estado (shopping cart)
public class CursoFacade extends AbstractFacade<Curso>{

    //Identifico cual es la unidad de persistencia
    @PersistenceContext(unitName = "syscapPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CursoFacade() {
        super(Curso.class);
    }
    
}
