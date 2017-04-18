/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.convenio;

import cvcc.practicas.entidades.CConvenio;

/**
 *
 * @author Liseth
 */
public class ConvenioAD extends CConvenio{

    public ConvenioAD(CConvenio convenio) {
        this.setDescripcion(convenio.getDescripcion());
        this.setEstado(convenio.isEstado());
        this.setFechaFin(convenio.getFechaFin());
        this.setFechaInicio(convenio.getFechaInicio());
        this.setIdConvenio(convenio.getIdConvenio());
        this.setObjEmpresa(convenio.getObjEmpresa());
        this.setObjEntidad(convenio.getObjEntidad());
        
    }
 
    
}
