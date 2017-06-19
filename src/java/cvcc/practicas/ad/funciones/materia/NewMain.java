/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.materia;

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
        MateriaLN obj = new MateriaLN();
        // String x = obj.loadMateriasCarrera("EIS");
        String x = obj.loadDocentesMateria("EIS", "IS13131");
        System.out.println(x);
    }

}
