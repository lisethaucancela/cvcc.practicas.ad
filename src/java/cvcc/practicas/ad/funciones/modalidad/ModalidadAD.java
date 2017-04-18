/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.modalidad;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CModalidad;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Liseth
 */
public class ModalidadAD extends CModalidad {

    ModalidadAD(CModalidad actividad) {
        this.setIdModalidad(actividad.getIdModalidad());
        this.setDescripcion(actividad.getDescripcion());
    }

    public void guardarModalidad(AccesoDatos accesoDatos) {
        try {
            if (this.getIdModalidad() < 0) {
                this.insertarModalidad(accesoDatos);
            } else {
                this.modificarModalidad(accesoDatos);
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public int insertarModalidad(AccesoDatos accesoDatos) {
        int result = 0;
        String strSQL;
        try {
            strSQL = " ";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
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

    public boolean modificarModalidad(AccesoDatos accesoDatos) {
        boolean result = false;
        String strSQL;
        try {
            strSQL = "";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
                    ResultSet rslDatos = accesoDatos.getRs();
                    if (rslDatos.next()) {
                        ///////////////////////////////////////////////////
                    }
                    result = true;
                }
            }
        } catch (Exception exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }

}
