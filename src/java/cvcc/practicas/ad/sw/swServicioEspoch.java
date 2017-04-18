/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.sw;

import cvcc.practicas.ad.sw.espoch.ArrayOfDictadoMateria;
import cvcc.practicas.ad.sw.espoch.ArrayOfFacultad;
import cvcc.practicas.ad.sw.espoch.ArrayOfMateriaPensum;
import cvcc.practicas.ad.sw.espoch.ArrayOfPeriodo;
import cvcc.practicas.ad.sw.espoch.Entidad;
import cvcc.practicas.ad.sw.espoch.Persona;

/**
 *
 * @author Paola_Cajilema
 */
public class swServicioEspoch {

    public ArrayOfDictadoMateria dictadosMateria(java.lang.String strCodCarrera, java.lang.String strCodMateria) {
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service service = new cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service();
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch port = service.getWSservicioEspochPort();
        return port.dictadosMateria(strCodCarrera, strCodMateria);
    }

    public ArrayOfMateriaPensum mallaCurricularPensumVigenteSinDescripcion(java.lang.String strCodCarrera) {
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service service = new cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service();
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch port = service.getWSservicioEspochPort();
        return port.mallaCurricularPensumVigenteSinDescripcion(strCodCarrera);
    }

    public Persona datosUsuarioCarrera(java.lang.String strCodCarrera, java.lang.String strCedula) {
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service service = new cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service();
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch port = service.getWSservicioEspochPort();
        return port.datosUsuarioCarrera(strCodCarrera, strCedula);
    }

    public ArrayOfPeriodo datosPeriodo() {
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service service = new cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service();
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch port = service.getWSservicioEspochPort();
        return port.datosPeriodo();
    }
    public static ArrayOfFacultad facultadesTotales() {
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service service = new cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service();
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch port = service.getWSservicioEspochPort();
        return port.facultadesTotales();
    }
    /* *************************************************************************
     *      Convenios SERVICIOS WEB VINCULACION
     * *************************************************************************/

    public Entidad convenios(int idEntidad) {
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service service = new cvcc.practicas.ad.sw.espoch.WSservicioEspoch_Service();
        cvcc.practicas.ad.sw.espoch.WSservicioEspoch port = service.getWSservicioEspochPort();
        return port.convenios(idEntidad);
    }

    

}
