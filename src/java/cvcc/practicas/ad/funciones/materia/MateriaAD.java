/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.materia;

import cvcc.practicas.entidades.CMateria;

/**
 *
 * @author Paola_Cajilema
 */
public class MateriaAD extends CMateria {

    public MateriaAD() {
    }

    public MateriaAD(CMateria objMateria) {
        try {
            if (objMateria != null) {
                this.setId(objMateria.getId());
                this.setCodigo(objMateria.getCodigo());
                this.setNombre(objMateria.getNombre());
                this.setNivel(objMateria.getNivel());
                this.setArea(objMateria.getArea());
                this.setCreditos(objMateria.getCreditos());
                this.setHorasTeoricas(objMateria.getHorasTeoricas());
                this.setHorasPracticas(objMateria.getHorasPracticas());
                this.setListUsuario(objMateria.getListUsuario());
            }
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

}
