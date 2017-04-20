package cvcc.practicas.ad.funciones.usuario;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CUsuario;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Paola_Cajilema
 */
public class UsuarioAD extends CUsuario {

    UsuarioAD(CUsuario usuario) {
        try {
            if (usuario != null) {
                this.setIdUsuario(usuario.getIdUsuario());
                this.setCedula(usuario.getCedula());
                this.setNombres(usuario.getNombres());
                this.setApellidos(usuario.getApellidos());
                this.setEmail(usuario.getEmail());
                this.setNumeroPractica(usuario.getNumeroPractica());
            }
        } catch (Exception e) {
            //
        }

    }

    UsuarioAD() {

    }

    public int NumeroPracticasACargo(AccesoDatos accesoDatos) throws Exception {
        //número de prácticas de cada docente en un periodo determinado 
        //en el cual se ha matriculado la tesis
        int num = 0;
        try {
            StringBuilder strSQL = new StringBuilder();
            strSQL.append("SELECT count(usuario_entidad_rol_practica.id_usuario_entidad_rol)");
            strSQL.append(" FROM ");
            strSQL.append("  practicas.usuario, ");
            strSQL.append("  practicas.usuario_entidad,");
            strSQL.append("  practicas.usuario_entidad_rol,");
            strSQL.append("  practicas.usuario_entidad_rol_practica,");
            strSQL.append("  practicas.practica,");
            strSQL.append("  practicas.estado_practica,");
            strSQL.append("  practicas.entidad ");
            strSQL.append("WHERE ");
            strSQL.append("  usuario.cedula = '").append(this.getCedula()).append("' AND ");
            strSQL.append("  usuario.id_usuario = usuario_entidad.id_usuario ");
            strSQL.append("  AND entidad.id_entidad = usuario_entidad.id_entidad ");
            strSQL.append("  AND usuario_entidad.id_usuario_entidad = usuario_entidad_rol.id_usuario_entidad");
            strSQL.append("  AND usuario_entidad_rol.id_usuario_entidad_rol = usuario_entidad_rol_practica.id_usuario_entidad_rol");
            strSQL.append("  AND usuario_entidad_rol_practica.id_practica = practica.id_practica ");
            strSQL.append("  AND practica.id_estado_practica = estado_practica.id_estado_practica ");
            strSQL.append("  AND estado_practica.id_estado_practica = '").append(2).append("';");

            if (accesoDatos.EjecutarSQL(strSQL.toString()) == 1) {
                ResultSet rslCodigoEmpresa = accesoDatos.getRs();
                while (rslCodigoEmpresa.next()) {
                    num = rslCodigoEmpresa.getInt(1);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return num;
    }

    public String codigoEntidad(AccesoDatos accesoDatos, String cedula) throws Exception {
        String result = "";

        try {
            StringBuilder strSQL = new StringBuilder();
            strSQL.append("SELECT \n" + "  entidad.codigo_entidad\n" + "FROM \n" + "  practicas.usuario, \n" + "  practicas.usuario_entidad, \n" + "  practicas.entidad\n" + "WHERE \n" + "  usuario.id_usuario = usuario_entidad.id_usuario AND\n" + "  entidad.id_entidad = usuario_entidad.id_entidad AND\n" + "  usuario.cedula='").append(cedula).append("';");

            if (accesoDatos.EjecutarSQL(strSQL.toString()) == 1) {
                ResultSet rslCodigoEmpresa = accesoDatos.getRs();
                while (rslCodigoEmpresa.next()) {
                    result = rslCodigoEmpresa.getString(1);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
