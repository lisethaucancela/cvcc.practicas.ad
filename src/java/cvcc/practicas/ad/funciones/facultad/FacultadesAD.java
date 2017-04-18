/*
 * To change convenio license header, choose License Headers in Project Properties.
 * To change convenio template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.facultad;

import cvcc.practicas.ad.funciones.convenio.*;
import cvcc.practicas.ad.sw.espoch.ArrayOfFacultad;
import cvcc.practicas.ad.sw.espoch.Entidad;
import cvcc.practicas.ad.sw.espoch.Convenio;
import cvcc.practicas.ad.sw.espoch.Facultad;
import cvcc.practicas.ad.sw.swServicioEspoch;
import cvcc.practicas.entidades.CConvenio;
import cvcc.practicas.entidades.CConvenios;
import cvcc.practicas.entidades.CFacultad;
import cvcc.practicas.entidades.CFacultades;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Liseth
 */
public class FacultadesAD extends CFacultades {
    
    

    public void loadFacultades() {
        ArrayOfFacultad arrayFacultades = swServicioEspoch.facultadesTotales();

        for (Facultad facultadSW : arrayFacultades.getFacultad()) {
            CFacultad facultad = new CFacultad(); // puede ser FacultadAD? FacultadAD c = new FacultadAD(facultad);
            facultad.setNombre(facultadSW.getNombre());
            facultad.setCodigo(facultadSW.getCodigo());

            this.addFacultad(facultad);
        }
    }
}
