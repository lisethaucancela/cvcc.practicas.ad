/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.entidad;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;

/**
 *
 * @author Liseth
 */
public class EntidadLN {

    public String loadEntidades() {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                EntidadesAD entidadAD = new EntidadesAD();
                entidadAD.obtenerListaEntidades(accesoDatos);
                Gson gson = new Gson();
                result = gson.toJson(entidadAD);
            }
        } catch (Exception exConec) {
            System.err.println("e: " + exConec.getMessage());
        }
        return result;
    }

}
