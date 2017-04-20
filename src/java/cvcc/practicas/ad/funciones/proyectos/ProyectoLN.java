/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.proyectos;

import com.google.gson.Gson;

/**
 *
 * @author Paola_Cajilema
 */
public class ProyectoLN {

    public String loadLProyectos() {
        String result = "{}";
        try {
            ProyectosAD FAD = new ProyectosAD();
            FAD.loadLProyectos();

            Gson gson = new Gson();
            result = gson.toJson(FAD);

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }
}
