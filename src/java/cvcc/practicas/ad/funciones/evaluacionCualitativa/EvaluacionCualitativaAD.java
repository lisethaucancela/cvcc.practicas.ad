/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.evaluacionCualitativa;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CEvaluacionCualitativa;
import java.sql.ResultSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Paola_Cajilema
 */
public class EvaluacionCualitativaAD extends CEvaluacionCualitativa {

    public EvaluacionCualitativaAD() {
    }

    public EvaluacionCualitativaAD(CEvaluacionCualitativa evaluacionCualitativa) {
        try {
            if (evaluacionCualitativa != null) {
                this.setId(evaluacionCualitativa.getId());
                this.setIdParametro(evaluacionCualitativa.getIdParametro());
                this.setIdNivelSatisfaccion(evaluacionCualitativa.getIdNivelSatisfaccion());
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public boolean guardarEvaluacionCualitativa(AccesoDatos accesoDatos, int idInforme) {
        boolean intResult = false;
        try {
            StringBuilder strSQL = new StringBuilder();
            strSQL.append("INSERT INTO practicas.evaluacion_cualitativa(\n"
               + "            id_informe, id_parametro_cualitativo,id_nivel_satisfaccion)\n"
               + "         VALUES ( '" + idInforme + "', '" + this.getIdParametro() + "', '" + this.getIdNivelSatisfaccion() + "');");
            if (accesoDatos.EjecutarUpdate(strSQL.toString()) == 1) {
                intResult = true;
            }
        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
        }
        return intResult;
    }

    public boolean actualizarEvaluacionCualitativa(AccesoDatos accesoDatos, int idInforme) {
        boolean intResult = false;
        try {
            StringBuilder strSQL = new StringBuilder();
            strSQL.append("UPDATE practicas.evaluacion_cualitativa\n"
               + "   SET id_parametro_cualitativo='" + this.getIdParametro() + "', \n"
               + "       id_nivel_satisfaccion='" + this.getIdNivelSatisfaccion() + "' \n"
               + "   WHERE evaluacion_cualitativa.id_informe='" + idInforme + "' AND evaluacion_cualitativa.id_parametro_cualitativo='"+this.getIdParametro()+"';");
            if (accesoDatos.EjecutarUpdate(strSQL.toString()) == 1) {
                intResult = true;
            }
        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
        }
        return intResult;
    }

}
