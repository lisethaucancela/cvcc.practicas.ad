/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.objetivo;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CObjetivo;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Liseth
 */
public class ObjetivoAD extends CObjetivo {

    public ObjetivoAD(CObjetivo objetivo) {
        this.setIdObjetivo(objetivo.getIdObjetivo());
        this.setIdPractica(objetivo.getIdPractica());
        this.setDescripcion(objetivo.getDescripcion());
    }

    public void guardarObjetivo(AccesoDatos accesoDatos) {
        try {
            if (this.getIdObjetivo() < 0) {
                this.insertarObjetivo(accesoDatos);
            } else {
                this.modificarObjetivo(accesoDatos);
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public boolean insertarObjetivo(AccesoDatos accesoDatos) {
        boolean result = false;
        String strSQL;
        try {
            strSQL = "INSERT INTO practicas.objetivo(id_practica, descripcion)\n"
                    + "	VALUES ('" + this.getIdPractica() + "', '" + this.getDescripcion() + "')";

            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
                    ResultSet rslDatos = accesoDatos.getRs();
                    if (rslDatos.next()) {
                        result = rslDatos.getBoolean(1);
                    }
                }
            }
        } catch (SQLException exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }

    public boolean modificarObjetivo(AccesoDatos accesoDatos) {
        boolean result = false;
        String strSQL;
        try {
            strSQL = "UPDATE practicas.objetivo\n"
                    + "   SET id_practica='" + this.getIdPractica() + " ', descripcion='" + this.getDescripcion() + "'\n"
                    + " WHERE id_objetivo = '" + this.getIdObjetivo() + "';";

            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
                    ResultSet rslDatos = accesoDatos.getRs();
                    if (rslDatos.next()) {
                        result = rslDatos.getBoolean(1);
                    }
                }
            }
        } catch (Exception exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }

}
