/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.unidadAministrativa;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.funciones.convenio.ConvenioAD;
import cvcc.practicas.ad.sw.WSVinculacion.Convenio;

import cvcc.practicas.ad.sw.swVinculacion;
import cvcc.practicas.entidades.CUnidadAdministrativa;
import cvcc.practicas.entidades.CUnidadAdministrativas;
import java.sql.ResultSet;
import java.sql.SQLException;
//import cvcc.practicas.entidades.CFuncionario;

/**
 *
 * @author Paola_Cajilema
 */
public class UnidadAdministrativasAD extends CUnidadAdministrativas {

    public UnidadAdministrativasAD() {
    }

    public UnidadAdministrativasAD(CUnidadAdministrativas UAs) {
        for (CUnidadAdministrativa UA : UAs.getListUnidadAdministrativa()) {
            UnidadAdministrativaAD UAAD = new UnidadAdministrativaAD(UA);
            this.addUnidadAdministrativa(UAAD);
        }
    }

    public void loadUnidadAdministrativaPorPractica(AccesoDatos accesoDatos, int CodigoPractica) throws Exception {
        int idConvenio;
        try {
            //buscar por codigo de practica
            idConvenio = idConvenioPorPractica(accesoDatos, CodigoPractica);
            //carga la lista de funcioanrios
            if (idConvenio != -1) {
                //si es -1 significa que no hay un convenio asignado 
                loadUnidadAdministrativaPorPractica1(accesoDatos, idConvenio);
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
    }

    public int idConvenioPorPractica(AccesoDatos accesoDatos, int idPractica) {
        //devuelve el código idConvenio mediante el idPractica en la tabla practica_convenio
        int result = -1;
        String strSQL;
        try {
            strSQL = "SELECT \n"
               + "  practica_convenio.id_convenio\n"
               + "FROM \n"
               + "  practicas.practica_convenio\n"
               + "  where practica_convenio.id_practica='" + idPractica + "';";
            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslDatos = accesoDatos.getRs();
                if (rslDatos.next()) {
                    result = rslDatos.getInt(1);
                }

            }
        } catch (SQLException exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }

    public void loadUnidadAdministrativaPorPractica1(AccesoDatos accesoDatos, int codigoPractica) throws Exception {
        //Listar los  Funcionarios de una empresa dependiendo de la empresa a 
        //la que pertenece mediante el  codigo de prácticas
        int idConvenio;
        try {
            ConvenioAD c = new ConvenioAD();
            idConvenio = c.idConvenioPorPractica(accesoDatos, codigoPractica);
            if (idConvenio != -1) {
                //si es -1 significa que no hay un convenio asignado 
                loadUnidadesAdministrativasPorConvenio(accesoDatos, idConvenio);
            }
            //buscar por codigo de practica
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
    }

    public void loadUnidadesAdministrativasPorConvenio(AccesoDatos accesoDatos, int idConvenio) {
        try {
            swVinculacion obj = new swVinculacion();
            Convenio datosConvenio = obj.loadUnidadesAdministrativasPorConvenio(idConvenio);
            for (int i = 0; i < datosConvenio.getObjEmpresa().getListUnidadAdministrativa().size(); i++) {
                CUnidadAdministrativa cf = new CUnidadAdministrativa();
                cf.setIdUnidadAdministrativa(datosConvenio.getObjEmpresa().getListUnidadAdministrativa().get(i).getId());
                cf.setNombre(datosConvenio.getObjEmpresa().getListUnidadAdministrativa().get(i).getNombre());
                cf.setDescripcion(datosConvenio.getObjEmpresa().getListUnidadAdministrativa().get(i).getDescripcion());
                this.addUnidadAdministrativa(cf);
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
    }
}
