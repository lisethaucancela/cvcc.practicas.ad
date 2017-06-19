/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.empresa;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.funciones.convenio.ConvenioAD;
import cvcc.practicas.ad.sw.WSVinculacion.Convenio;
import cvcc.practicas.ad.sw.swVinculacion;
import cvcc.practicas.entidades.CDpa;
import cvcc.practicas.entidades.CEmpresa;
import cvcc.practicas.entidades.CFuncionario;
import cvcc.practicas.entidades.CSectorEconomico;
import cvcc.practicas.entidades.CTipoEmpresa;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Liseth
 */
public class EmpresaAD extends CEmpresa {

    public EmpresaAD() {
    }

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

    public CEmpresa loadEmpresaPorPractica(AccesoDatos accesoDatos, int idPractica) throws Exception {
        int idConvenio;
        CEmpresa objEmpresa = new CEmpresa();
        try {
            //buscar por codigo de practica
            ConvenioAD c = new ConvenioAD();
            idConvenio = c.idConvenioPorPractica(accesoDatos, idPractica);
            //carga la lista de funcioanrios
            if (idConvenio != -1) {
                //si es -1 significa que no hay un convenio asignado 

                objEmpresa = loadEmpresaPorConvenio(accesoDatos, idConvenio);
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return objEmpresa;
    }

    public CEmpresa loadEmpresaPorConvenio(AccesoDatos accesoDatos, int idConvenio) throws Exception {
        //Listar los  empresas dependiendo de la empresa a 
        //la que pertenece mediante el  codigo de prácticas
        CEmpresa objEmpresa = new CEmpresa();
        try {
            //buscar por codigo de practica
            swVinculacion obj = new swVinculacion();
            Convenio datosConvenio = obj.loadEmpresasPorConvenio(idConvenio);
            if (datosConvenio.getObjEmpresa() != null) {

                objEmpresa.setIdEmpresa(datosConvenio.getObjEmpresa().getIdEmpresa());
                objEmpresa.setRuc(datosConvenio.getObjEmpresa().getRuc());
                CTipoEmpresa objTE = new CTipoEmpresa();
                objTE.setDescripcion(datosConvenio.getObjEmpresa().getObjTipoEmpresa().getDescripcion());
                objEmpresa.setObjTipoEmpresa(objTE);
                CSectorEconomico objSE = new CSectorEconomico();
                objSE.setDescripcion(datosConvenio.getObjEmpresa().getObjSectorEconomico().getDescripcion());
                objEmpresa.setObjSectorEconomico(objSE);
                CDpa objDPA = new CDpa();
                objDPA.setDescripcion(datosConvenio.getObjEmpresa().getObjDpa().getDescripcion());
                objEmpresa.setObjDpa(objDPA);
                objEmpresa.setRazonSocial(datosConvenio.getObjEmpresa().getRazonSocial());
                objEmpresa.setDireccion(datosConvenio.getObjEmpresa().getDireccion());
                objEmpresa.setTelefono(datosConvenio.getObjEmpresa().getTelefono());
                objEmpresa.setDireccionWeb(datosConvenio.getObjEmpresa().getDireccionWeb());
                objEmpresa.setEmail(datosConvenio.getObjEmpresa().getEmail());
                objEmpresa.setActividades(datosConvenio.getObjEmpresa().getActividades());

            }

        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return objEmpresa;
    }

}
