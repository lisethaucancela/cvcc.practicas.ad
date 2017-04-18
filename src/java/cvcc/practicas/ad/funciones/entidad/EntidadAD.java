/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.entidad;

import cvcc.practicas.ad.funciones.entidad.*;
import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CEntidad;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Liseth
 */
public class EntidadAD extends CEntidad {

    EntidadAD(CEntidad entidad) {
        this.setIdEntidad(entidad.getIdEntidad());
        this.setDescripcion(entidad.getDescripcion());
        this.setObjPadre(entidad.getObjPadre());
        this.setObjTipoEntidad(getObjTipoEntidad());
    }

    public void guardarEntidad(AccesoDatos accesoDatos) {
        try {
            if (this.getIdEntidad() < 0) {
                this.insertarEntidad(accesoDatos);
            } else {
                this.modificarEntidad(accesoDatos);
            }

        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public int insertarEntidad(AccesoDatos accesoDatos) {
        int result = 0;
        String strSQL;
        try {
            strSQL = "--------------- ";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate_id(strSQL) == 1) {
                    ResultSet rslDatos = accesoDatos.getRs();
                    if (rslDatos.next()) {
                        result = rslDatos.getInt(1);
                    }
                }
            }
        } catch (SQLException exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }

    public boolean modificarEntidad(AccesoDatos accesoDatos) {
        boolean result = false;
        String strSQL;
        try {
            strSQL = "-----------------";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
                    /*  ResultSet rslDatos = accesoDatos.getRs();
                     if (rslDatos.next()) {
                     result = rslDatos.getBoolean(1);
                     }*/
                    result = true;
                }
            }
        } catch (Exception exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }
}
