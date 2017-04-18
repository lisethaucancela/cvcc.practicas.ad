/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.actividadplanificada;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CActividadPlanificada;
import cvcc.practicas.entidades.CActividadesPlanificadas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Liseth
 */
public class ActividadPlanificadaLN {

    public String loadActividadesPlanificadas(int codPlanificacion) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                ActividadesPlanificadasAD APAD = new ActividadesPlanificadasAD();
                APAD.obtenerListaActividadPlanificada(accesoDatos, codPlanificacion);
                Gson gson = new Gson();
                result = gson.toJson(APAD);
            }
        } catch (Exception exConec) {
            System.err.println("e: " + exConec.getMessage());
        }
        return result;
    }

    public String guardarActividades(String strCadenaJSON) {
        String result = "DATOS NO INGRESADOS";
        try {
            Gson gson = new Gson();
            CActividadesPlanificadas listaActividades = gson.fromJson(strCadenaJSON, CActividadesPlanificadas.class);
            ActividadesPlanificadasAD ActividadesAD = new ActividadesPlanificadasAD(listaActividades);
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                ActividadesAD.guardarActividades(accesoDatos);
                result = "DATOS INGRESADOS";
            }
            accesoDatos.Desconectar();
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return result;
    }
}
