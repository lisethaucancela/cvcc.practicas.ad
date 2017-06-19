/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.evaluacionCualitativa;

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

        EvaluacionCualitativaLN obj = new EvaluacionCualitativaLN();
        //obj.actualizarEvaluacionCualitativaPractica(1, "{\"EvaluacionCualitativas\":[{\"idParametro\":1,\"idNivelSatisfaccion\":\"1\"},{\"idParametro\":2,\"idNivelSatisfaccion\":\"2\"},{\"idParametro\":3,\"idNivelSatisfaccion\":\"3\"},{\"idParametro\":4,\"idNivelSatisfaccion\":\"4\"},{\"idParametro\":5,\"idNivelSatisfaccion\":\"3\"},{\"idParametro\":6,\"idNivelSatisfaccion\":\"2\"},{\"idParametro\":7,\"idNivelSatisfaccion\":\"1\"},{\"idParametro\":8,\"idNivelSatisfaccion\":\"2\"},{\"idParametro\":9,\"idNivelSatisfaccion\":\"3\"},{\"idParametro\":10,\"idNivelSatisfaccion\":\"4\"},{\"idParametro\":11,\"idNivelSatisfaccion\":\"3\"},{\"idParametro\":12,\"idNivelSatisfaccion\":\"2\"},{\"idParametro\":13,\"idNivelSatisfaccion\":\"1\"},{\"idParametro\":14,\"idNivelSatisfaccion\":\"2\"},{\"idParametro\":15,\"idNivelSatisfaccion\":\"3\"},{\"idParametro\":16,\"idNivelSatisfaccion\":\"4\"}]}");
        String srtResult = obj.loadEvaluacionCualitativaPractica(2);
        System.out.println(srtResult);
    }

}
