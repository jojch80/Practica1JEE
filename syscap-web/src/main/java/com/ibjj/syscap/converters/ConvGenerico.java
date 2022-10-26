/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.converters;

import com.ibjj.syscap.controllers.AbstractFacade;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Jose J
 * @param <T>
 */
public abstract class ConvGenerico<T> implements Converter<T> {

    protected abstract AbstractFacade<T> getAdminGenerico();

    //Pantalla a la Bdd
    @Override
    public T getAsObject(FacesContext fc, UIComponent uic, String idEnt) {
        T entidad = null;
        if (idEnt != null) {
            entidad = getAdminGenerico().buscarPorId(Integer.parseInt(idEnt));
        }
        return entidad;
    }

    //Bdd a la Pantalla
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, T entidad) {
        AtomicReference<String> idEnt = new AtomicReference<>("");
        if (entidad != null) {
            Arrays.asList(entidad.getClass().getDeclaredFields()).stream()
                    .filter(fie -> fie.isAnnotationPresent(javax.persistence.Id.class))
                    .forEach(fie -> {
                    try {
                        Method metodo = entidad.getClass().getDeclaredMethod("_persistence_get_" + fie.getName());
                        idEnt.set(metodo.invoke(entidad).toString());
                    } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                        idEnt.set("");
                    }
            });

        }
        return idEnt.toString();
    }

}
