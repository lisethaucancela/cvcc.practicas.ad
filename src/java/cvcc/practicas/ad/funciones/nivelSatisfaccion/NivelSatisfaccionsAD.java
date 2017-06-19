/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.nivelSatisfaccion;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CNivelSatisfaccion;
import cvcc.practicas.entidades.CNivelSatisfaccions;
import java.sql.ResultSet;

/**
 *
 * @author Paola_Cajilema
 */
public class NivelSatisfaccionsAD extends CNivelSatisfaccions {

    public NivelSatisfaccionsAD() {
    }

    public NivelSatisfaccionsAD(CNivelSatisfaccions nivelSatisfaccions) {
        for (CNivelSatisfaccion nivelSatisfaccion : nivelSatisfaccions.getNivelesSatisfaccion()) {
            NivelSatisfaccionAD nivelSatisfaccionAD = new NivelSatisfaccionAD(nivelSatisfaccion);
            this.addNivelSatisfaccion(nivelSatisfaccionAD);
        }
    }

    public void loadNivelesSatisfaccion(AccesoDatos accesoDatos) {
        String sql = "SELECT id_parametro_cualitativo_valor, valor, descripcion\n"
           + "  FROM practicas.nivel_satisfaccion\n"
           + "   order by id_parametro_cualitativo_valor;  ";
        try {
            if (accesoDatos.EjecutarSQL(sql) == 1) {
                ResultSet rsDatos = accesoDatos.getRs();
                while (rsDatos.next()) {
                    CNivelSatisfaccion objNivelSatisfaccion = new CNivelSatisfaccion();
                    objNivelSatisfaccion.setId(rsDatos.getInt(1));
                    objNivelSatisfaccion.setValor(rsDatos.getInt(2));
                    objNivelSatisfaccion.setDescripcion(rsDatos.getString(3));
                    this.addNivelSatisfaccion(objNivelSatisfaccion);
                }
            }
        } catch (Exception exConec) {
            System.err.println("error: " + exConec.getMessage());

        }

    }

}
