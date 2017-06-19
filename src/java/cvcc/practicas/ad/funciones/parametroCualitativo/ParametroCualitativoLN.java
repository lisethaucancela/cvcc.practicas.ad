/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.parametroCualitativo;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;

/**
 *
 * @author Paola_Cajilema
 */
public class ParametroCualitativoLN {

    public String loadParametrosCualitativos() {
        String strResult = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                ParametroCualitativosAD objParametroCualitativo = new ParametroCualitativosAD();
                objParametroCualitativo.loadParametrosCualitativos(accesoDatos);
                Gson gson = new Gson();
                strResult = gson.toJson(objParametroCualitativo);
            }
        } catch (Exception exConec) {
            System.err.println("e: " + exConec.getMessage());
        }
        return strResult;
    }

}
