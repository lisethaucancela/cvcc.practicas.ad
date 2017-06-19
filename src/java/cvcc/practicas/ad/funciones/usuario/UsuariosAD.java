/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.usuario;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.funciones.practicas.PracticasAD;
import cvcc.practicas.ad.sw.espoch.ArrayOfDictadoMateria;
import cvcc.practicas.ad.sw.espoch.ArrayOfMateriaPensum;
import cvcc.practicas.ad.sw.espoch.DictadoMateria;
import cvcc.practicas.ad.sw.espoch.MateriaPensum;
import cvcc.practicas.ad.sw.espoch.Persona;
import cvcc.practicas.ad.sw.swServicioEspoch;
import cvcc.practicas.entidades.CUsuario;
import cvcc.practicas.entidades.CUsuarios;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paola_Cajilema
 */
public class UsuariosAD extends CUsuarios {

    public UsuariosAD() {
    }

    public UsuariosAD(List<CUsuario> lstUsuario) {
        for (int i = 0; i < lstUsuario.size(); i++) {
            this.addUsuario(lstUsuario.get(i));
        }
    }

    public UsuariosAD(CUsuarios usuarios) {
        for (CUsuario usuario : usuarios.getUsuarios()) {
            UsuarioAD usuarioAD = new UsuarioAD(usuario);
            this.addUsuario(usuarioAD);
        }
    }

    public void loadListadoDocentesPorCarrera(String codigoEntidad) throws Exception {
        //Se obtendrá una lista de docentes de una escuela consumiendo servicios web del OASIS
        //dado un codigo de la entidad(codigo de la entidad a la cual pertenece la secretaria )

        swServicioEspoch oE = new swServicioEspoch();
        //obtiene todas las materias de la carrera
        ArrayOfMateriaPensum lst = oE.mallaCurricularPensumVigenteSinDescripcion(codigoEntidad);
        List<MateriaPensum> obj = lst.getMateriaPensum();
        for (MateriaPensum obj2 : obj) {
            //busca los datos de los docentes en cada materia 
            loadDocentesMateria(codigoEntidad, obj2.getCodMateria());
        }
        eliminarRepetidos();
    }

    public void loadDocentesMateria(String codigoEntidad, String CodMateria) {
        swServicioEspoch oE = new swServicioEspoch();
        ArrayOfDictadoMateria lstDM = oE.dictadosMateria(codigoEntidad, CodMateria);
        if (lstDM != null) {
            List<DictadoMateria> objDM = lstDM.getDictadoMateria();
            for (DictadoMateria objDM2 : objDM) {
                CUsuario objUsuario = new CUsuario();
                objUsuario.setCedula(objDM2.getDocente().getCedula());
                objUsuario.setNombres(objDM2.getDocente().getNombres());
                objUsuario.setApellidos(objDM2.getDocente().getApellidos());
                objUsuario.setEmail(objDM2.getDocente().getEmail());// + obj2.getMateria() + obj2.getNivel());
                //getUsuarios().add(objUsuario);
                addUsuario(objUsuario);
            }
        }
    }

    public void loadDocentesMaterias(String codigoEntidad, String CodMateria) {
        loadDocentesMateria(codigoEntidad, CodMateria);
        eliminarRepetidos();
    }

    public void asignarNumeroPracticaDocente(AccesoDatos accesoDatos) throws Exception {
        for (int i = 0; i < this.getUsuarios().size(); i++) {
            UsuarioAD o = new UsuarioAD(this.getUsuarios().get(i));
            int num = o.NumeroPracticasACargo(accesoDatos);
            //Create a new object for replace 
            CUsuario objUsuario = new CUsuario();
            objUsuario.setIdUsuario(this.getUsuarios().get(i).getIdUsuario());
            objUsuario.setCedula(this.getUsuarios().get(i).getCedula());
            objUsuario.setNombres(this.getUsuarios().get(i).getNombres());
            objUsuario.setApellidos(this.getUsuarios().get(i).getApellidos());
            objUsuario.setEmail(this.getUsuarios().get(i).getEmail());
            objUsuario.setNumeroPractica(num);
            //Delete object in this position 
            this.getUsuarios().remove(i);
            //insert object in this position
            this.getUsuarios().add(i, objUsuario);
        }
    }

    public void eliminarRepetidos() {
        //Delete all objects repeated leave only one 
        for (int i = 0; i < this.getUsuarios().size(); i++) {
            for (int j = i + 1; j < this.getUsuarios().size(); j++) {
                if (this.getUsuarios().get(i).getCedula().equals(this.getUsuarios().get(j).getCedula())) {
                    //Delete a object repetead 
                    this.getUsuarios().remove(j);
                    j = j - 1;
                }
            }
        }
    }

    // lista de docente tutores de un estudiante
    public void ListaTutoresPractica(AccesoDatos accesoDatos, int idPractica) {
        //lista los estudiantes pertenecientes a una práctica
        String cedula = "";
        // List<CUsuario> objU = new ArrayList<>();
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
               + "usuario_entidad_rol_practica.id_practica='" + idPractica + "' AND  "
               //+ "usuario_entidad_rol_practica.estado=true AND "
               + "usuario_entidad_rol_practica.id_usuario_entidad_rol = usuario_entidad_rol.id_usuario_entidad_rol AND "
               + "usuario_entidad_rol.id_usuario_entidad = usuario_entidad.id_usuario_entidad AND "
               + "usuario_entidad_rol.id_rol!='" + 1 + "' AND "
               + "usuario_entidad.id_usuario = usuario.id_usuario;";

            if (accesoDatos.EjecutarSQL(strSQL) == 1) {//==
                ResultSet rslCedula = accesoDatos.getRs();
                while (rslCedula.next()) {
                    cedula = rslCedula.getString(2);
                    boolean estado = rslCedula.getBoolean(3);

                    CUsuario objUsuario = new CUsuario();
                    SWDatosUsuario("EIS", cedula, estado);
                    //objUsuario = SWDatosUsuario("EIS", cedula, estado);
                    // addUsuario(objUsuario);
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        // return objU;
    }

    public void SWDatosUsuario(String codCarrera, String cedula, boolean estado) {
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
        // return objUsuario;
        addUsuario(objUsuario);
    }

}
