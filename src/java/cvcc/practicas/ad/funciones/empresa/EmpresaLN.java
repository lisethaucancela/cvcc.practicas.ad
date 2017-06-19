/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.empresa;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CEmpresa;

import cvcc.practicas.entidades.CEmpresas;

/**
 *
 * @author Liseth
 */
public class EmpresaLN {

    public String loadEmpresaPorPractica(int idPractica) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                EmpresaAD FAD = new EmpresaAD();
                CEmpresa obj = new CEmpresa();
                obj = FAD.loadEmpresaPorPractica(accesoDatos, idPractica);
                Gson gson = new Gson();
                result = gson.toJson(obj);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }

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

}
