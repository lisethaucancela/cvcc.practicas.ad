/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.modalidad;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CModalidad;
import cvcc.practicas.entidades.CModalidades;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Liseth
 */
public class ModalidadLN {

    public String loadModalidades() {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                ModalidadesAD APAD = new ModalidadesAD();
                APAD.obtenerListaModalidades(accesoDatos);
                Gson gson = new Gson();
                result = gson.toJson(APAD);
            }
        } catch (Exception exConec) {
            System.err.println("e: " + exConec.getMessage());
        }
        return result;
    }

    public String guardarModalidades(String strCadenaJSON) {
        String result = "DATOS NO INGRESADOS";
        try {
            Gson gson = new Gson();
            CModalidades listaModalidades = gson.fromJson(strCadenaJSON, CModalidades.class);
            ModalidadesAD ModalidadesAD = new ModalidadesAD(listaModalidades);
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                ModalidadesAD.guardarModalidades(accesoDatos);
                result = "DATOS INGRESADOS";
            }
            accesoDatos.Desconectar();
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return result;
    }
}
