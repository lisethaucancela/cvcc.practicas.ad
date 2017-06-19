/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.institucionesEjecutoras;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.sw.swProyectosInvestigacion;
import cvcc.practicas.entidades.CInstitucionEjecutora;
import cvcc.practicas.entidades.CInstitucionEjecutoras;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import cvcc.practicas.ad.sw.WSProyectosInvestigacion.SwCInstitucionEjecutora;

/**
 *
 * @author Paola_Cajilema
 */
public class InstitucionesEjecutorasAD extends CInstitucionEjecutoras {

    public void loadInstitucionesEjecutorasPorPractica(AccesoDatos accesoDatos, int idPractica) throws SQLException {
        swProyectosInvestigacion sw = new swProyectosInvestigacion();
        int idProyecto = loadCodigoProyecto(accesoDatos, idPractica);
        List<SwCInstitucionEjecutora> ListIE = sw.loadListaInstitucionesEjecutorasPorProyecto(idProyecto);
        for (SwCInstitucionEjecutora IEsw : ListIE) {
            CInstitucionEjecutora IE = new CInstitucionEjecutora();
            IE.setId(IEsw.getId());
            IE.setDescripcion(IEsw.getDescripcion());
            IE.setCodigo(IEsw.getCodigo());
            this.addInstitucionEjecutoras(IE);
        }

    }

    public int loadCodigoProyecto(AccesoDatos accesoDatos, int idPractica) throws SQLException {
        int result = -1;

        String strSQL2 = "SELECT id_proyecto\n"
           + "  FROM practicas.practica_proyecto where id_practica='" + idPractica + "';";
        if (accesoDatos.EjecutarSQL(strSQL2) == 1) {
            ResultSet rslDatos = accesoDatos.getRs();
            while (rslDatos.next()) {
                result = rslDatos.getInt(1);
            }
        }
        return result;
    }

}
