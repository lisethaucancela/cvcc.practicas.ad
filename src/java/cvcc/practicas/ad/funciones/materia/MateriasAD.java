/*
 * To change objMateria license header, choose License Headers in Project Properties.
 * To change objMateria template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.materia;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.sw.espoch.ArrayOfMateriaPensum;
import cvcc.practicas.ad.sw.espoch.MateriaPensum;
import cvcc.practicas.ad.sw.swServicioEspoch;
import cvcc.practicas.entidades.CMateria;
import cvcc.practicas.entidades.CMaterias;
import java.util.List;

/**
 *
 * @author Paola_Cajilema
 */
public class MateriasAD extends CMaterias {

    public MateriasAD() {
    }

    public MateriasAD(CMaterias objMaterias) {
        for (CMateria materia : objMaterias.getListMaterias()) {
            MateriaAD materiaAD = new MateriaAD(materia);
            this.addListMaterias(materiaAD);
        }
    }

    public void loadMateriasCarrera(String CodigoCarrera) {
        try {

            swServicioEspoch objSW = new swServicioEspoch();
            ArrayOfMateriaPensum lst = objSW.mallaCurricularPensumVigenteSinDescripcion(CodigoCarrera);
            List<MateriaPensum> obj = lst.getMateriaPensum();
            for (MateriaPensum obj2 : obj) {
                CMateria objMateria = new CMateria();
                objMateria.setCodigo(obj2.getCodMateria());
                objMateria.setNombre(obj2.getMateria());
                objMateria.setNivel(obj2.getNivel());
                objMateria.setArea(obj2.getArea());
                objMateria.setCreditos(obj2.getCreditos());
                objMateria.setHorasTeoricas(obj2.getHorasTeoricas());
                objMateria.setHorasPracticas(obj2.getHorasPracticas());
                addListMaterias(objMateria);
            }
        } catch (Exception e) {
            System.out.println("e: " + e.getMessage());
        }

    }

}
