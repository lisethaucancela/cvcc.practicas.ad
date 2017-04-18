/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.modalidad;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.entidades.CModalidad;
import cvcc.practicas.entidades.CModalidades;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Liseth
 */
public class ModalidadesAD extends CModalidades {

    ModalidadesAD(CModalidades listaModalidades) {
        for (CModalidad modalidad : listaModalidades.getListModalidades()) {
            ModalidadAD modalidadAD = new ModalidadAD(modalidad);
            this.addListModalidades(modalidadAD);
        }
    }

    ModalidadesAD() {

    }

    public void guardarModalidades(AccesoDatos accesoDatos) {
        try {
            for (CModalidad modalidades : this.getListModalidades()) {
                ModalidadAD modalidadAD = (ModalidadAD) modalidades;
                modalidadAD.guardarModalidad(accesoDatos);
            }
        } catch (Exception e) {
            System.err.println("e:" + e.getMessage());
        }
    }

    public void obtenerListaModalidades(AccesoDatos accesoDatos) throws Exception {
        List<CModalidad> lstModalidad = new ArrayList<>();
        CModalidad objModalidad = new CModalidad();
        String sql = "SELECT id_modalidad, descripcion\n"
                + "  FROM practicas.modalidad;";
        try {
            if (accesoDatos.Connectar() == 2) {
                if (accesoDatos.EjecutarSQL(sql) == 1) {
                    ResultSet rsDatos = accesoDatos.getRs();
                    while (rsDatos.next()) {
                        objModalidad.setIdModalidad(rsDatos.getInt(1));
                        objModalidad.setDescripcion(rsDatos.getString(2));
                        getListModalidades().add(objModalidad);
                    }
                }
            }
        } catch (SQLException exConec) {
            System.err.println("e: " + exConec.getMessage());
            throw new Exception(exConec.getMessage());
        }
    }
}
