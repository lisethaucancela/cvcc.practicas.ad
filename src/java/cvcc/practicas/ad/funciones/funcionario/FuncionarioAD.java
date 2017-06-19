/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.funcionario;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CFuncionario;
import java.sql.ResultSet;

/**
 *
 * @author Paola_Cajilema
 */
public class FuncionarioAD extends CFuncionario {

    public FuncionarioAD() {
    }

    FuncionarioAD(CFuncionario funcionario) {
        try {
            if (funcionario != null) {
                this.setIdFuncionario(funcionario.getIdFuncionario());
                this.setCedula(funcionario.getCedula());
                this.setNombres(funcionario.getNombres());
                this.setApellidos(funcionario.getApellidos());
                this.setCargo(funcionario.getCargo());
                this.setTelefono(funcionario.getTelefono());
                this.setEmail(funcionario.getEmail());
                this.setEstado(funcionario.getEstado());
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }

    }

    public int idFuncionarioExistentePracticaConvenio(AccesoDatos accesoDatos, int idPractica) {
        //id del funcionario existente dado una idpractica
        int result = -1;

        try {
            String strSQL = " SELECT id_funcionario\n"
               + "  FROM practicas.practica_convenio where practica_convenio.id_practica='" + idPractica + "';";
            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                accesoDatos.BeginTran();
                ResultSet rslDatosPracticas = accesoDatos.getRs();
                while (rslDatosPracticas.next()) {
                    result = rslDatosPracticas.getInt(1);
                    accesoDatos.CommitTran();
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
            accesoDatos.RollbackTran();
        }
        return result;
    }

    public boolean actualizarAsignarFuncionario(AccesoDatos accesoDatos, int idPractica, int idFuncionario) {
        boolean result = false;
        try {
            result = actualizarAsignarFuncionario1(accesoDatos, idPractica, idFuncionario);
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }

    public boolean actualizarAsignarFuncionario1(AccesoDatos accesoDatos, int idPractica, int idFuncionario) {
        boolean result = false;
        try {
            //fin comprobar que el proyecto no se encuentra registrado 
            String strSQL = "UPDATE practicas.practica_convenio\n"
               + "   SET  id_funcionario='" + idFuncionario + "'\n"
               + " WHERE practica_convenio.id_practica='" + idPractica + "' RETURNING  id_practica_convenio;";
            accesoDatos.BeginTran();
            if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
                result = true;
                accesoDatos.CommitTran();
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
            accesoDatos.RollbackTran();
        }
        return result;
    }

}
