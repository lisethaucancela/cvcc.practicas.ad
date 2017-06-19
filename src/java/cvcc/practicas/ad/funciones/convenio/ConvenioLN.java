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

    public String loadConvenio(String codigo_unidad_academica) {
        String result = "{}";
        try {
            ConveniosAD conAD = new ConveniosAD();
            conAD.loadConvenio(codigo_unidad_academica);
            Gson gson = new Gson();
            result = gson.toJson(conAD);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

}
