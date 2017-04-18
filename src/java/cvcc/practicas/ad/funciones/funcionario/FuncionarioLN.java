/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.funcionario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CFuncionario;
import cvcc.practicas.entidades.CFuncionarios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paola_Cajilema
 */
public class FuncionarioLN {

    public String loadDatosUnFuncionario(int codigoPractica) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                FuncionarioAD FAD = new FuncionarioAD();
                CFuncionario cF = FAD.loadDatosUnFuncionario(accesoDatos, codigoPractica);
                Gson gson = new Gson();
                result = gson.toJson(cF);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }

    public String loadListaFuncionarios(int codigoPractica) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                FuncionariosAD FAD = new FuncionariosAD();
                FAD.ListarFuncionariosPorPractica(accesoDatos, codigoPractica);
                Gson gson = new Gson();
                result = gson.toJson(FAD);
                /* 
                 //lista en una mejor forma de visualización 
                 Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                 result = prettyGson.toJson(FAD);
                 */
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }

    public boolean InsertarFuncionario(String strCadenaJSON) {
        boolean ret = false;
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                Gson gson = new Gson();
                CFuncionarios result = gson.fromJson(strCadenaJSON, CFuncionarios.class);
                FuncionariosAD fAD = new FuncionariosAD(result);
                ret = fAD.insertarFuncionario(accesoDatos);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return ret;
    }

    public boolean EliminarFuncionario(String strCadenaJSON) {
        boolean ret = false;
        try {
            String cadenaListaF = loadListaFuncionarios(1);
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                CFuncionarios result = new CFuncionarios();
                Gson gson = new Gson();
                result = gson.fromJson(strCadenaJSON, CFuncionarios.class);

                CFuncionarios resultcadenaListaF = new CFuncionarios();
                Gson gCadenaListaF = new Gson();
                resultcadenaListaF = gCadenaListaF.fromJson(cadenaListaF, CFuncionarios.class);
                FuncionarioAD _funcionarioADcadenaListaF = new FuncionarioAD();
                ret = _funcionarioADcadenaListaF.eliminarFuncionarios(accesoDatos, result, resultcadenaListaF);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return ret;
    }

}
