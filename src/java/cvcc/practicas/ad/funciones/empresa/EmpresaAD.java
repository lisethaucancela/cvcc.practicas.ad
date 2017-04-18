/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.empresa;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CEmpresa;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Liseth
 */
public class EmpresaAD extends CEmpresa {

    EmpresaAD(CEmpresa empresa) {
        this.setIdEmpresa(empresa.getIdEmpresa());
        this.setActividades(empresa.getActividades());
        this.setDireccion(empresa.getDireccion());
        this.setDireccionWeb(empresa.getDireccionWeb());
        this.setEmail(empresa.getEmail());
        this.setObjDpa(empresa.getObjDpa());
        this.setObjSectorEconomico(empresa.getObjSectorEconomico());
        this.setObjTipoEmpresa(empresa.getObjTipoEmpresa());
        this.setRazonSocial(empresa.getRazonSocial());
        this.setRuc(empresa.getRuc());
        this.setTelefono(empresa.getTelefono());
    }

    public void guardarEmpresa(AccesoDatos accesoDatos) {
        try {
            if (this.getIdEmpresa() < 0) {
                this.insertarEmpresa(accesoDatos);
            } else {
                this.modificarEmpresa(accesoDatos);
            }

        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public int insertarEmpresa(AccesoDatos accesoDatos) {
        int result = 0;
        String strSQL;
        try {
            strSQL = " ";
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

    public boolean modificarEmpresa(AccesoDatos accesoDatos) {
        boolean result = false;
        String strSQL;
        try {
            strSQL = "";
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
