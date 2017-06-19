/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.convenio;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.sw.WSVinculacion.Convenio;
import cvcc.practicas.entidades.CConvenio;
import cvcc.practicas.entidades.CDpa;
import cvcc.practicas.entidades.CEmpresa;
import cvcc.practicas.entidades.CSectorEconomico;
import cvcc.practicas.entidades.CTipoEmpresa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Liseth
 */
public class ConvenioAD extends CConvenio {

    public ConvenioAD() {
    }

    public ConvenioAD(CConvenio convenio) {
        this.setDescripcion(convenio.getDescripcion());
        this.setEstado(convenio.isEstado());
        this.setFechaFin(convenio.getFechaFin());
        this.setFechaInicio(convenio.getFechaInicio());
        this.setIdConvenio(convenio.getIdConvenio());
        this.setObjEmpresa(convenio.getObjEmpresa());
        this.setObjEntidad(convenio.getObjEntidad());
    }

    public ConvenioAD(Convenio convenio) {
        try {
            this.setDescripcion(convenio.getDescripcion());
            this.setEstado(convenio.isEstado());
            this.setIdConvenio(convenio.getIdConvenio());
            this.setEstado(convenio.isEstado());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formatter.parse(convenio.getFechaInicio());
            java.sql.Date Datesql = new java.sql.Date(fecha.getTime());

            this.setFechaInicio(Datesql);
            fecha = formatter.parse(convenio.getFechaFin());
            Datesql = new java.sql.Date(fecha.getTime());
            this.setFechaFin(Datesql);

            CEmpresa empresa = new CEmpresa();
            empresa.setActividades(convenio.getObjEmpresa().getActividades());
            empresa.setDireccion(convenio.getObjEmpresa().getDireccion());
            empresa.setDireccionWeb(convenio.getObjEmpresa().getDireccionWeb());
            empresa.setEmail(convenio.getObjEmpresa().getEmail());
            empresa.setIdEmpresa(convenio.getObjEmpresa().getIdEmpresa());
            empresa.setRazonSocial(convenio.getObjEmpresa().getRazonSocial());
            empresa.setRuc(convenio.getObjEmpresa().getRuc());
            empresa.setTelefono(convenio.getObjEmpresa().getTelefono());

            CTipoEmpresa objTipoEmpresa = new CTipoEmpresa();
            objTipoEmpresa.setDescripcion(convenio.getObjEmpresa().getObjTipoEmpresa().getDescripcion());
            empresa.setObjTipoEmpresa(objTipoEmpresa);

            CSectorEconomico objSectorEconomico = new CSectorEconomico();
            objSectorEconomico.setDescripcion(convenio.getObjEmpresa().getObjSectorEconomico().getDescripcion());
            empresa.setObjSectorEconomico(objSectorEconomico);

            CDpa objDpa = new CDpa();
            objDpa.setDescripcion(convenio.getObjEmpresa().getObjDpa().getDescripcion());
            empresa.setObjDpa(objDpa);

            this.setObjEmpresa(empresa);
        } catch (Exception e) {
            System.out.print("e: " + e.getMessage());
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

}
