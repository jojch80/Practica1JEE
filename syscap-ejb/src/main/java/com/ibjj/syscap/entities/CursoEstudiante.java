/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ibjj.syscap.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author user1
 */
@Entity
@Table(name = "curso_estudiante")
@NamedQueries({
    @NamedQuery(name = "CursoEstudiante.findAll", query = "SELECT c FROM CursoEstudiante c"),
    @NamedQuery(name = "CursoEstudiante.findByEstado", query = "SELECT c FROM CursoEstudiante c WHERE c.estado = :estado"),
    @NamedQuery(name = "CursoEstudiante.findByFechaRegistro", query = "SELECT c FROM CursoEstudiante c WHERE c.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "CursoEstudiante.findByIdCursestu", query = "SELECT c FROM CursoEstudiante c WHERE c.idCursestu = :idCursestu")})
public class CursoEstudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cursestu")
    private Integer idCursestu;
    @JoinColumn(name = "id_curso", referencedColumnName = "id_curso")
    @ManyToOne(optional = false)
    private Curso idCurso;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private Estudiante idEstudiante;

    public CursoEstudiante() {
    }

    public CursoEstudiante(Integer idCursestu) {
        this.idCursestu = idCursestu;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdCursestu() {
        return idCursestu;
    }

    public void setIdCursestu(Integer idCursestu) {
        this.idCursestu = idCursestu;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCursestu != null ? idCursestu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CursoEstudiante)) {
            return false;
        }
        CursoEstudiante other = (CursoEstudiante) object;
        if ((this.idCursestu == null && other.idCursestu != null) || (this.idCursestu != null && !this.idCursestu.equals(other.idCursestu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ibjj.syscap.entities.CursoEstudiante[ idCursestu=" + idCursestu + " ]";
    }
    
}
