/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.beans;

import com.ibjj.syscap.beans.util.AbstractManagedBean;
import com.ibjj.syscap.controllers.CursoEstudianteFacade;
import com.ibjj.syscap.controllers.EstudianteFacade;
import com.ibjj.syscap.controllers.CursoFacade;
import com.ibjj.syscap.entities.Curso;
import com.ibjj.syscap.entities.CursoEstudiante;
import com.ibjj.syscap.entities.Estudiante;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Jose J
 */
@Named
@SessionScoped
public class EstudianteBean extends AbstractManagedBean implements Serializable {

    @Getter
    @Setter
    private Estudiante estudiante;
    @Getter
    @Setter
    private Estudiante estudianteSel;
    @Getter
    @Setter
    private List<Estudiante> listaEstudiantes;
    @Getter
    @Setter
    private String identificacionApellido;
    @Getter
    @Setter
    private List<SelectItem> listaCursos;
    @Getter
    @Setter
    private List<Curso> listaCursosObj;
    @Getter
    @Setter
    private CursoEstudiante cursoEstudiante;    
    @Getter
    @Setter
    private int idCurso;
    @Getter
    @Setter
    private String tipoIden;
    @Getter
    @Setter
    private String mascaraIden;
    @Getter
    @Setter
    private String pathImagen;
    @Getter
    @Setter
    private StreamedContent imagenBinaria;

    @Inject
    private EstudianteFacade adminEstudiante;
    @Inject
    private CursoFacade adminCurso;
    @Inject
    private CursoEstudianteFacade adminCursoEst;
    
    

    public EstudianteBean() {
        this.estudiante = new Estudiante();
        this.cursoEstudiante = new CursoEstudiante();
        this.listaEstudiantes = new ArrayList<>();
        this.listaCursos = new ArrayList<>();
        this.listaCursosObj = new ArrayList<>();
        this.tipoIden = "CED";
        this.mascaraIden = "9999999999";
        this.pathImagen = "/resources/img/usuario.webp";
    }

    /**
     * Método para buscar Estudientes
     */
    public void buscarEstudiantes() {
        try {
            this.listaEstudiantes = adminEstudiante.buscarIdentificacionApellido(identificacionApellido);
            if (listaEstudiantes.isEmpty()) {
                anadirInfo("No existen estudiantes con ese criterio");
            }
        } catch (Exception e) {
            anadirError("Error al buscar los estudiantes con ese criterio:" + e.getMessage());
        }
    }

    /**
     * Método para cargar los cursos
     */
    private void cargarCursos() {
        this.listaCursos.clear();
        this.listaCursosObj.clear();
        adminCurso.buscarTodos().forEach(curso -> {
            this.listaCursos.add(new SelectItem(curso.getIdCurso(), curso.getDescripcion()));
            this.listaCursosObj.add(curso);
        });

    }

    /**
     * Método para actualizar la máscara según tipo de identificación
     */
    public void actualizarMascaraIdentificacion() {
        switch (tipoIden) {
            case "CED":
                this.mascaraIden = "9999999999";
                break;
            case "RUC":
                this.mascaraIden = "9999999999999";
                break;
            default:
                this.mascaraIden = "999999";
                break;
        }
    }

    /**
     * Método para guardar o actualizar un estudiante
     */
    public void guardar() {
        try {
            //Recuperar el curso

            if (estudiante.getIdEstudiante() != null) {
                adminEstudiante.actualizar(estudiante);
                anadirInfo("Estudiante actualizado");
            } else {
                adminEstudiante.guardar(estudiante);
                anadirInfo("Estudiante guardado");
            }
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error al procesar la operación:" + e.getMessage());
        }
    }

    /**
     * Método para guardar o actualizar un estudiante

    /**
     */
    public void guardarConvertidor() {
        try {
            if (estudiante.getIdEstudiante()!= null) {
                adminEstudiante.actualizar(estudiante);
                anadirInfo("Estudiante actualizado");
            } else {
                adminEstudiante.guardar(estudiante);
                anadirInfo("Estudiante guardado ");
            }
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error al procesar la operación:" + e.getMessage());
        }
    }

    /**
     * Método para seleccionar un estudiante
     *
     * @param ev
     */
    public void seleccionarFila(SelectEvent<Estudiante> ev) {
        this.estudianteSel = ev.getObject();
    }

    /**
     * Método para cargar un estudiante
     */
    public void editar() {
        if (estudianteSel != null) {
            this.estudiante = estudianteSel;
            //Validar la máscara
            switch (estudiante.getCedulaPas().length()) {
                case 10:
                    this.tipoIden = "CED";
                    break;
                case 13:
                    this.tipoIden = "RUC";
                    break;
                default:
                    this.tipoIden = "PAS";
                    break;
            }
            actualizarMascaraIdentificacion();

        
            PrimeFaces.current().executeScript("PF('diaNueEstu').show();");
        } else {
            anadirError("Se debe seleccionar un Estudiante");
        }
    }


    /**
     * Método para cargar un estudiante
     */
    public void cargarCursosEst() {
        if (estudianteSel != null) {
            this.estudiante = estudianteSel;
            //Validar la máscara
            adminCursoEst.buscarPorEstudiante(estudiante);
        } else {
            anadirError("Se debe seleccionar un Estudiante");
        }
    }

    
    public void crear() {
            resetearFormulario();
            //Validar la máscara
            this.tipoIden = "CED";
            actualizarMascaraIdentificacion();
            PrimeFaces.current().executeScript("PF('diaNueEstu').show();");
    }

    /**
     * Método para eliminar un estudiante
     */
    public void eliminar() {
        if (estudianteSel != null) {
            adminEstudiante.eliminar(estudianteSel);
            anadirInfo("Estudiante eliminado");
            buscarEstudiantes();
            resetearFormulario();
        } else {
            anadirError("Se debe seleccionar un estudiante");
        }
    }


    /**
     * Método para resetear el formulario
     */
    public void resetearFormulario() {
        this.estudiante = new Estudiante();
        this.estudianteSel = null;
        this.identificacionApellido = null;
    }

    @PostConstruct
    public void inicializar() {
        cargarCursos();
    }

}
