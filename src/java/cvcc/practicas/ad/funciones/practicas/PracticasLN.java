/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.funciones.practicas;

import cvcc.practicas.ad.conexion.AccesoDatos;
import com.google.gson.Gson;
import cvcc.practicas.entidades.CPractica;
import cvcc.practicas.entidades.CPracticass;

/**
 *
 * @author Paola_Cajilema
 */
public class PracticasLN {

    public String guardarPracticas(String strCadenaJSON, int user_ent_rol) {
        String result = "DATOS NO INGRESADOS";
        try {
            Gson gson = new Gson();
            CPracticass listaPractica = gson.fromJson(strCadenaJSON, CPracticass.class);
            PracticassAD ActividadesAD = new PracticassAD(listaPractica);
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                ActividadesAD.guardarPracticas(accesoDatos, user_ent_rol);
                result = "DATOS INGRESADOS";
            }
            accesoDatos.Desconectar();
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return result;
    }

    public String loadListarPracticas(String codigoEntidad) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                PracticassAD FAD = new PracticassAD();
                FAD.loadListarPracticas(accesoDatos, codigoEntidad);
                Gson gson = new Gson();
                result = gson.toJson(FAD);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

    public String loadPracticasPorUsuario(String usuario) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                PracticassAD PAD = new PracticassAD();
                PAD.loadPracticasPorUsuario(accesoDatos, usuario);
                Gson gson = new Gson();
                result = gson.toJson(PAD);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

    public String insertarTutor(String cedula, String idPractica, int entidad) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                PracticasAD FAD = new PracticasAD();
                result = FAD.insertarTutor(accesoDatos, cedula, idPractica, entidad);

            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

    /*      ---     OBJETIVOS       ---*/
    public String loadObjetivos(int id_practica) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                PracticasAD PAD = new PracticasAD();
                PAD.setIdPractica(id_practica);
                PAD.loadObjetivos(accesoDatos);
                Gson gson = new Gson();
                result = gson.toJson(PAD);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

    public String guardarObjetivos(String strCadenaJSON) {
        String result = "DATOS NO INGRESADOS";
        AccesoDatos accesoDatos = new AccesoDatos();
        Gson gson = new Gson();
        try {
            CPractica practica = gson.fromJson(strCadenaJSON, CPractica.class);
            PracticasAD PracticaAD = new PracticasAD(practica);
            if (accesoDatos.Connectar() == 2) {
                PracticaAD.guardarObjetivos(accesoDatos);
                result = "DATOS INGRESADOS";
            }
            accesoDatos.Desconectar();
        } catch (Exception e) {
            accesoDatos.Desconectar();
            System.out.println("error" + e);
        }
        return result;
    }

    /*      ---     PLANIFICACIONES       ---*/
    public String loadPlanificaciones(int id_practica) {
        String result = "{}";
        try {
            AccesoDatos accesoDatos = new AccesoDatos();
            if (accesoDatos.Connectar() == 2) {
                PracticasAD PAD = new PracticasAD();
                PAD.setIdPractica(id_practica);
                PAD.loadPlanificaciones(accesoDatos);
                Gson gson = new Gson();
                result = gson.toJson(PAD);
                accesoDatos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return result;
    }

    public String guardarPlanificaciones(String strCadenaJSON) {
        String result = "DATOS NO INGRESADOS";
        AccesoDatos accesoDatos = new AccesoDatos();
        Gson gson = new Gson();
        try {
            CPractica practica = gson.fromJson(strCadenaJSON, CPractica.class);
            
            PracticasAD PracticaAD = new PracticasAD(practica);
            if (accesoDatos.Connectar() == 2) {
                PracticaAD.guardarPlanificaciones(accesoDatos);
                result = "DATOS INGRESADOS";
            }
            accesoDatos.Desconectar();
        } catch (Exception e) {
            accesoDatos.Desconectar();
            System.out.println("error" + e);
        }
        return result;
    }

}
