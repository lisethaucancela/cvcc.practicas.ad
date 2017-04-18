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
import cvcc.practicas.ad.sw.swServicioEspoch;
import cvcc.practicas.entidades.CUsuario;
import cvcc.practicas.entidades.CUsuarios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paola_Cajilema
 */
public class UsuariosAD extends CUsuarios {

    public UsuariosAD() {
    }

    public UsuariosAD(CUsuarios usuarios) {
        for (CUsuario usuario : usuarios.getUsuarios()) {
            UsuarioAD usuarioAD = new UsuarioAD(usuario);
            this.addUsuario(usuarioAD);
        }
    }

    public void loadListadoDocentesPorEscuela(AccesoDatos accesoDatos, String codigoEntidad) throws Exception {
        //Se obtendrá una lista de docentes de una escuela consumiendo servicios web del OASIS
        //dado un codigo de la entidad(codigo de la entidad a la cual pertenece la secretaria )

        swServicioEspoch oE = new swServicioEspoch();
        //obtiene todas las materias de la carrera
        ArrayOfMateriaPensum lst = oE.mallaCurricularPensumVigenteSinDescripcion(codigoEntidad);
        List<MateriaPensum> obj = lst.getMateriaPensum();
        for (MateriaPensum obj2 : obj) {
            //busca los datos de los docentes en cada materia 
            ArrayOfDictadoMateria lstDM = oE.dictadosMateria(codigoEntidad, obj2.getCodMateria());
            if (lstDM != null) {
                List<DictadoMateria> objDM = lstDM.getDictadoMateria();
                for (DictadoMateria objDM2 : objDM) {
                    CUsuario objUsuario = new CUsuario();
                    objUsuario.setCedula(objDM2.getDocente().getCedula());
                    objUsuario.setNombres(objDM2.getDocente().getNombres());
                    objUsuario.setApellidos(objDM2.getDocente().getApellidos());
                    objUsuario.setEmail(objDM2.getDocente().getEmail());// + obj2.getMateria() + obj2.getNivel());
                    getUsuarios().add(objUsuario);
                }
            }
        }
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
}
