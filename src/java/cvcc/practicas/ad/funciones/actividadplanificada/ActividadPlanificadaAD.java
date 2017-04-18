/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.actividadplanificada;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CActividadPlanificada;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Liseth
 */
public class ActividadPlanificadaAD extends CActividadPlanificada {

    ActividadPlanificadaAD(CActividadPlanificada actividad) {
        this.setIdActividadPlanificada(actividad.getIdActividadPlanificada());
        this.setDescripcion(actividad.getDescripcion());
        this.setEstado(actividad.isEstado());
        this.setFechaInicio(actividad.getFechaInicio());
        this.setFechaFin(actividad.getFechaFin());
        this.setHoras(actividad.getHoras());
        this.setId(actividad.getId());
        if (actividad.getLstActividades() != null) {
            for (CActividadPlanificada subactividad : actividad.getLstActividades()) {
                ActividadPlanificadaAD subactividadAD = new ActividadPlanificadaAD(subactividad);
                this.addLstActividadesPlanificadas(subactividadAD);
            }
        }
    }

    public void guardarActividad(AccesoDatos accesoDatos) {
        int idPadre = 0;
        try {
            if (this.getIdActividadPlanificada() < 0) {
                idPadre = this.insertarActividadPlanificada(accesoDatos);
            } else {
                this.modificarActividadPlanificada(accesoDatos);
            }
            if (idPadre > 0) {
                if (this.getLstActividades() != null) {
                    for (CActividadPlanificada subactividad : this.getLstActividades()) {
                        subactividad.setId(idPadre);
                        ActividadPlanificadaAD actividadAD = (ActividadPlanificadaAD) subactividad;
                        actividadAD.guardarActividad(accesoDatos);
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public int insertarActividadPlanificada(AccesoDatos accesoDatos) {
        int result = 0;
        String strSQL;
        try {
            if (this.getId() > 0) {
                strSQL = " INSERT INTO practicas.actividad_planificada( id_planificacion, descripcion, fecha_ini, \n"
                        + " fecha_fin, horas, estado, id_padre)\n"
                        + " VALUES (1, '" + this.getDescripcion() + "',\n"
                        + " '" + this.getFechaInicio() + "', '" + this.getFechaFin() + "', \n"
                        + " '" + this.getHoras() + "', 'false','" + this.getId() + "');";
            } else {
                strSQL = " INSERT INTO practicas.actividad_planificada( id_planificacion, descripcion, fecha_ini, \n"
                        + " fecha_fin, horas, estado)\n"
                        + " VALUES (1, '" + this.getDescripcion() + "',\n"
                        + " '" + this.getFechaInicio() + "', '" + this.getFechaFin() + "', \n"
                        + " '" + this.getHoras() + "', 'false');";
            }
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate_id(strSQL) == 1) {
                    ResultSet rslDatos = accesoDatos.getRs();
                    if (rslDatos.next()) {
                        result = rslDatos.getInt(1);
                    }
                }
            }
        } catch (SQLException exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }

    public boolean modificarActividadPlanificada(AccesoDatos accesoDatos) {
        boolean result = false;
        String strSQL;
        try {
            strSQL = "UPDATE practicas.actividad_planificada\n"
                    + "   SET descripcion='" + this.getDescripcion() + "', \n"
                    + " fecha_ini='" + this.getFechaInicio() + "', fecha_fin='" + this.getFechaFin() + "',\n"
                    + " horas='" + this.getHoras() + "'\n"
                    + " WHERE id_actividad_planificada= '" + this.getIdActividadPlanificada() + "';";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
                    /*  ResultSet rslDatos = accesoDatos.getRs();
                     if (rslDatos.next()) {
                     result = rslDatos.getBoolean(1);
                     }*/
                    result = true;
                }
            }
        } catch (Exception exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }
}
