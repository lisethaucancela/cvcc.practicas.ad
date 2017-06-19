/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.parametroCualitativo;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CParametroCualitativo;
import cvcc.practicas.entidades.CParametroCualitativos;
import java.sql.ResultSet;

/**
 *
 * @author Paola_Cajilema
 */
public class ParametroCualitativosAD extends CParametroCualitativos {

    public ParametroCualitativosAD() {
    }

    public ParametroCualitativosAD(CParametroCualitativos parametroCualitativos) {
        for (CParametroCualitativo parametroCualitativo : parametroCualitativos.getParametrosCualitativos()) {
            ParametroCualitativoAD parametroCualitativoAD = new ParametroCualitativoAD(parametroCualitativo);
            this.addParametrosCualitativos(parametroCualitativoAD);
        }
    }

    public void loadParametrosCualitativos(AccesoDatos accesoDatos) {
        String sql = "SELECT id_parametro_cualitativo, descripcion\n"
           + "  FROM practicas.parametro_cualitativo\n"
           + "  order by id_parametro_cualitativo; ";
        try {
            if (accesoDatos.EjecutarSQL(sql) == 1) {
                ResultSet rsDatos = accesoDatos.getRs();
                while (rsDatos.next()) {
                    CParametroCualitativo objParametroCualitativo = new CParametroCualitativo();
                    objParametroCualitativo.setId(rsDatos.getInt(1));
                    objParametroCualitativo.setDescripcion(rsDatos.getString(2));
                    this.addParametrosCualitativos(objParametroCualitativo);
                }
            }
        } catch (Exception exConec) {
            System.err.println("error: " + exConec.getMessage());

        }

    }

}
