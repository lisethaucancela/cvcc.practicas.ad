/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.funcionario;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;

/**
 *
 * @author Paola_Cajilema
 */
public class FuncionarioLN {

    public String loadFuncionariosPorPractica(int codigoPractica) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                FuncionariosAD FAD = new FuncionariosAD();
                FAD.loadFuncionariosPorPractica(accesoDatos, codigoPractica);
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

    public int idFuncionarioExistentePracticaConvenio(int codigoPractica) {
        int result = -1;
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                FuncionarioAD FAD = new FuncionarioAD();
                result = FAD.idFuncionarioExistentePracticaConvenio(accesoDatos, codigoPractica);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }

    public boolean actualizarAsignarFuncionario(int idPractica, int idFuncionario) {
        boolean result = false;
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                FuncionarioAD FAD = new FuncionarioAD();
                result = FAD.actualizarAsignarFuncionario(accesoDatos, idPractica, idFuncionario);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }

}
