package cvcc.practicas.ad.funciones.proyectos;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CProyectos;
import java.sql.ResultSet;

/**
 *
 * @author Paola_Cajilema
 */
public class ProyectoAD extends CProyectos {

    ProyectoAD(CProyectos usuario) {
        try {
            if (usuario != null) {
                this.setId(usuario.getId());
                this.setNombre(usuario.getNombre());
                this.setFecha_inicio(usuario.getFecha_inicio());
                this.setFecha_fin(usuario.getFecha_fin());
                this.setNombreInvestigadorRresponsable(usuario.getNombreInvestigadorRresponsable());
                this.setDescripcionTipoInvestigacion(usuario.getDescripcionTipoInvestigacion());
                this.setDescripcionTipoProyecto(usuario.getDescripcionTipoProyecto());

                this.setDescripcionEstadoProyecto(usuario.getDescripcionEstadoProyecto());

                this.setLstInstitucionesEjecutoras(usuario.getLstInstitucionesEjecutoras());

            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }

    }

    ProyectoAD() {

    }

    public String guardarProyectoEstudiante(AccesoDatos accesoDatos, int idPractica, int idProyecto) {
        String result = "";
        try {
            if (verificoExistenciaProyectoEstudiante(accesoDatos, idPractica, idProyecto)) {
                result = actualizarProyectoEstudiante(accesoDatos, idPractica, idProyecto);

            } else {
                result = insertarProyectoEstudiante(accesoDatos, idPractica, idProyecto);
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }
//CRUD

    public boolean verificoExistenciaProyectoEstudiante(AccesoDatos accesoDatos, int idPractica, int idProyecto) {
        boolean result = false;
        int reslt = -1;
        try {
            String strSQL = " SELECT id_practica_proyecto\n"
               + "             FROM practicas.practica_proyecto where id_practica='" + idPractica + "'";

            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslDatosPracticas = accesoDatos.getRs();
                while (rslDatosPracticas.next()) {

                    reslt = rslDatosPracticas.getInt(1);
                    if (reslt != -1) {
                        result = true;//existe un proyecto asignado
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }

    public String insertarProyectoEstudiante(AccesoDatos accesoDatos, int idPractica, int idProyecto) {
        String result = "";
        try {
            String strSQL = "INSERT INTO practicas.practica_proyecto(\n"
               + "             id_practica, id_proyecto)\n"
               + "    VALUES ('" + idPractica + "', '" + idProyecto + "')"
               + " RETURNING  id_practica_proyecto;";
            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslDatosPracticas = accesoDatos.getRs();
                while (rslDatosPracticas.next()) {
                    int reslt = rslDatosPracticas.getInt(1);
                    if (reslt != 0) {
                        result = "DATOS INGRESADOS CORRECTAMENTE";
                    } else {
                        result = "NO SE HAN GUARDADO LOS DATOS";
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }

    public String actualizarProyectoEstudiante(AccesoDatos accesoDatos, int idPractica, int idProyecto) {
        String result = "";
        try {

            //fin comprobar que el proyecto no se encuentra registrado 
            String strSQL = "UPDATE practicas.practica_proyecto\n"
               + "   SET id_proyecto='" + idProyecto + "' \n"
               + " WHERE id_practica='" + idPractica + "' RETURNING  id_practica_proyecto;";
            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslDatosPracticas = accesoDatos.getRs(); //verificar que un registro se modifico
                while (rslDatosPracticas.next()) {
                    int reslt = rslDatosPracticas.getInt(1);
                    if (reslt != 0) {
                        result = "DATOS ACTUALIZADOS CORRECTAMENTE";
                    } else {
                        result = "NO SE HAN ACTUALIZADO LOS DATOS";
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }

    //FIN CRUD
    public int codigoProyectoAsignado(AccesoDatos accesoDatos, int idPractica) {
        //se obtendrá elcódigo de proyecto asociado a la práctica del estudiante
        int result = -1;
        try {
            String strSQL = "SELECT id_proyecto\n"
               + "  FROM practicas.practica_proyecto where id_practica='" + idPractica + "';";
            if (accesoDatos.EjecutarSQL(strSQL) == 1) {
                ResultSet rslDatosPracticas = accesoDatos.getRs();
                while (rslDatosPracticas.next()) {
                    result = rslDatosPracticas.getInt(1);
                }
            }
        } catch (Exception e) {
            System.err.println("e: " + e.getMessage());
        }
        return result;
    }

}
