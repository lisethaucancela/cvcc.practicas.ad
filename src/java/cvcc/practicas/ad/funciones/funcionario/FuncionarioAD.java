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
                this.setNombres(funcionario.getNombres());
                this.setCargo(funcionario.getCargo());
                this.setTelefono(funcionario.getTelefono());
                this.setObjEmpresa(funcionario.getObjEmpresa());
                this.setEstado(funcionario.getEstado());
            }
        } catch (Exception e) {
            //
        }
        
    }
    
    public CFuncionario loadDatosUnFuncionario(AccesoDatos accesoDatos, int codigoPractica) {
        CFuncionario result = new CFuncionario();
        try {
            String strSQL = "SELECT funcionario.id_funcionario,\n"
                    + "funcionario.cargo,\n"
                    + "funcionario.nombres,\n"
                    + "funcionario.telefono,\n"
                    + "funcionario.id_empresa\n"
                    + "FROM \n"
                    + "	practicas.practica_convenio, practicas.funcionario\n"
                    + "WHERE \n"
                    + "	  practica_convenio.id_funcionario = funcionario.id_funcionario AND practica_convenio.id_practica = '" + codigoPractica + "'";
            
            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslDatosFuncionario = accesoDatos.getRs();
                while (rslDatosFuncionario.next()) {
                    result.setIdFuncionario(rslDatosFuncionario.getInt(1));
                    result.setCargo(rslDatosFuncionario.getString(2));
                    result.setNombres(rslDatosFuncionario.getString(3));
                    result.setTelefono(rslDatosFuncionario.getString(4));
                    CEmpresa empresa = new CEmpresa();
                    empresa.setIdEmpresa(rslDatosFuncionario.getInt(5));
                    result.setObjEmpresa(empresa);
                }
            }
            
        } catch (Exception e) {
            //logger
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }
    
    public boolean insertarFuncionario(AccesoDatos accesoDatos) throws Exception {
        boolean respuesta = false;
        long idFuncionario = 0;
        int idEmpresa = 0;
        String nombres = "";
        String telefono = "";
        String cargo = "";
        boolean estado = false;
        try {
            
            idFuncionario = this.getIdFuncionario();
            idEmpresa = this.getObjEmpresa().getIdEmpresa();
            nombres = this.getNombres();
            telefono = this.getTelefono();
            cargo = this.getCargo();
            estado = this.getEstado();
            if (idFuncionario < 0) {
                String strSQL = "insert into practicas.funcionario(id_empresa,nombres,telefono,cargo,estado)\n"
                        + "values('" + idEmpresa + "','" + nombres + "','" + telefono + "','" + cargo + "','" + estado + "')";
                
                accesoDatos.EjecutarSQL(strSQL);
                respuesta = true;
                
            } else {
                //update con los datos ingresados
                String strSQL = "UPDATE practicas.funcionario\n"
                        + "   SET id_funcionario='" + idFuncionario + "', id_empresa='" + idEmpresa + "', nombres='" + nombres + "', telefono='" + telefono + "', cargo='" + cargo + "',estado='" + estado + "' \n"
                        + "WHERE (id_funcionario='" + idFuncionario + "' AND id_empresa='" + idEmpresa + "')";
                accesoDatos.EjecutarSQL(strSQL);
                respuesta = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }
//eliminar

    public boolean eliminarFuncionarios(AccesoDatos accesoDatos, CFuncionarios LFInterfaz, CFuncionarios LFBD) throws Exception {
        boolean respuesta = false;
        long IidFuncionario = 0;
        int IidEmpresa = 0;
        long BDidFuncionario = 0;
        int BDidEmpresa = 0;
        
        try {
            for (int i = 0; i < LFBD.getFuncionario().size(); i++) {
                BDidFuncionario = LFBD.getFuncionario().get(i).getIdFuncionario();
                BDidEmpresa = LFBD.getFuncionario().get(i).getObjEmpresa().getIdEmpresa();
                boolean v = false;
                
                for (int j = 0; j < LFInterfaz.getFuncionario().size(); j++) {
                    IidFuncionario = LFInterfaz.getFuncionario().get(i).getIdFuncionario();
                    IidEmpresa = LFInterfaz.getFuncionario().get(i).getObjEmpresa().getIdEmpresa();
                    
                    if (BDidFuncionario == IidFuncionario && BDidEmpresa == IidEmpresa) {
                        v = true;
                        
                    }
                }
                if (!v) {
                    String strSQL = "DELETE FROM practicas.funcionario WHERE (id_funcionario='" + BDidFuncionario + "' AND id_empresa='" + BDidEmpresa + "')";
                    
                    accesoDatos.EjecutarSQL(strSQL);
                    respuesta = true;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return respuesta;
    }
}
