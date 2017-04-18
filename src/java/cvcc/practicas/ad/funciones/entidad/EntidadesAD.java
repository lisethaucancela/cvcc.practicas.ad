/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.entidad;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CEntidad;
import cvcc.practicas.entidades.CEntidades;
import cvcc.practicas.entidades.CTipoEntidad;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Liseth
 */
public class EntidadesAD extends CEntidades {

    public EntidadesAD() {
    }

    public void guardarEntidades(AccesoDatos accesoDatos) {
        for (CEntidad entidades : this.getEntidades()) {
            EntidadAD entidadAD = (EntidadAD) entidades;
            entidadAD.guardarEntidad(accesoDatos);
        }
    }

    public void obtenerListaEntidades(AccesoDatos accesoDatos) throws Exception {

        String sql = "SELECT entidad.id_entidad, tipo_entidad.id_tipo_entidad, tipo_entidad.descripcion as des, id_padre, entidad.descripcion, entidad.codigo_entidad\n"
                + "  FROM practicas.entidad, practicas.tipo_entidad \n"
                + "  WHERE tipo_entidad.id_tipo_entidad = entidad.id_tipo_entidad\n"
                + "  ORDER BY id_entidad;";
        try {
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarSQL(sql) == 1) {
                    ResultSet rsDatos = accesoDatos.getRs();
                    while (rsDatos.next()) {
                        CEntidad objEntidad = new CEntidad();
                        objEntidad.setIdEntidad(rsDatos.getInt("id_entidad"));

                        CTipoEntidad objTipoEntidad = new CTipoEntidad(rsDatos.getInt("id_tipo_entidad"), rsDatos.getString("des"));
                        objEntidad.setObjTipoEntidad(objTipoEntidad);

                        objEntidad.setDescripcion(rsDatos.getString("descripcion"));

                        this.addEntidades(objEntidad);
                    }
                }
            }
        } catch (SQLException exConec) {
            System.err.println("e: " + exConec.getMessage());
            throw new Exception(exConec.getMessage());
        }
    }
}
