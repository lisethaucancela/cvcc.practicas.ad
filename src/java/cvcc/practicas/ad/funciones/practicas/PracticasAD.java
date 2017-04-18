/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.practicas;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.funciones.objetivo.ObjetivoAD;
import cvcc.practicas.ad.funciones.periodo.PeriodoAD;
import cvcc.practicas.ad.funciones.planificacion.PlanificacionAD;
import cvcc.practicas.entidades.CObjetivo;
import cvcc.practicas.entidades.CPeriodo;
import cvcc.practicas.entidades.CPlanificacion;
import cvcc.practicas.entidades.CPractica;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Paola_Cajilema
 */
public class PracticasAD extends CPractica {

    public PracticasAD() {
    }

    public PracticasAD(CPractica practicas) {
        try {
            if (practicas != null) {
                this.setIdPractica(practicas.getIdPractica());
                this.setObjModalidad(practicas.getObjModalidad());
                this.setObjEstadoPractica(practicas.getObjEstadoPractica());
                this.setObjPeriodo(practicas.getObjPeriodo());
                this.setDescripcion(practicas.getDescripcion());
                this.setObjUsuario(practicas.getObjUsuario());
                this.setObjFuncionario(practicas.getObjFuncionario());
                this.setObjEmpresa(practicas.getObjEmpresa());
                if (practicas.getListObjetivos() != null) {
                    for (CObjetivo objetivo : practicas.getListObjetivos()) {
                        ObjetivoAD ObjetivoAD = new ObjetivoAD(objetivo);
                        this.addObjetivos(ObjetivoAD);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("error:" + e.getMessage());
        }
    }

    public void guardarPractica(AccesoDatos accesoDatos, int user_ent_rol) {
        try {
            if (this.getIdPractica() < 0) {
                this.insertarPractica(accesoDatos, user_ent_rol);
            } else {
                this.modificarPractica(accesoDatos);
            }

        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    public boolean insertarPractica(AccesoDatos accesoDatos, int user_ent_rol) {
        int id_practica = 0, id_uerp = 0;
        boolean res = false;
        String strSQL1, strSQL2;
        ResultSet rslDatos;
        try {
            if (accesoDatos.Connectar() == 2) {
                strSQL1 = "INSERT INTO practicas.practica(id_modalidad, id_estado_practica, descripcion)\n"
                        + "		VALUES ('" + this.getObjModalidad().getIdModalidad() + "', '" + 1 + "', '" + this.getDescripcion() + "') \n"
                        + "		RETURNING  id_practica;";
                if (accesoDatos.EjecutarSQL(strSQL1) == 1) {
                    rslDatos = accesoDatos.getRs();
                    if (rslDatos.next()) {
                        id_practica = rslDatos.getInt(1);
                    }
                }
                if (id_practica > 0) {
                    strSQL2 = "INSERT INTO practicas.usuario_entidad_rol_practica(id_usuario_entidad_rol, id_practica)\n"
                            + "		VALUES ('" + user_ent_rol + "', '" + id_practica + "') RETURNING  id_usuario_entidad_rol_practica";
                    if (accesoDatos.EjecutarSQL(strSQL2) == 1) {
                        rslDatos = accesoDatos.getRs();
                        if (rslDatos.next()) {
                            id_uerp = rslDatos.getInt(1);
                        }
                    }
                }
                if (id_uerp > 0) {
                    res = true;
                }
            }
        } catch (SQLException exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return res;
    }

    public boolean modificarPractica(AccesoDatos accesoDatos) {
        boolean result = false;
        String strSQL;
        try {
            strSQL = "UPDATE practicas.practica\n"
                    + "   SET id_modalidad='" + this.getObjModalidad().getIdModalidad() + "', \n"
                    + "id_estado_practica='" + this.getObjEstadoPractica().getIdEstadoPractica() + "', \n"
                    + "descripcion='" + this.getDescripcion() + "'\n"
                    + " WHERE id_practica='" + this.getIdPractica() + "';";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarUpdate(strSQL) == 1) {
                    ResultSet rslDatos = accesoDatos.getRs();
                    if (rslDatos.next()) {
                        int i = rslDatos.getInt(1);
                        if (i > 0) {
                            result = true;
                        }
                    }
                }
            }
        } catch (Exception exConec) {
            System.err.println("Error: " + exConec.getMessage());
        }
        return result;
    }

    /* *************************************************************************
     * OBJETIVOS   
     * ************************************************************************/
    public void loadObjetivos(AccesoDatos accesoDatos) {
        try {
            String strSQL = "SELECT id_objetivo, id_practica, descripcion FROM practicas.objetivo WHERE id_practica='" + this.getIdPractica() + "';";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                    ResultSet rslObjetivos = accesoDatos.getRs();
                    while (rslObjetivos.next()) {
                        CObjetivo objetivo = new CObjetivo();
                        objetivo.setIdObjetivo(rslObjetivos.getInt("id_objetivo"));
                        objetivo.setIdPractica(this.getIdPractica());
                        objetivo.setDescripcion(rslObjetivos.getString("descripcion"));
                        this.addObjetivos(objetivo);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public void guardarObjetivos(AccesoDatos accesoDatos) {
        try {
            for (CObjetivo objetivos : this.getListObjetivos()) {
                ObjetivoAD objetivoAD = new ObjetivoAD(objetivos);
                objetivoAD.guardarObjetivo(accesoDatos);
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /* *************************************************************************
     * PLANIFICACIONES   
     * ************************************************************************/
    public void loadPlanificaciones(AccesoDatos accesoDatos) {
        try {
            String strSQL = "SELECT id_planificacion, id_practica, fecha_ini, fecha_fin, horas_planificadas\n"
                    + "  FROM practicas.planificacion WHERE id_practica='" + this.getIdPractica() + "';";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                    ResultSet rslObjetivos = accesoDatos.getRs();
                    while (rslObjetivos.next()) {
                        CPlanificacion objPlanificacion = new CPlanificacion();
                        objPlanificacion.setIdPlanificacion(rslObjetivos.getInt("id_planificacion"));
                        objPlanificacion.setFechaInicio(rslObjetivos.getDate("fecha_ini"));
                        objPlanificacion.setFechaFin(rslObjetivos.getDate("fecha_fin"));
                        this.addPlanificaciones(objPlanificacion);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public void guardarPlanificaciones(AccesoDatos accesoDatos) {
        try {
            for (CPlanificacion planificaciones : this.getListPlanificaciones()) {
                PlanificacionAD planificacionAD = new PlanificacionAD(planificaciones);
                planificacionAD.guardarPlanificacion(accesoDatos);
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /* *************************************************************************
     * LISTA PERIODO
     * ************************************************************************/
    public void loadPeriodos(AccesoDatos accesoDatos) {
        try {
            String strSQL = "SELECT 	practica_periodo.id_practica_periodo, practica_periodo.id_periodo, practica_periodo.fecha, periodo.codigo\n"
                    + "     FROM   	practicas.practica_periodo, practicas.periodo\n"
                    + "     WHERE 	practica_periodo.id_practica = '" + this.getIdPractica() + "' AND\n"
                    + "                 practica_periodo.id_periodo = periodo.id_periodo;\n";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                    ResultSet rslDatos = accesoDatos.getRs();
                    while (rslDatos.next()) {
                        PeriodoAD objPeriodoAD = new PeriodoAD();
                        
                        objPeriodoAD.setFecha(rslDatos.getDate("fecha"));
                        objPeriodoAD.setCodigo(rslDatos.getString("codigo"));

                        objPeriodoAD.SWDatosPeriodo();                          //LLenar datos periodo SW

                        this.addPeriodos(objPeriodoAD);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /* *************************************************************************
     * TUTORES    
     * ************************************************************************/
    public String insertarTutor(AccesoDatos accesoDatos, String cedulaTutor, String idPractica, int entidad) throws Exception {
        //muestra lista de practicas mediante el codigoEntidad de la secretaria de la escuela 
        String res = "";
        try {
            //anular el tutor anterior
            deshabilitarTutor(accesoDatos, Integer.parseInt(idPractica));
            //fin anular el tutor anterior
            int id_usuario_entidad_rol = id_usuario_entidad_rol(accesoDatos, cedulaTutor);//
            if (id_usuario_entidad_rol == 0) {
                //insertar usuario
                int cod = insertarDocente(accesoDatos, cedulaTutor, entidad);
                if (cod != 0) {
                    id_usuario_entidad_rol = cod;
                }
            }
            //verificar si el id_usuario_entidad_rol con el idPractica ya están registrado 
            //si existe ya no 
            if (!verificarTutor(accesoDatos, id_usuario_entidad_rol, Integer.parseInt(idPractica))) {
                String strSQL = " INSERT INTO practicas.usuario_entidad_rol_practica(id_usuario_entidad_rol, id_practica,estado)"
                        + " VALUES ('" + id_usuario_entidad_rol + "','" + Integer.parseInt(idPractica) + "',TRUE)returning id_usuario_entidad_rol_practica \n";

                if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                    ResultSet rslCodigoEmpresa = accesoDatos.getRs();
                    while (rslCodigoEmpresa.next()) {
                        int v = rslCodigoEmpresa.getInt(1);
                        if (v != 0) {
                            res = "EL TUTOR FUE ASIGNADO EXITOSAMENTE";
                        } else {
                            res = "EL TUTOR NO FUE ASIGNADO";
                        }
                    }
                }
            } else {
                System.err.println("YA SE ECUENTRA ASIGNADO EL TUTOR PARA LA PRACTICA INDICADA");
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return res;
    }

    public boolean deshabilitarTutor(AccesoDatos accesoDatos, int idPractica) throws SQLException {
        boolean res = false;
        int idUsuarioEntidadRolPractica = idUsuarioEntidadRolPractica(accesoDatos, idPractica);
        if (deshabilitarIdUsuarioEntidadRolPractica(accesoDatos, idUsuarioEntidadRolPractica)) {
            res = true;
        }
        return res;
    }

    public int idUsuarioEntidadRolPractica(AccesoDatos accesoDatos, int idPractica) throws SQLException {
        int idUsuarioEntidadRolPractica = -1;
        String strSQL = "SELECT \n"
                + "  usuario_entidad_rol_practica.id_usuario_entidad_rol_practica \n"
                + "FROM \n"
                + "  practicas.usuario_entidad_rol_practica, \n"
                + "  practicas.usuario_entidad_rol\n"
                + "WHERE \n"
                + "  usuario_entidad_rol_practica.id_practica='" + idPractica + "' AND\n"
                + "  usuario_entidad_rol.id_usuario_entidad_rol = usuario_entidad_rol_practica.id_usuario_entidad_rol AND\n"
                + "  usuario_entidad_rol.id_rol!='" + 1 + "';";

        if (accesoDatos.EjecutarSQL(strSQL) == 1) {
            ResultSet rslCodigoEmpresa = accesoDatos.getRs();
            while (rslCodigoEmpresa.next()) {
                idUsuarioEntidadRolPractica = rslCodigoEmpresa.getInt(1);
            }
        }
        return idUsuarioEntidadRolPractica;

    }

    public boolean deshabilitarIdUsuarioEntidadRolPractica(AccesoDatos accesoDatos, int idUsuarioEntidadRolPractica) throws SQLException {
        boolean res = false;
        String strSQL = "UPDATE practicas.usuario_entidad_rol_practica\n"
                + "             SET estado=FALSE\n"
                + "     WHERE usuario_entidad_rol_practica.id_usuario_entidad_rol_practica='" + idUsuarioEntidadRolPractica + "';";
        if (accesoDatos.EjecutarSQL(strSQL) == 1) {
            res = true;
        }
        return res;
    }

    public boolean verificarTutor(AccesoDatos accesoDatos, int idUsuarioEntidadRol, int idPractica) throws SQLException {
        boolean res = false;
        String strSQL = " SELECT id_usuario_entidad_rol_practica "
                + "FROM practicas.usuario_entidad_rol_practica "
                + " where id_usuario_entidad_rol = '" + idUsuarioEntidadRol + "' AND id_practica = '" + idPractica + "';";
        if (accesoDatos.EjecutarSQL(strSQL) == 1) {
            ResultSet rslCodigoEmpresa = accesoDatos.getRs();
            while (rslCodigoEmpresa.next()) {
                String val = String.valueOf(rslCodigoEmpresa.getInt(1));
                if (!val.equals("")) {
                    res = true;//el codiggo existe;
                }
            }
        }
        return res;
    }

    public int id_usuario_entidad_rol(AccesoDatos accesoDatos, String cedulaTutor) throws SQLException {
        int id = 0;
        String strSQL = "SELECT \n"
                + "  usuario_entidad_rol.id_usuario_entidad_rol\n"
                + "FROM \n"
                + "  practicas.usuario, \n"
                + "  practicas.usuario_entidad_rol, \n"
                + "  practicas.usuario_entidad\n"
                + "WHERE \n"
                + "usuario.cedula='" + cedulaTutor + "' AND\n"
                + "  usuario.id_usuario = usuario_entidad.id_usuario AND\n"
                + "  usuario_entidad.id_usuario_entidad = usuario_entidad_rol.id_usuario_entidad ";

        if (accesoDatos.EjecutarSQL(strSQL) == 1) {
            ResultSet rslCodigoEmpresa = accesoDatos.getRs();
            while (rslCodigoEmpresa.next()) {
                id = rslCodigoEmpresa.getInt(1);
            }
        }
        return id;
    }

    public int insertarDocente(AccesoDatos accesoDatos, String cedulaTutor, int entidad) throws SQLException {
        int id = 0;
        String strSQL = "INSERT INTO practicas.usuario(cedula)VALUES ('" + cedulaTutor + "')returning id_usuario";

        if (accesoDatos.EjecutarSQL(strSQL) == 1) {
            ResultSet rslU = accesoDatos.getRs();
            while (rslU.next()) {
                strSQL = "INSERT INTO practicas.usuario_entidad(id_usuario, id_entidad)VALUES ('" + rslU.getInt(1) + "','" + entidad + "')returning id_usuario_entidad";

                if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                    ResultSet rslUE = accesoDatos.getRs();
                    while (rslUE.next()) {
                        strSQL = "INSERT INTO practicas.usuario_entidad_rol(id_usuario_entidad, id_rol)VALUES ('" + rslUE.getInt(1) + "' ,'" + 2 + "')returning id_usuario_entidad_rol";

                        if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                            ResultSet rslUER = accesoDatos.getRs();
                            while (rslUER.next()) {
                                id = rslUER.getInt(1);
                            }
                        }

                    }
                }
            }
        }
        return id;
    }

}
