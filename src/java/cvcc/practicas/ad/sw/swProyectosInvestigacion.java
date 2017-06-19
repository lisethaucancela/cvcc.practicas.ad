/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.sw;

import cvcc.practicas.ad.sw.WSProyectosInvestigacion.SwCProyectoss;

/**
 *
 * @author Paola_Cajilema
 */
public class swProyectosInvestigacion {

    public java.util.List<cvcc.practicas.ad.sw.WSProyectosInvestigacion.SwCInstitucionEjecutora> loadListaInstitucionesEjecutorasPorProyecto(int idPractica) {
        cvcc.practicas.ad.sw.WSProyectosInvestigacion.WSProyectosInvestigacion_Service service = new cvcc.practicas.ad.sw.WSProyectosInvestigacion.WSProyectosInvestigacion_Service();
        cvcc.practicas.ad.sw.WSProyectosInvestigacion.WSProyectosInvestigacion port = service.getWSProyectosInvestigacionPort();
        return port.loadListaInstitucionesEjecutorasPorProyecto(idPractica);
    }

    public SwCProyectoss loadListaProyectosPorEntidad(java.lang.String codidoEntidad) {
        cvcc.practicas.ad.sw.WSProyectosInvestigacion.WSProyectosInvestigacion_Service service = new cvcc.practicas.ad.sw.WSProyectosInvestigacion.WSProyectosInvestigacion_Service();
        cvcc.practicas.ad.sw.WSProyectosInvestigacion.WSProyectosInvestigacion port = service.getWSProyectosInvestigacionPort();
        return port.loadListaProyectosPorEntidad(codidoEntidad);
    }

}
