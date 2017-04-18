/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.periodo;

import cvcc.practicas.ad.sw.espoch.ArrayOfPeriodo;
import cvcc.practicas.ad.sw.espoch.Periodo;
import cvcc.practicas.ad.sw.swServicioEspoch;
import cvcc.practicas.entidades.CPeriodo;

/**
 *
 * @author Liseth
 */
public class PeriodoAD extends CPeriodo {

    public void SWDatosPeriodo() {
        swServicioEspoch objSW = new swServicioEspoch();
        ArrayOfPeriodo arrayPeriodo = objSW.datosPeriodo();
        for (Periodo periodo : arrayPeriodo.getPeriodo()) {
            if (periodo.getCodigo().equals(this.getCodigo())) {
                this.setDescripcion(periodo.getDescripcion());
            }
        }
    }

}
