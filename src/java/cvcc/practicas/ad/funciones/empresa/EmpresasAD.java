/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.empresa;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CDpa;
import cvcc.practicas.entidades.CEmpresa;
import cvcc.practicas.entidades.CEmpresas;
import cvcc.practicas.entidades.CPlanificacion;
import cvcc.practicas.entidades.CSectorEconomico;
import cvcc.practicas.entidades.CTipoEmpresa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Liseth
 */
public class EmpresasAD extends CEmpresas {

    EmpresasAD(CEmpresas listaEmpresas) {
        for (CEmpresa empresa : listaEmpresas.getEmpresas()) {
            EmpresaAD empresaAD = new EmpresaAD(empresa);
            this.addEmpresas(empresaAD);
        }
    }

    EmpresasAD() {
    }

    public void guardarEmpresas(AccesoDatos accesoDatos) {
        for (CEmpresa empresas : this.getEmpresas()) {
            EmpresaAD empresaAD = (EmpresaAD) empresas;
            empresaAD.guardarEmpresa(accesoDatos);
        }
    }

    public void obtenerListaEmpresas(AccesoDatos accesoDatos) throws Exception {
        CEmpresa objEmpresa = new CEmpresa();
        String sql = "SELECT 	empresa.id_empresa, empresa.ruc,\n"
                + "             empresa.id_tipo_empresa,  tipo_empresa.descripcion as tip_emp_descripcion, \n"
                + "             empresa.id_sector_economico, sector_economico.descripcion as sec_eco_descripcion,\n"
                + "             empresa.id_dpa,	dpa.descripcion as dpa_descripcion,\n"
                + "             empresa.razon_social, empresa.direccion,\n"
                + "             empresa.telefono, empresa.direccion_web,\n"
                + "             empresa.email, empresa.actividades\n"
                + "     FROM 	practicas.empresa, practicas.tipo_empresa,\n"
                + "             practicas.sector_economico, practicas.dpa\n"
                + "     WHERE	empresa.id_tipo_empresa = tipo_empresa.id_tipo_empresa AND\n"
                + "             empresa.id_sector_economico = sector_economico.id_sector_economico AND\n"
                + "             empresa.id_dpa = dpa.id_dpa;";
        try {
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarSQL(sql) == 1) {
                    ResultSet rsDatos = accesoDatos.getRs();
                    while (rsDatos.next()) {
                        objEmpresa.setIdEmpresa(rsDatos.getInt("id_empresa"));
                        objEmpresa.setRuc(rsDatos.getString("ruc"));

                        CTipoEmpresa objTipoEmpresa = new CTipoEmpresa(rsDatos.getInt("id_tipo_empresa"),
                                rsDatos.getString("tip_emp_descripcion"));
                        CSectorEconomico objSectorEconomico = new CSectorEconomico(rsDatos.getInt("id_sector_economico"),
                                rsDatos.getString("sec_eco_descripcion"));
                        CDpa objDpa = new CDpa(rsDatos.getInt("id_dpa"),
                                rsDatos.getString("dpa_descripcion"));

                        objEmpresa.setRazonSocial(rsDatos.getString("razon_social"));
                        objEmpresa.setDireccion(rsDatos.getString("direccion"));
                        objEmpresa.setTelefono(rsDatos.getString("telefono"));
                        objEmpresa.setDireccionWeb(rsDatos.getString("direccion_web"));
                        objEmpresa.setEmail(rsDatos.getString("email"));
                        objEmpresa.setActividades(rsDatos.getString("actividades"));

                        objEmpresa.setObjTipoEmpresa(objTipoEmpresa);
                        objEmpresa.setObjSectorEconomico(objSectorEconomico);
                        objEmpresa.setObjDpa(objDpa);

                        this.addEmpresas(objEmpresa);
                    }
                }
            }
        } catch (SQLException exConec) {
            System.err.println("e: " + exConec.getMessage());
            throw new Exception(exConec.getMessage());
        }
    }
}
