/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.nivelSatisfaccion;

import cvcc.practicas.entidades.CNivelSatisfaccion;

/**
 *
 * @author Paola_Cajilema
 */
public class NivelSatisfaccionAD extends CNivelSatisfaccion {
    
    public NivelSatisfaccionAD() {
    }
    
    public NivelSatisfaccionAD(CNivelSatisfaccion nivelSatisfaccion) {
        try {
            if (nivelSatisfaccion != null) {
                this.setId(nivelSatisfaccion.getId());
                this.setValor(nivelSatisfaccion.getValor());
                this.setDescripcion(nivelSatisfaccion.getDescripcion());
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        
    }
    
}
