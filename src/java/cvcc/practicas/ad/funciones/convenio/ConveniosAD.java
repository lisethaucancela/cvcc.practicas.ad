/*
 * To change convenio license header, choose License Headers in Project Properties.
 * To change convenio template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.convenio;

import cvcc.practicas.ad.sw.WSVinculacion.Convenio;
import cvcc.practicas.ad.sw.WSVinculacion.Entidad;
import cvcc.practicas.ad.sw.swVinculacion;
import cvcc.practicas.entidades.CConvenios;

/**
 *
 * @author Liseth
 */
public class ConveniosAD extends CConvenios {

    public void loadConvenio(String codigo_unidad_academica) {
        swVinculacion oVinculacion = new swVinculacion();
        Entidad list = oVinculacion.loadConvenios(codigo_unidad_academica);

        for (Convenio convenioSW : list.getListConvenios()) {
            ConvenioAD convenioAD = new ConvenioAD(convenioSW);
            this.addConvenios(convenioAD);
        }

    }

   
}
