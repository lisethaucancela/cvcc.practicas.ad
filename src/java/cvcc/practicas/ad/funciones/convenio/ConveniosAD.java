/*
 * To change convenio license header, choose License Headers in Project Properties.
 * To change convenio template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.convenio;

import cvcc.practicas.ad.sw.espoch.Entidad;
import cvcc.practicas.ad.sw.espoch.Convenio;
import cvcc.practicas.ad.sw.swServicioEspoch;
import cvcc.practicas.entidades.CConvenio;
import cvcc.practicas.entidades.CConvenios;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Liseth
 */
public class ConveniosAD extends CConvenios {

    public void loadConvenio(int idEntidad) {
        swServicioEspoch oServicioE = new swServicioEspoch();
        Entidad list = oServicioE.convenios(idEntidad);

        for (Convenio convenioSW : list.getListConvenios()) {
            CConvenio convenio = new CConvenio();
            convenio.setDescripcion(convenioSW.getDescripcion());
            convenio.setEstado(convenioSW.isEstado());
            String fecha = convenioSW.getFechaFin().toString();
            cvcc.practicas.ad.sw.espoch.Date swdate = convenioSW.getFechaFin();
            
            Timestamp stamp = Timestamp.valueOf(swdate.toString());
            Date date = Date.valueOf(fecha);
                    
            convenio.setFechaFin(date);

            ConvenioAD c = new ConvenioAD(convenio);

            c.setDescripcion(convenio.getDescripcion());
            c.setEstado(convenio.isEstado());
            //Date fecha = convenio.getFechaInicio();
            // c.setFechaFin((Date)convenio.getFechaFin());
            c.setIdConvenio(convenio.getIdConvenio());
            

        }

    }

}
