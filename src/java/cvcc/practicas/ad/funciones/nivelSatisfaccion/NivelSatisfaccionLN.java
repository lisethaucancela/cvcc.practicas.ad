/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.nivelSatisfaccion;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;

/**
 *
 * @author Paola_Cajilema
 */
public class NivelSatisfaccionLN {

    public String loadNivelesSatisfaccion() {
        String strResult = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                NivelSatisfaccionsAD objNivelesSatisfaccion = new NivelSatisfaccionsAD();
                objNivelesSatisfaccion.loadNivelesSatisfaccion(accesoDatos);
                Gson gson = new Gson();
                strResult = gson.toJson(objNivelesSatisfaccion);
            }
        } catch (Exception exConec) {
            System.err.println("e: " + exConec.getMessage());
        }
        return strResult;
    }
}
