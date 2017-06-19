/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.unidadAministrativa;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CUnidadAdministrativa;
import java.sql.ResultSet;

/**
 *
 * @author Paola_Cajilema
 */
public class UnidadAdministrativaAD extends CUnidadAdministrativa {

    public UnidadAdministrativaAD() {
    }

    UnidadAdministrativaAD(CUnidadAdministrativa UA) {
        try {
            if (UA != null) {
                this.setIdUnidadAdministrativa(UA.getIdUnidadAdministrativa());
                this.setNombre(UA.getNombre());
                this.setDescripcion(UA.getDescripcion());
            }
        } catch (Exception e) {
            //
        }

    }

    public int idUnidadAdministrativaExistente_practicaConvenio(AccesoDatos accesoDatos, int idPractica) {
        //id del funcionario existente dado una idpractica
        int result = -1;

        try {
            String strSQL = " SELECT id_unidad_administrativa \n"
               + "  FROM practicas.practica_convenio where practica_convenio.id_practica='" + idPractica + "';";
            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslDatosPracticas = accesoDatos.getRs();
                while (rslDatosPracticas.next()) {
                    result = rslDatosPracticas.getInt(1);
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }

    public String actualizarUnidadAdministrativa(AccesoDatos accesoDatos, int idPractica, int idUnidadAdministrativa) {
        String result = "";
        try {
            result = actualizarUnidadAdministrativa1(accesoDatos, idPractica, idUnidadAdministrativa);
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }

    public String actualizarUnidadAdministrativa1(AccesoDatos accesoDatos, int idPractica, int id_unidad_administrativa) {
        String result = "";
        try {

            //fin comprobar que el proyecto no se encuentra registrado 
            String strSQL = "UPDATE practicas.practica_convenio\n"
               + "   SET  id_unidad_administrativa='" + id_unidad_administrativa + "'\n"
               + " WHERE practica_convenio.id_practica='" + idPractica + "' RETURNING  id_practica_convenio;";
            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslDatosPracticas = accesoDatos.getRs(); //verificar que un registro se modifico
                while (rslDatosPracticas.next()) {
                    int reslt = rslDatosPracticas.getInt(1);
                    if (reslt != 0) {
                        result = "DATOS ACTUALIZADOS CORRECTAMENTE";
                    } else {
                        result = "NO SE HAN ACTUALIZADO LOS DATOS";
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }

}
