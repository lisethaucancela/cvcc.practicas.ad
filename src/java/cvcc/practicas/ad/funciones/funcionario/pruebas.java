/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.funcionario;

import cvcc.practicas.ad.sw.swInfoPracticas;

/**
 *
 * @author Paola_Cajilema
 */
public class pruebas {

    public static void main(String[] args) {
        // TODO code application logic here
        // FacultadLN obj = new FacultadLN();
        //String var = obj.loadFacultades();
        // System.out.println(var);
       /* FuncionarioLN obj = new FuncionarioLN();

         int x = obj.idFuncionarioExistentePracticaConvenio(1);

         AccesoDatos accesoDatos = new AccesoDatos();
         if (accesoDatos.Connectar() == 2) {
         ConvenioAD objC = new ConvenioAD();
         objC.idConvenioPorPractica(accesoDatos, 1);
         }*/

        swInfoPracticas objInfoPracticas = new swInfoPracticas();
       // int x=objInfoPracticas.getIdFuncionarioExistentePracticaConvenio(1);
        // System.out.println(x);
        // Gson gson = new Gson();
        //CFuncionario xyz = gson.fromJson(x, CFuncionario.class);

        //obj.InsertarFuncionario("{\"funcionarios\":[{\"IdFuncionario\":2,\"ObjEmpresa\":{\"IdEmpresa\":1},\"Nombres\":\"Abril  Gonzales\",\"Telefono\":\"0995229601\",\"Cargo\":\"secretaria/otro\",\"Estado\":false},{\"IdFuncionario\":1,\"ObjEmpresa\":{\"IdEmpresa\":1},\"Nombres\":\"Wilson Sebastian Tijeras Camacho\",\"Telefono\":\"0988947971\",\"Cargo\":\"director/otro\",\"Estado\":false},{\"IdFuncionario\":39,\"ObjEmpresa\":{\"IdEmpresa\":1},\"Nombres\":\"Paola\",\"Telefono\":\"0995229609\",\"Cargo\":\"tecina\",\"Estado\":true}]}");
    }
}
