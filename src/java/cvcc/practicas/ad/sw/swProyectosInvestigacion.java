/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.sw;

import cvcc.practicas.ad.sw.proyectosInvestigacion.SwCProyectoss;

/**
 *
 * @author Paola_Cajilema
 */
public class swProyectosInvestigacion {

    public SwCProyectoss loadListaProyectos() {
        cvcc.practicas.ad.sw.proyectosInvestigacion.ProyectosInvestigacion_Service service = new cvcc.practicas.ad.sw.proyectosInvestigacion.ProyectosInvestigacion_Service();
        cvcc.practicas.ad.sw.proyectosInvestigacion.ProyectosInvestigacion port = service.getProyectosInvestigacionPort();
        return port.loadListaProyectos();
    }

}
