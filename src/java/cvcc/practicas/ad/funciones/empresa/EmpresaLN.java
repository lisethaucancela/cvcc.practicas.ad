/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.empresa;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CEmpresas;

/**
 *
 * @author Liseth
 */
public class EmpresaLN {

    public String loadEmpresas() {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                EmpresasAD empresaAD = new EmpresasAD();
                empresaAD.obtenerListaEmpresas(accesoDatos);
                Gson gson = new Gson();
                result = gson.toJson(empresaAD);
            }
        } catch (Exception exConec) {
            System.err.println("e: " + exConec.getMessage());
        }
        return result;
    }

    public String guardarEmpresas(String strCadenaJSON) {
        String result = "DATOS NO INGRESADOS";
        try {
            Gson gson = new Gson();
            CEmpresas listaEmpresas = gson.fromJson(strCadenaJSON, CEmpresas.class);
            EmpresasAD empresasAD = new EmpresasAD(listaEmpresas);
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                empresasAD.guardarEmpresas(accesoDatos);
                result = "DATOS INGRESADOS";
            }
            accesoDatos.Desconectar();
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return result;
    }
}
