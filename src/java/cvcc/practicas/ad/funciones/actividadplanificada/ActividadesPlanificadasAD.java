/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.actividadplanificada;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CActividadPlanificada;
import cvcc.practicas.entidades.CActividadesPlanificadas;
import cvcc.practicas.entidades.CPlanificacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Liseth
 */
public class ActividadesPlanificadasAD extends CActividadesPlanificadas {

    ActividadesPlanificadasAD(CActividadesPlanificadas listaActividades) {
        try {
            for (CActividadPlanificada actividad : listaActividades.getActividadesPlanificadas()) {
                ActividadPlanificadaAD actividadAD = new ActividadPlanificadaAD(actividad);
                this.addActividadesPlanificadas(actividadAD);
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    ActividadesPlanificadasAD() {
    }

    public void guardarActividades(AccesoDatos accesoDatos) {
        try {
            for (CActividadPlanificada actividades : this.getActividadesPlanificadas()) {
                ActividadPlanificadaAD actividadAD = (ActividadPlanificadaAD) actividades;
                actividadAD.guardarActividad(accesoDatos);
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public void obtenerListaActividadPlanificada(AccesoDatos accesoDatos, int codigo_planificacion) throws Exception {
        List<CActividadPlanificada> lstAP = new ArrayList<>();
        List<CActividadPlanificada> lstActividadPlanificada = new ArrayList<>();
        CActividadPlanificada objActividadP = null;
        CPlanificacion objPlanificacion = null;
        Hashtable<Integer, CActividadPlanificada> ht = new Hashtable<>();
        String sql = "WITH RECURSIVE tree AS\n"
           + "	(SELECT id_actividad_planificada, id_planificacion, descripcion, fecha_ini, fecha_fin, horas, estado, id_padre, CAST(descripcion As varchar(1000)) As fullname\n"
           + "	FROM practicas.actividad_planificada\n"
           + "	WHERE id_padre IS NULL AND id_planificacion = '" + codigo_planificacion + "'\n"
           + "	UNION ALL\n"
           + "	SELECT act.id_actividad_planificada, act.id_planificacion, act.descripcion, act.fecha_ini, act.fecha_fin, act.horas, act.estado, act.id_padre,\n"
           + "		CAST(t.fullname || '->' || act.descripcion As varchar(1000)) As fullname\n"
           + "	FROM practicas.actividad_planificada As act\n"
           + "		INNER JOIN tree AS t\n"
           + "		ON (act.id_padre = t.id_actividad_planificada)\n"
           + "	)\n"
           + "	SELECT *\n"
           + "	FROM tree ORDER BY fullname  ;";
        try {

            if (accesoDatos.EjecutarSQL(sql) == 1) {
                ResultSet rsDatos = accesoDatos.getRs();
                while (rsDatos.next()) {
                    objPlanificacion = new CPlanificacion();
                    objPlanificacion.setIdPlanificacion(rsDatos.getInt(2));
                    objActividadP = new CActividadPlanificada(rsDatos.getInt(1),
                       rsDatos.getString(3),
                       rsDatos.getString(4),
                       rsDatos.getString(5),
                       rsDatos.getInt(6),
                       rsDatos.getBoolean(7),
                       new ArrayList<CActividadPlanificada>());
                    if (rsDatos.getObject(8) != null) {
                        objActividadP.setId(rsDatos.getInt(8));  //información del padre
                    }
                    lstAP.add(objActividadP);               //Todos los objetos en una lista
                    ht.put(objActividadP.getIdActividadPlanificada(), objActividadP);
                }
                for (CActividadPlanificada lst : lstAP) {   //Recorremos lista
                    if (lst.getId() == 0) {                 //Si es padre
                        lstActividadPlanificada.add(lst);
                        getActividadesPlanificadas().add(lst);
                    } else {
                        objActividadP = ht.get(lst.getId());//Buscamos al padre por ID
                        objActividadP.getLstActividades().add(lst);//Anadimos a la lista de hijos el objeto.
                    }
                }
            }
        } catch (SQLException exConec) {
            System.err.println("e: " + exConec.getMessage());
            throw new Exception(exConec.getMessage());
        }
    }

}
