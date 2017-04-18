/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.practicas;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.sw.espoch.Persona;
import cvcc.practicas.ad.sw.swServicioEspoch;

import cvcc.practicas.entidades.CEstadoPractica;
import cvcc.practicas.entidades.CModalidad;
import cvcc.practicas.entidades.CPeriodo;
import cvcc.practicas.entidades.CPractica;
import cvcc.practicas.entidades.CPracticass;
import cvcc.practicas.entidades.CUsuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paola_Cajilema
 */
public class PracticassAD extends CPracticass {

    PracticassAD(CPracticass listaPractica) {
        for (CPractica actividad : listaPractica.getPracticas()) {
            PracticasAD actividadAD = new PracticasAD(actividad);
            this.addPracticas(actividadAD);
        }
    }

    public PracticassAD() {
    }

    public void guardarPracticas(AccesoDatos accesoDatos, int user_ent_rol) {
        for (CPractica practica : this.getPracticas()) {
            PracticasAD practicaAD = (PracticasAD) practica;
            practicaAD.guardarPractica(accesoDatos, user_ent_rol);
        }
    }

    public void loadPracticasPorUsuario(AccesoDatos accesoDatos, String usuario) throws Exception {
        //muestra lista de practicas por cedula de usuario
        try {
            String strSQL = "SELECT  practica.id_practica, practica.descripcion,         \n"
                    + "          modalidad.descripcion, estado_practica.descripcion  \n"
                    + "    FROM  practicas.practica, practicas.usuario_entidad_rol_practica, \n"
                    + "          practicas.estado_practica, practicas.modalidad, \n"
                    + "          practicas.usuario_entidad_rol, practicas.usuario_entidad, \n"
                    + "          practicas.usuario\n"
                    + "    WHERE practica.id_modalidad = modalidad.id_modalidad AND\n"
                    + "          usuario_entidad_rol_practica.id_practica = practica.id_practica AND\n"
                    + "          estado_practica.id_estado_practica = practica.id_estado_practica AND\n"
                    + "          usuario_entidad_rol.id_usuario_entidad_rol = usuario_entidad_rol_practica.id_usuario_entidad_rol AND\n"
                    + "          usuario_entidad.id_usuario_entidad = usuario_entidad_rol.id_usuario_entidad AND\n"
                    + "          usuario.id_usuario = usuario_entidad.id_usuario AND usuario.cedula='" + usuario + "' ;";
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                    ResultSet rslDatosPracticas = accesoDatos.getRs();
                    while (rslDatosPracticas.next()) {
                        PracticasAD objPracticaAD = new PracticasAD();
                        objPracticaAD.setIdPractica(rslDatosPracticas.getInt(1));
                        objPracticaAD.setDescripcion(rslDatosPracticas.getString(2));

                        CModalidad objModalidad = new CModalidad();
                        objModalidad.setDescripcion(rslDatosPracticas.getString(3));
                        objPracticaAD.setObjModalidad(objModalidad);

                        CEstadoPractica objetoEstadoPractica = new CEstadoPractica();
                        objetoEstadoPractica.setDescripcion(rslDatosPracticas.getString(4));
                        objPracticaAD.setObjEstadoPractica(objetoEstadoPractica);
                        
                        objPracticaAD.loadPeriodos(accesoDatos);

                        this.addPracticas(objPracticaAD);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
    }

    public void loadListarPracticas(AccesoDatos accesoDatos, String codigoEntidad) throws Exception {
        //muestra lista de practicas mediante el codigoEntidad de la secretaria de la escuela 
        try {
            String strSQL = "SELECT "
                    + "practica.id_practica,"
                    + "practica.descripcion, "
                    + "modalidad.descripcion,"
                    + "periodo.codigo,"
                    + "estado_practica.descripcion"
                    + "     FROM "
                    + "practicas.entidad,"
                    + "practicas.usuario_entidad, "
                    + "practicas.usuario_entidad_rol, "
                    + "practicas.usuario_entidad_rol_practica, "
                    + "practicas.practica, "
                    + "practicas.modalidad, "
                    + " practicas.practica_periodo, "
                    + "practicas.periodo, "
                    + "practicas.estado_practica"
                    + "     WHERE "
                    + "usuario_entidad.id_entidad = entidad.id_entidad AND "
                    + "entidad.codigo_entidad='" + codigoEntidad + "'AND "
                    + "usuario_entidad_rol.id_rol='" + 1 + "' AND "//código 1 de estudiante
                    + "usuario_entidad_rol.id_usuario_entidad = usuario_entidad.id_usuario_entidad AND "
                    + "usuario_entidad_rol_practica.id_usuario_entidad_rol = usuario_entidad_rol.id_usuario_entidad_rol AND "
                    + "practica.id_practica = usuario_entidad_rol_practica.id_practica AND "
                    + "practica.id_practica = practica_periodo.id_practica AND "
                    + "practica.id_modalidad = modalidad.id_modalidad AND "
                    + "practica.id_estado_practica = estado_practica.id_estado_practica AND "
                    + "periodo.id_periodo = practica_periodo.id_periodo";

            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslCodigoEmpresa = accesoDatos.getRs();
                while (rslCodigoEmpresa.next()) {

                    CPractica objetoPractica = new CPractica();
                    objetoPractica.setIdPractica(rslCodigoEmpresa.getInt(1));
                    objetoPractica.setDescripcion(rslCodigoEmpresa.getString(2));

                    CModalidad objModalidad = new CModalidad();//---
                    objModalidad.setDescripcion(rslCodigoEmpresa.getString(3));
                    objetoPractica.setObjModalidad(objModalidad);

                    CPeriodo objPeriodo = new CPeriodo();//---
                    objPeriodo.setCodigo(rslCodigoEmpresa.getString(4));
                    //String en el servicio web 

                    objetoPractica.setObjPeriodo(objPeriodo);

                    CEstadoPractica objetoEstadoPractica = new CEstadoPractica();//----
                    objetoEstadoPractica.setDescripcion(rslCodigoEmpresa.getString(5));
                    objetoPractica.setObjEstadoPractica(objetoEstadoPractica);
                    //Lista de estudiantes
                    CUsuario objEstudiante = new CUsuario();
                    objEstudiante = DatosEstudiantePractica(accesoDatos, rslCodigoEmpresa.getInt(1));
                    objetoPractica.setObjUsuario(objEstudiante);
                    //Lista de docentes
                    List<CUsuario> objTutor = new ArrayList<>();
                    objTutor = DatosDocentesPractica(accesoDatos, rslCodigoEmpresa.getInt(1));
                    objetoPractica.setLstTutores(objTutor);

                    getPracticas().add(objetoPractica);
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
    }

    public CUsuario DatosEstudiantePractica(AccesoDatos accesoDatos, int idPractica) {
        //lista los estudiantes pertenecientes a una práctica
        String cedula = "";
        CUsuario objUsuario = new CUsuario();
        try {
            String strSQL = "SELECT "
                    + "usuario_entidad_rol_practica.id_practica, "
                    + "usuario.cedula"
                    + "     FROM "
                    + "practicas.usuario_entidad_rol_practica, "
                    + "practicas.usuario_entidad_rol, "
                    + "practicas.usuario_entidad, "
                    + "practicas.usuario"
                    + "     WHERE "
                    + "usuario_entidad_rol_practica.id_practica='" + idPractica + "' AND "
                    + "usuario_entidad_rol_practica.id_usuario_entidad_rol = usuario_entidad_rol.id_usuario_entidad_rol AND "
                    + "usuario_entidad_rol.id_usuario_entidad = usuario_entidad.id_usuario_entidad AND "
                    + "usuario_entidad_rol.id_rol='" + 1 + "' AND "
                    + "usuario_entidad.id_usuario = usuario.id_usuario;";

            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslCedula = accesoDatos.getRs();
                while (rslCedula.next()) {
                    cedula = rslCedula.getString(2);
                    boolean estado = true;
                    objUsuario = SWDatosUsuario("EIS", cedula, estado);
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return objUsuario;
    }

    public CUsuario SWDatosUsuario(String codCarrera, String cedula, boolean estado) {
        //obtiene los datos de un usuario del servicio web ya sea docente o estudiante
        CUsuario objUsuario = new CUsuario();
        swServicioEspoch o = new swServicioEspoch();
        Persona oPersona = o.datosUsuarioCarrera(codCarrera, cedula);//servicio web 
        //consumo de datos del estudiante del servicio web y asigno en el objeto 
        objUsuario.setCedula(oPersona.getCedula());
        objUsuario.setNombres(oPersona.getNombres());
        objUsuario.setApellidos(oPersona.getApellidos());
        if (oPersona.getEmail().equals("")) {
            objUsuario.setEmail("ninguno");
        } else {
            objUsuario.setEmail(oPersona.getEmail());
        }
        objUsuario.setEstado(estado);
        return objUsuario;
    }

    public List<CUsuario> DatosDocentesPractica(AccesoDatos accesoDatos, int idPractica) {
        //lista los estudiantes pertenecientes a una práctica
        String cedula = "";
        List<CUsuario> objU = new ArrayList<>();
        try {
            String strSQL = "SELECT "
                    + "usuario_entidad_rol_practica.id_practica, "
                    + "usuario.cedula, "
                    + "usuario_entidad_rol_practica.estado"
                    + "     FROM "
                    + "practicas.usuario_entidad_rol_practica, "
                    + "practicas.usuario_entidad_rol, "
                    + "practicas.usuario_entidad, "
                    + "practicas.usuario"
                    + "     WHERE "
                    + "usuario_entidad_rol_practica.id_practica='" + idPractica + "' AND "
                    + "usuario_entidad_rol_practica.id_usuario_entidad_rol = usuario_entidad_rol.id_usuario_entidad_rol AND "
                    + "usuario_entidad_rol.id_usuario_entidad = usuario_entidad.id_usuario_entidad AND "
                    + "usuario_entidad_rol.id_rol!='" + 1 + "' AND "
                    + "usuario_entidad.id_usuario = usuario.id_usuario;";

            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslCedula = accesoDatos.getRs();
                while (rslCedula.next()) {
                    cedula = rslCedula.getString(2);
                    boolean estado = rslCedula.getBoolean(3);

                    CUsuario objUsuario = new CUsuario();
                    objUsuario = SWDatosUsuario("EIS", cedula, estado);
                    //getUsuario().add(objUsuario);
                    objU.add(objUsuario);
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return objU;
    }

}
