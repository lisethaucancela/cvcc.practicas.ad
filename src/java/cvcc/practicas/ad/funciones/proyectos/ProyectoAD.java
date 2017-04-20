package cvcc.practicas.ad.funciones.proyectos;

import cvcc.practicas.entidades.CProyectos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Paola_Cajilema
 */
public class ProyectoAD extends CProyectos {
    
    ProyectoAD(CProyectos usuario) {
        try {
            if (usuario != null) {
                this.setId(usuario.getId());
                this.setNombre(usuario.getNombre());
                this.setFecha_inicio(usuario.getFecha_inicio());
                this.setFecha_fin(usuario.getFecha_fin());
                this.setNombreInvestigadorRresponsable(usuario.getNombreInvestigadorRresponsable());
                this.setDescripcionTipoInvestigacion(usuario.getDescripcionTipoInvestigacion());
                this.setDescripcionTipoProyecto(usuario.getDescripcionTipoProyecto());
                this.setDescripcionEstadoProyecto(usuario.getDescripcionEstadoProyecto());
                this.setNombreInstitucionEjecutora(usuario.getNombreInstitucionEjecutora());
                
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        
    }
    
}
