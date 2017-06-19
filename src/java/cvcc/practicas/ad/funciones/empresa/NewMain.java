/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.empresa;

/**
 *
 * @author Paola_Cajilema
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EmpresaLN obj = new EmpresaLN();
        String x = obj.loadEmpresaPorPractica(1);
        System.out.println(x);

    }

}
