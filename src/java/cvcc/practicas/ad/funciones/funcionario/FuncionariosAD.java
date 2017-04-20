/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.funcionario;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CEmpresa;
import cvcc.practicas.entidades.CFuncionario;
import cvcc.practicas.entidades.CFuncionarios;
//import cvcc.practicas.entidades.CFuncionario;
import java.sql.ResultSet;
import java.sql.SQLException;


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

    public void ListarFuncionariosPorPractica(AccesoDatos accesoDatos, int CodigoPractica) throws Exception {
        //Listar los  Funcionarios de una empresa dependiendo de la empresa a 
        //la que pertenece mediante el  codigo de prácticas
        int codigoempresa;
        try {
            //buscar por codigo de practica
            codigoempresa = codigoEmpresaPorPractica(accesoDatos, CodigoPractica);
            //carga la lista de funcioanrios
            if (codigoempresa != 0) {
                loadListaFuncionariosPorEmpresa(accesoDatos, codigoempresa);
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
    }

    public int codigoEmpresaPorPractica(AccesoDatos accesoDatos, int CodigoPractica) throws SQLException {
        // return a enterprise's code by practice'code 
        int codigoempresa = 0;
        String strSQL = " SELECT convenio.id_empresa FROM\n"
                + "practicas.practica, \n"
                + "practicas.practica_convenio, \n"
                + "practicas.convenio\n"
                + "WHERE \n"
                + "practica.id_practica = practica_convenio.id_practica AND\n"
                + "practica_convenio.id_convenio = convenio.id_convenio AND practica.id_practica='" + CodigoPractica + "'";

        if (accesoDatos.EjecutarSQL(strSQL) == 1) {
            ResultSet rslCodigoEmpresa = accesoDatos.getRs();
            while (rslCodigoEmpresa.next()) {
                codigoempresa = rslCodigoEmpresa.getInt(1);
            }
        }
        return codigoempresa;
    }

    public void loadListaFuncionariosPorEmpresa(AccesoDatos accesoDatos, int codigoempresa) throws SQLException {
        // generate a list with functionary by enterprise's code
        String strSQL2 = " SELECT funcionario.id_funcionario,\n"
                + "funcionario.id_empresa,\n"
                + "funcionario.nombres,\n"
                + "funcionario.telefono,\n"
                + "funcionario.cargo,\n"
                + "funcionario.estado\n"
                + "FROM \n"
                + "practicas.funcionario\n"
                + "WHERE \n"
                + "funcionario.id_empresa ='" + codigoempresa + "'";
        if (accesoDatos.EjecutarSQL(strSQL2) == 1) {
            ResultSet rslDatosFuncionarios = accesoDatos.getRs();
            while (rslDatosFuncionarios.next()) {
                CFuncionario objetoFuncionario = new CFuncionario();
                objetoFuncionario.setIdFuncionario(rslDatosFuncionarios.getInt(1));
                CEmpresa objetoEmpresa = new CEmpresa();
                objetoEmpresa.setIdEmpresa(rslDatosFuncionarios.getInt(2));
                objetoFuncionario.setObjEmpresa(objetoEmpresa);
                objetoFuncionario.setNombres(rslDatosFuncionarios.getString(3));
                objetoFuncionario.setTelefono(rslDatosFuncionarios.getString(4));
                objetoFuncionario.setCargo(rslDatosFuncionarios.getString(5));
                objetoFuncionario.setEstado(rslDatosFuncionarios.getBoolean(6));
                getFuncionario().add(objetoFuncionario);
            }
        }
    }

    boolean guardarFuncionario(AccesoDatos accesoDatos) throws Exception {
        //Save 
        boolean respuesta = false;
        try {
            for (int i = 0; i < this.getFuncionario().size(); i++) {
                FuncionarioAD f = new FuncionarioAD(this.getFuncionario().get(i));
                f.guardarFuncionario(accesoDatos);
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }
}
