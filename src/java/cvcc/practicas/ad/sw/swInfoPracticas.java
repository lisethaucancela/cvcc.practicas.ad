/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.sw;

import cvcc.practicas.ad.funciones.empresa.EmpresaLN;
import cvcc.practicas.ad.funciones.funcionario.FuncionarioLN;
import cvcc.practicas.ad.funciones.modalidad.ModalidadLN;
import cvcc.practicas.ad.funciones.practicas.PracticasLN;
import cvcc.practicas.ad.funciones.usuario.UsuarioLN;
import cvcc.practicas.ad.funciones.actividadplanificada.ActividadPlanificadaLN;
import cvcc.practicas.ad.funciones.convenio.ConvenioLN;
import cvcc.practicas.ad.funciones.entidad.EntidadLN;
import cvcc.practicas.ad.funciones.facultad.FacultadLN;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Paola_Cajilema
 */
@WebService(serviceName = "swInfoPracticas")
public class swInfoPracticas {

    /**
     * *************************************************************************
     * ACTIVIDADES PLANIFICADAS
     * *************************************************************************
     * Lista las actividades planificadas de una planificación
     */
    @WebMethod(operationName = "loadActividadesPlanificadas")
    public String loadActividadesPlanificadas(@WebParam(name = "codigoPlanificacion") int codigoPlanificacion) {
        ActividadPlanificadaLN DatosActividadesLN = new ActividadPlanificadaLN();
        String result = DatosActividadesLN.loadActividadesPlanificadas(codigoPlanificacion);
        return result;
    }

    @WebMethod(operationName = "guardarListaActividades")
    public String guardarListaActividades(@WebParam(name = "strCadenaJSON") String strCadenaJSON) {
        String result = "{}";
        ActividadPlanificadaLN actividadesLN = new ActividadPlanificadaLN();
        result = actividadesLN.guardarActividades(strCadenaJSON);
        return result;
    }

    /* *************************************************************************
     *     CONVENIOS
     * *************************************************************************/
    @WebMethod(operationName = "loadConvenios")
    public String loadConvenios(@WebParam(name = "idEntidad") int idEntidad) {
        String result = "{}";
        ConvenioLN convenioLn = new ConvenioLN();
        result = convenioLn.loadConvenio(idEntidad);
        // swServicioEspoch oServicioE = new swServicioEspoch();
        //CEntidad list = oServicioE.convenios(idEntidad);
        //Gson gson = new Gson();
        //result = gson.toJson(objEntidad);
        return result;
    }
    /* *************************************************************************
     *  DOCENTES
     * *************************************************************************
     Lista los docentes con el número de prácticas a cargo sin importar el periodo */

    @WebMethod(operationName = "loadListadoDocentesPorEscuela")
    public String loadListadoDocentesPorEscuela(@WebParam(name = "codigoCarrera") String codigoCarrera) {
        UsuarioLN uLN = new UsuarioLN();
        String result = uLN.loadListadoDocentesPorEscuela(codigoCarrera);
        return result;
    }

    @WebMethod(operationName = "insertarTutor")
    public String insertarPractica(@WebParam(name = "cedula") String cedula, @WebParam(name = "practica") String practica, @WebParam(name = "entidad") int entidad) {
        PracticasLN uLN = new PracticasLN();
        String result = uLN.insertarTutor(cedula, practica, entidad);
        return result;
    }

    /* *************************************************************************
     *  EMPRESAS
     * *************************************************************************/
    @WebMethod(operationName = "loadEmpresas")
    public String loadEmpresas() {
        EmpresaLN DatosEmpresaLN = new EmpresaLN();
        String result = DatosEmpresaLN.loadEmpresas();
        return result;
    }

    /* *************************************************************************
     *  ENTIDADES
     * *************************************************************************/
    @WebMethod(operationName = "loadEntidades")
    public String loadEntidades() {
        EntidadLN DatosEntidadesLN = new EntidadLN();
        String result = DatosEntidadesLN.loadEntidades();
        return result;
    }

    /* *************************************************************************
     *  FACULTADES
     * *************************************************************************/
    @WebMethod(operationName = "loadFacultades")
    public String loadFacultades() {
        FacultadLN DatosFacultadLN = new FacultadLN();
        String result = DatosFacultadLN.loadFacultades();
        return result;
    }


    /* *************************************************************************
     *  FUNCIONARIOS
     * *************************************************************************/
    /* Función que permite obtener los datos de un funcionario */
    @WebMethod(operationName = "loadDatosUnFuncionario")
    public String loadDatosUnFuncionario(@WebParam(name = "codigoPractica") int codigoPractica) {
        FuncionarioLN DatosFuncionarioLN = new FuncionarioLN();
        String result = DatosFuncionarioLN.loadDatosUnFuncionario(codigoPractica);
        return result;
    }

    //ListarFuncionariosPorPractica
    @WebMethod(operationName = "loadListarFuncionariosPorPractica")
    public String loadListarFuncionariosPorPractica(@WebParam(name = "codigoPractica") int codigoPractica) {
        FuncionarioLN DatosFuncionarioLN = new FuncionarioLN();

        String result = DatosFuncionarioLN.loadListaFuncionarios(codigoPractica);
        return result;
    }

    //insertarFuncionarios datos de los funcionarios con id de la empresa
    @WebMethod(operationName = "insertarListaFuncionarios")
    public String insertarFuncionario(@WebParam(name = "strCadenaJSON") String strCadenaJSON) {
        String result = "{}";
        FuncionarioLN objFuncionario = new FuncionarioLN();
        boolean resultBoolean = false;
        resultBoolean = objFuncionario.InsertarFuncionario(strCadenaJSON);
        if (resultBoolean) {
            result = "Datos Ingresados";
        } else {
            result = "Datos NO Ingresados";
        }
        return result;
    }

    /* *************************************************************************
     *  MODALIDADES
     * *************************************************************************/
    @WebMethod(operationName = "loadModalidades")
    public String loadModalidades() {
        ModalidadLN DatosModalidadesLN = new ModalidadLN();
        String result = DatosModalidadesLN.loadModalidades();
        return result;
    }

    /* *************************************************************************
     *  OBJETIVOS
     * *************************************************************************/
    @WebMethod(operationName = "guardarListaObjetivos")
    public String guardarListaObjetivos(@WebParam(name = "strCadenaJSON") String strCadenaJSON) {
        String result = "{}";
        PracticasLN PracticaLN = new PracticasLN();
        result = PracticaLN.guardarObjetivos(strCadenaJSON);
        return result;
    }

    @WebMethod(operationName = "loadObjetivos")
    public String loadObjetivos(@WebParam(name = "codigoPractica") int codigoPractica) {
        PracticasLN DatosPracticasLN = new PracticasLN();
        String result = DatosPracticasLN.loadObjetivos(codigoPractica);
        return result;
    }

    /* *************************************************************************
     *  PLANIFICACIONES
     * *************************************************************************/
    @WebMethod(operationName = "loadPlanificaciones")
    public String loadPlanificaciones(@WebParam(name = "codigoPractica") int codigoPractica) {
        PracticasLN DatosPracticasLN = new PracticasLN();
        String result = DatosPracticasLN.loadPlanificaciones(codigoPractica);
        return result;
    }

    @WebMethod(operationName = "guardarPlanificaciones")
    public String guardarPlanificaciones(@WebParam(name = "strCadenaJSON") String strCadenaJSON) {
        String result = "{}";
        PracticasLN PracticaLN = new PracticasLN();
        result = PracticaLN.guardarPlanificaciones(strCadenaJSON);
        return result;
    }

    /* *************************************************************************
     *  PRACTICAS
     * *************************************************************************/
    /*Listar Practicas para la secretaria la cual permitir cambiar de tutor o asignar un tutor eliminar la practica
     */
    @WebMethod(operationName = "loadListarPracticas")
    public String loadListarPracticas(@WebParam(name = "codigoPractica") String codigoEntidad) {
        PracticasLN pLN = new PracticasLN();
        String result = pLN.loadListarPracticas(codigoEntidad);
        return result;
    }

    /* Listar Practicas de cualquier con su número de cédula*/
    @WebMethod(operationName = "loadPracticasPorUsuario")
    public String loadPracticasPorUsuario(@WebParam(name = "cedula") String cedula) {
        String result = "{}";
        try {
            PracticasLN pLN = new PracticasLN();
            result = pLN.loadPracticasPorUsuario(cedula);
        } catch (Exception e) {
            System.out.println("error:" + e);
        }
        return result;
    }

    @WebMethod(operationName = "guardarPracticas")
    public String guardarPracticas(@WebParam(name = "strCadenaJSON") String strCadenaJSON, @WebParam(name = "user_ent_rol") int user_ent_rol) {
        String result = "{}";
        PracticasLN practicasLN = new PracticasLN();
        result = practicasLN.guardarPracticas(strCadenaJSON, user_ent_rol);
        return result;
    }

}
