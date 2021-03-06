/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.proyectos;

import cvcc.practicas.ad.sw.swProyectosInvestigacion;
import cvcc.practicas.entidades.CInstitucionEjecutora;
import cvcc.practicas.entidades.CProyectos;
import cvcc.practicas.entidades.CProyectoss;

import java.util.List;

import java.util.ArrayList;
import cvcc.practicas.ad.sw.WSProyectosInvestigacion.SwCProyectoss;

/**
 *
 * @author Paola_Cajilema
 */
public class ProyectosAD extends CProyectoss {

    public void loadListaProyectosPorEntidad(String codidoEntidad) throws Exception {
        //carga datos de los proyectos de investigación de la espoch (consumo de servicios web)
        swProyectosInvestigacion sw = new swProyectosInvestigacion();

        SwCProyectoss lstProyectoss = sw.loadListaProyectosPorEntidad(codidoEntidad);
        for (int i = 0; i < lstProyectoss.getProyectos().size(); i++) {
            CProyectos objProyecto = new CProyectos();
            objProyecto.setId(lstProyectoss.getProyectos().get(i).getId());
            objProyecto.setNombre(lstProyectoss.getProyectos().get(i).getNombre());
            objProyecto.setFecha_inicio(lstProyectoss.getProyectos().get(i).getFechaInicio());
            objProyecto.setFecha_fin(lstProyectoss.getProyectos().get(i).getFechaFin());

            objProyecto.setDescripcionTipoProyecto(lstProyectoss.getProyectos().get(i).getObjTipoProyecto().getDescripcion());
            objProyecto.setDescripcionTipoInvestigacion(lstProyectoss.getProyectos().get(i).getObjTipoInvestigacion().getDescripcion());
            objProyecto.setDescripcionEstadoProyecto(lstProyectoss.getProyectos().get(i).getObjEstadoProyecto().getDescripcion());
            objProyecto.setNombreInvestigadorRresponsable(lstProyectoss.getProyectos().get(i).getObjUsuario().getNombresApellidos());

            //lista de instituciones
            List<CInstitucionEjecutora> lstIE = new ArrayList<>();

            for (int j = 0; j < lstProyectoss.getProyectos().get(i).getLstInstitucionEjecutora().size(); j++) {
                CInstitucionEjecutora obj = new CInstitucionEjecutora();
                obj.setId(lstProyectoss.getProyectos().get(i).getLstInstitucionEjecutora().get(j).getId());
                obj.setDescripcion(lstProyectoss.getProyectos().get(i).getLstInstitucionEjecutora().get(j).getDescripcion());
                obj.setCodigo(lstProyectoss.getProyectos().get(i).getLstInstitucionEjecutora().get(j).getCodigo());
                lstIE.add(obj);
            }
            objProyecto.setLstInstitucionesEjecutoras(lstIE);

            // añadir el nombre del usuario 
            //hay que revisar más servicios de funcionarios
            //espoch aparte de los del OASIS   
            //**** INSTITUCIONES EJECUTORAS
            getProyectos().add(objProyecto);
        }
        

    }

}
