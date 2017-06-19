/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.institucionesEjecutoras;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;

/**
 *
 * @author Paola_Cajilema
 */
public class InstitucionesEjecutoraLN {

    public String loadInstitucionesEjecutorasPorPractica(int idPractica) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            InstitucionesEjecutorasAD iEAD = new InstitucionesEjecutorasAD();
            if (accesoDatos.Connectar() == 2) {
                iEAD.loadInstitucionesEjecutorasPorPractica(accesoDatos, idPractica);
            }
            Gson gson = new Gson();
            result = gson.toJson(iEAD);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }
}
