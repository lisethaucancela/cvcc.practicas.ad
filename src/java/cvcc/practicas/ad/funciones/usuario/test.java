/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.usuario;

/**
 *
 * @author Paola_Cajilema
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UsuarioLN obj = new UsuarioLN();
        //System.out.println(obj.loadListadoDocentesPorEscuela("EIS"));
        String x = obj.loadListarDocentesTutores(3);

        System.out.println(x);

    }

}
