/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.unidadAministrativa;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;

/**
 *
 * @author Paola_Cajilema
 */
public class UnidadAdministrativaLN {

    public String loadUnidadAdministrativaPorPractica(int codigoPractica) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                UnidadAdministrativasAD FAD = new UnidadAdministrativasAD();
                FAD.loadUnidadAdministrativaPorPractica(accesoDatos, codigoPractica);//idPractica
                Gson gson = new Gson();
                result = gson.toJson(FAD);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }

    public int idUnidadAdministrativaExistente_practicaConvenio(int codigoPractica) {
        int result = -1;
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                UnidadAdministrativaAD FAD = new UnidadAdministrativaAD();
                result = FAD.idUnidadAdministrativaExistente_practicaConvenio(accesoDatos, codigoPractica);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }

    public String actualizarUnidadAdministrativa(int idPractica, int idUnidadAdministrativa) {
        String result = "";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                UnidadAdministrativaAD FAD = new UnidadAdministrativaAD();
                result = FAD.actualizarUnidadAdministrativa(accesoDatos, idPractica, idUnidadAdministrativa);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error" + e);
            //
        }
        return result;
    }

}
