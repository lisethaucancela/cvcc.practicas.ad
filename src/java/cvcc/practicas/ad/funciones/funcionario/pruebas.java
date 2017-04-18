/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.funcionario;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import cvcc.practicas.entidades.CFuncionario;
import cvcc.practicas.entidades.CFuncionarios;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paola_Cajilema
 */
public class pruebas {

    public static void main(String[] args) {
        // TODO code application logic here
        FuncionarioLN obj = new FuncionarioLN();
        //System.out.println(obj.loadDatosUnFuncionario(1));
        String x = obj.loadListaFuncionarios(1);
        System.out.println(x);
        Gson gson = new Gson();
        CFuncionario xyz = gson.fromJson(x, CFuncionario.class);

        obj.InsertarFuncionario("{\"funcionarios\":[{\"IdFuncionario\":2,\"ObjEmpresa\":{\"IdEmpresa\":1},\"Nombres\":\"Abril  Gonzales\",\"Telefono\":\"0995229601\",\"Cargo\":\"secretaria/otro\",\"Estado\":false},{\"IdFuncionario\":1,\"ObjEmpresa\":{\"IdEmpresa\":1},\"Nombres\":\"Wilson Sebastian Tijeras Camacho\",\"Telefono\":\"0988947971\",\"Cargo\":\"director/otro\",\"Estado\":false},{\"IdFuncionario\":39,\"ObjEmpresa\":{\"IdEmpresa\":1},\"Nombres\":\"Paola\",\"Telefono\":\"0995229609\",\"Cargo\":\"tecina\",\"Estado\":true}]}");
    }
}
