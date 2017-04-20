/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.usuario;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;

/**
 *
 * @author Paola_Cajilema
 */
public class UsuarioLN {

    public String loadListadoDocentesPorEscuela(String codigoEntidad) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                UsuariosAD FAD = new UsuariosAD();
                FAD.loadListadoDocentesPorEscuela(accesoDatos, codigoEntidad);
                FAD.eliminarRepetidos();
                FAD.asignarNumeroPracticaDocente(accesoDatos);
                Gson gson = new Gson();
                result = gson.toJson(FAD);

                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

    public String codigoEntidad(String cedula) {
        String result = "";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                UsuarioAD FAD = new UsuarioAD();
                result = FAD.codigoEntidad(accesoDatos, cedula);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

        return result;
    }

}
