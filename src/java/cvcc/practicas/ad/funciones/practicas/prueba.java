/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.practicas;

import cvcc.practicas.ad.conexion.AccesoDatos;
import cvcc.practicas.ad.funciones.usuario.UsuarioLN;
import cvcc.practicas.ad.funciones.usuario.UsuariosAD;
import cvcc.practicas.entidades.CUsuario;
import cvcc.practicas.entidades.CUsuarios;
import java.util.List;
import com.google.gson.Gson;

/**
 *
 * @author Paola_Cajilema
 */
public class prueba {

    public static void main(String[] args) throws Exception {
        PracticasLN obj = new PracticasLN();
    String cadena = obj.loadListarPracticas("1");
//        System.out.println(cadena);
        //      UsuarioLN obj = new UsuarioLN();
        //AccesoDatos accesoDatos = new AccesoDatos();
//        String strCadena = obj.loadListadoDocentesPorEscuela("EIS");
//        System.out.println(strCadena);
//        System.out.println("");
//        Gson gson = new Gson();
//        CUsuarios resultCU = gson.fromJson(strCadena, CUsuarios.class);
//        UsuariosAD UsAD = new UsuariosAD(resultCU);
//
//        for (int i = 0; i < UsAD.getUsuarios().size(); i++) {
//            System.out.println(" " + UsAD.getUsuarios().get(i).getCedula() + " \t" + UsAD.getUsuarios().get(i).getNombres() + "  " + UsAD.getUsuarios().get(i).getApellidos() + " \t" + UsAD.getUsuarios().get(i).getEmail() + " \t" + UsAD.getUsuarios().get(i).getNumeroPractica());
//        }
        obj.insertarTutor("1234567895", "4", 3);
    }

}
