/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.proyectos;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;

/**
 *
 * @author Paola_Cajilema
 */
public class ProyectoLN {

    public String loadListaProyectosPorEntidad(String codidoEntidad) {
        String result = "{}";
        try {
            ProyectosAD FAD = new ProyectosAD();
            FAD.loadListaProyectosPorEntidad(codidoEntidad);

            Gson gson = new Gson();
            result = gson.toJson(FAD);

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

    public String guardarProyectoEstudiante(int idPractica, int idProyecto) {
        String result = "{}";
        try {
            ProyectoAD FAD = new ProyectoAD();
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                result = FAD.guardarProyectoEstudiante(accesoDatos, idPractica, idProyecto);
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

    public int codigoProyectoAsignado(int idPractica) {
        int result = -1;
        try {
            ProyectoAD FAD = new ProyectoAD();
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                result = FAD.codigoProyectoAsignado(accesoDatos, idPractica);
            }

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

}
