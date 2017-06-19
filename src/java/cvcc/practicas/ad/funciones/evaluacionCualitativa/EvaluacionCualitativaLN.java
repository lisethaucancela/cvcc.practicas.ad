/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.evaluacionCualitativa;

import com.google.gson.Gson;
import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CEvaluacionCualitativas;

/**
 *
 * @author Paola_Cajilema
 */
public class EvaluacionCualitativaLN {

    public boolean actualizarEvaluacionCualitativaPractica(int idPractica, String strJSON) {
        boolean boolResult = false;
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                Gson gson = new Gson();
                CEvaluacionCualitativas listaEvaluacionCualitativas = gson.fromJson(strJSON, CEvaluacionCualitativas.class);
                EvaluacionCualitativasAD FAD = new EvaluacionCualitativasAD(listaEvaluacionCualitativas);
                boolResult = FAD.actualizarEvaluacionCualitativaPractica(accesoDatos, idPractica);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.err.println("error" + e);
        }
        return boolResult;
    }

    public String loadEvaluacionCualitativaPractica(int idPractica) {
        String strResult = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                EvaluacionCualitativasAD objNivelesSatisfaccion = new EvaluacionCualitativasAD();
                objNivelesSatisfaccion.loadEvaluacionCualitativaPractica(accesoDatos, idPractica);
                Gson gson = new Gson();
                strResult = gson.toJson(objNivelesSatisfaccion);
            }
        } catch (Exception exConec) {
            System.err.println("e: " + exConec.getMessage());
        }
        return strResult;
    }

}
