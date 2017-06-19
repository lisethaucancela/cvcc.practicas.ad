/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.planificacion;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CPlanificacion;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Liseth
 */
public class PlanificacionAD extends CPlanificacion {

    public PlanificacionAD(CPlanificacion planificacion) {
        this.setIdPlanificacion(planificacion.getIdPlanificacion());
        this.setFechaFin(planificacion.getFechaFin());
        this.setFechaInicio(planificacion.getFechaInicio());
        this.setObjPractica(planificacion.getObjPractica());
    }

    public void guardarPlanificacion(AccesoDatos accesoDatos) {
        try {
            if (this.getIdPlanificacion() < 0) {
                this.insertarPlanificacion(accesoDatos);
            } else {
                this.modificarPlanificacion(accesoDatos);
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public boolean insertarPlanificacion(AccesoDatos accesoDatos) {
        boolean result = false;
        String strSQL;
        try {
            strSQL = "INSERT INTO practicas.planificacion(id_practica, fecha_ini, fecha_fin, horas_planificadas)\n"
                    + "    VALUES ('" + this.getObjPractica().getIdPractica() + "', '" + this.getFechaInicio() + "', '" + this.getFechaFin() + "', \n"
                    + "    '0' );";

            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
                    ResultSet rslDatos = accesoDatos.getRs();
                    if (rslDatos.next()) {
                        result = rslDatos.getBoolean(1);
                    }
                }
            }
        } catch (SQLException exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }

    public boolean modificarPlanificacion(AccesoDatos accesoDatos) {
        boolean result = false;
        String strSQL;
        try {
            strSQL = "UPDATE practicas.planificacion\n"
                    + "   SET fecha_ini='" + this.getFechaInicio() + "', fecha_fin='" + this.getFechaFin() + "', \n"
                    + " horas_planificadas='" + this.getHorasPlanificadas() + "' WHERE id_planificacion='" + this.getIdPlanificacion() + "';";

            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
                    ResultSet rslDatos = accesoDatos.getRs();
                    if (rslDatos.next()) {
                        result = rslDatos.getBoolean(1);
                    }
                }
            }
        } catch (Exception exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }

}
