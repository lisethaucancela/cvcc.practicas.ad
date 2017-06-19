/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.parametroCualitativo;

import cvcc.practicas.entidades.CParametroCualitativo;

/**
 *
 * @author Paola_Cajilema
 */
public class ParametroCualitativoAD extends CParametroCualitativo {

    public ParametroCualitativoAD() {
    }

    public ParametroCualitativoAD(CParametroCualitativo parametroCualitativo) {
        try {
            if (parametroCualitativo != null) {
                this.setId(parametroCualitativo.getId());
                this.setDescripcion(parametroCualitativo.getDescripcion());
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

    }

}
