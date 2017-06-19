/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.materia;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.funciones.usuario.UsuariosAD;
import cvcc.practicas.entidades.CUsuarios;

/**
 *
 * @author Paola_Cajilema
 */
public class MateriaLN {
    
    public String loadMateriasCarrera(String CodigoCarrera) {
        String result = "{}";
        try {
            MateriasAD mAD = new MateriasAD();
            mAD.loadMateriasCarrera(CodigoCarrera);
            Gson gson = new Gson();
            result = gson.toJson(mAD);
            
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }
    
    public String loadDocentesMateria(String CodCarrera, String CodigoMateria) {
        String result = "{}";
        try {
            MateriaAD mAD = new MateriaAD();
            UsuariosAD uAD = new UsuariosAD();
            uAD.loadDocentesMaterias(CodCarrera, CodigoMateria);
            UsuariosAD auxiliar = new UsuariosAD(uAD.getUsuarios());
            mAD.setListUsuario(auxiliar);
            Gson gson = new Gson();
            result = gson.toJson(mAD);
            
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }
    
}
