/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.funcionario;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.funciones.convenio.ConvenioAD;
import cvcc.practicas.ad.sw.WSVinculacion.Convenio;

import cvcc.practicas.ad.sw.swVinculacion;
import cvcc.practicas.entidades.CFuncionario;
import cvcc.practicas.entidades.CFuncionarios;

/**
 *
 * @author Paola_Cajilema
 */
public class FuncionariosAD extends CFuncionarios {

    public FuncionariosAD() {
    }

    public FuncionariosAD(CFuncionarios funcionarios) {
        for (CFuncionario funcionario : funcionarios.getFuncionario()) {
            FuncionarioAD funcionarioAD = new FuncionarioAD(funcionario);
            this.addFuncionario(funcionarioAD);
        }
    }

    public void loadFuncionariosPorPractica(AccesoDatos accesoDatos, int CodigoPractica) throws Exception {
        //Listar los  Funcionarios de una empresa dependiendo de la empresa a 
        //la que pertenece mediante el  codigo de prácticas
        int idConvenio;
        try {
            //buscar por codigo de practica
            ConvenioAD c = new ConvenioAD();
            idConvenio = c.idConvenioPorPractica(accesoDatos, CodigoPractica);
            //carga la lista de funcioanrios
            if (idConvenio != -1) {
                //si es -1 significa que no hay un convenio asignado 
                loadFuncionariosPorConvenio(accesoDatos, idConvenio);
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
    }

    public void loadFuncionariosPorConvenio(AccesoDatos accesoDatos, int idConvenio) throws Exception {
        //Listar los  Funcionarios de una empresa dependiendo de la empresa a 
        //la que pertenece mediante el  codigo de prácticas

        try {
            //buscar por codigo de practica
            swVinculacion obj = new swVinculacion();
            Convenio datosConvenio = obj.loadFuncionariosPorConvenio(idConvenio);
            for (int i = 0; i < datosConvenio.getObjEmpresa().getListFuncionario().size(); i++) {
                CFuncionario cf = new CFuncionario();
                cf.setIdFuncionario(datosConvenio.getObjEmpresa().getListFuncionario().get(i).getIdFuncionario());
                cf.setCedula(datosConvenio.getObjEmpresa().getListFuncionario().get(i).getCedula());
                cf.setNombres(datosConvenio.getObjEmpresa().getListFuncionario().get(i).getNombres());
                cf.setApellidos(datosConvenio.getObjEmpresa().getListFuncionario().get(i).getApellidos());
                cf.setTelefono(datosConvenio.getObjEmpresa().getListFuncionario().get(i).getTelefono());
                cf.setEmail(datosConvenio.getObjEmpresa().getListFuncionario().get(i).getEmail());
                cf.setCargo(datosConvenio.getObjEmpresa().getListFuncionario().get(i).getCargo());
                cf.setEstado(datosConvenio.getObjEmpresa().getListFuncionario().get(i).isEstado());
                this.addFuncionario(cf);
            }

        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
    }
}
