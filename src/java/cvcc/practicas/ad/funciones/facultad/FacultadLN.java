/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.facultad;

import cvcc.practicas.ad.funciones.convenio.*;
import com.google.gson.Gson;

/**
 *
 * @author Liseth
 */
public class FacultadLN {

    public String loadFacultades() {
        String result = "{}";
        try {
            FacultadesAD facAD = new FacultadesAD();
            facAD.loadFacultades();
            Gson gson = new Gson();
            result = gson.toJson(facAD);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

}
