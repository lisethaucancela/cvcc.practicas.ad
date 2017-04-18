/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.convenio;

import com.google.gson.Gson;

/**
 *
 * @author Liseth
 */
public class ConvenioLN {

    public String loadConvenio(int idEntidad) {
        String result = "{}";
        try {
            ConveniosAD conAD = new ConveniosAD();
            conAD.loadConvenio(idEntidad);
            Gson gson = new Gson();
            result = gson.toJson(conAD);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

}
