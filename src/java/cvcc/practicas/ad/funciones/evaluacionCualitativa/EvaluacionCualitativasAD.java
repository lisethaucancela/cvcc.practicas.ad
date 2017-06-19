/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.evaluacionCualitativa;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CEvaluacionCualitativa;
import cvcc.practicas.entidades.CEvaluacionCualitativas;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Paola_Cajilema
 */
public class EvaluacionCualitativasAD extends CEvaluacionCualitativas {

    public EvaluacionCualitativasAD() {
    }

    public EvaluacionCualitativasAD(CEvaluacionCualitativas evaluacionCualitativas) {
        for (CEvaluacionCualitativa funcionario : evaluacionCualitativas.getEvaluacionCualitativas()) {
            EvaluacionCualitativaAD funcionarioAD = new EvaluacionCualitativaAD(funcionario);
            this.addEvaluacionCualitativa(funcionarioAD);
        }
    }

    public void loadEvaluacionCualitativaPractica(AccesoDatos accesoDatos, int idPractica) {
        int idInforme = -1;
        int idInformeEvaluacionCualitativa = -1;
        try {
            idInforme = buscarIdInforme4Informe(accesoDatos, idPractica);
            if (idInforme == -1) { //buscar id de INFORME en tabla INFORME
                idInforme = guardarCrearInforme4(accesoDatos, idPractica);
            }
            //buscar id de INFORME en tabla EVALUACION CUALITATIVA
            idInformeEvaluacionCualitativa = buscarIdInforme4EvaluacionCualitativa(accesoDatos, idInforme);
            String sql = "SELECT id_evaluacion_cualitativa, id_informe, id_parametro_cualitativo, \n"
               + "       id_nivel_satisfaccion\n"
               + "  FROM practicas.evaluacion_cualitativa WHERE evaluacion_cualitativa.id_informe='" + idInformeEvaluacionCualitativa + "'\n"
               + "  ORDER BY id_informe; ";
            if (accesoDatos.EjecutarSQL(sql) == 1) {
                ResultSet rsDatos = accesoDatos.getRs();
                while (rsDatos.next()) {
                    CEvaluacionCualitativa objEvaluacionCualitativa = new CEvaluacionCualitativa();
                    objEvaluacionCualitativa.setId(rsDatos.getInt(1));
                    objEvaluacionCualitativa.setIdParametro(rsDatos.getInt(3));
                    objEvaluacionCualitativa.setIdNivelSatisfaccion(rsDatos.getInt(4));
                    this.addEvaluacionCualitativa(objEvaluacionCualitativa);
                }
            }
        } catch (Exception exConec) {
            System.err.println("error: " + exConec.getMessage());

        }

    }

    public boolean actualizarEvaluacionCualitativaPractica(AccesoDatos accesoDatos, int idPractica) {
        boolean boolResult = false;
        int idInforme = -1;
        idInforme = buscarIdInforme4Informe(accesoDatos, idPractica);
        if (idInforme == -1) { //buscar id de INFORME en tabla INFORME
            idInforme = guardarCrearInforme4(accesoDatos, idPractica);
        }
        //buscar id de INFORME en tabla EVALUACION CUALITATIVA
        int idInformeEvaluacionCualitativa = -1;
        idInformeEvaluacionCualitativa = buscarIdInforme4EvaluacionCualitativa(accesoDatos, idInforme);
        if (idInformeEvaluacionCualitativa == -1) {
            boolResult = guardarEvaluacionCualitativa(accesoDatos, idInforme);
        } else {
            boolResult = actualizarEvaluacionCualitativa(accesoDatos, idInforme);
        }
        return boolResult;
    }

    public int buscarIdInforme4Informe(AccesoDatos accesoDatos, int idPractica) {
        int intResult = -1;
        try {
            StringBuilder strSQL = new StringBuilder();
            strSQL.append("SELECT informe.id_informe FROM practicas.informe WHERE informe.id_practica='" + idPractica + "';");
            if (accesoDatos.EjecutarSQL(strSQL.toString()) == 1) {
                ResultSet rslCodigoEmpresa = accesoDatos.getRs();
                while (rslCodigoEmpresa.next()) {
                    intResult = rslCodigoEmpresa.getInt(1);
                }
            }
        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
        }
        return intResult;
    }

    public int guardarCrearInforme4(AccesoDatos accesoDatos, int idPractica) {
        int intResult = -1;
        String recomendacion = "ninguna";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            StringBuilder strSQL = new StringBuilder();
            strSQL.append("INSERT INTO practicas.informe(id_practica,id_tipo_informe,fecha,recomendacion,total_horas)\n"
               + "         VALUES ( '" + idPractica + "', '" + 4 + "', '" + dateFormat.format(date) + "', '" + recomendacion + "','" + 0 + "') returning id_informe;");
            if (accesoDatos.EjecutarSQL(strSQL.toString()) == 1) {
                ResultSet rslCodigoEmpresa = accesoDatos.getRs();
                while (rslCodigoEmpresa.next()) {
                    intResult = rslCodigoEmpresa.getInt(1);
                }
            }
        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
        }
        return intResult;
    }

    public int buscarIdInforme4EvaluacionCualitativa(AccesoDatos accesoDatos, int idInforme) {
        int intResult = -1;
        try {
            StringBuilder strSQL = new StringBuilder();
            strSQL.append("SELECT  evaluacion_cualitativa.id_informe\n"
               + "  FROM practicas.evaluacion_cualitativa,\n"
               + "	practicas.informe\n"
               + "  WHERE evaluacion_cualitativa.id_informe=informe.id_informe AND\n"
               + "  informe.id_informe='" + idInforme + "';");
            if (accesoDatos.EjecutarSQL(strSQL.toString()) == 1) {
                ResultSet rslCodigoEmpresa = accesoDatos.getRs();
                while (rslCodigoEmpresa.next()) {
                    intResult = rslCodigoEmpresa.getInt(1);
                }
            }
        } catch (Exception e) {
            System.err.println("error : " + e.getMessage());
        }
        return intResult;
    }

    public boolean guardarEvaluacionCualitativa(AccesoDatos accesoDatos, int idInforme) {
        boolean intResult = false;
        try {
            for (CEvaluacionCualitativa actividades : this.getEvaluacionCualitativas()) {
                EvaluacionCualitativaAD actividadAD = (EvaluacionCualitativaAD) actividades;
                actividadAD.guardarEvaluacionCualitativa(accesoDatos, idInforme);
            }
            intResult = true;
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return intResult;
    }

    public boolean actualizarEvaluacionCualitativa(AccesoDatos accesoDatos, int idInforme) {
        boolean intResult = false;
        try {
            for (CEvaluacionCualitativa actividades : this.getEvaluacionCualitativas()) {
                EvaluacionCualitativaAD actividadAD = (EvaluacionCualitativaAD) actividades;
                actividadAD.actualizarEvaluacionCualitativa(accesoDatos, idInforme);
            }
            intResult = true;
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return intResult;
    }

}
