/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.sw;

import cvcc.practicas.ad.funciones.empresa.EmpresaLN;
import cvcc.practicas.ad.funciones.modalidad.ModalidadLN;
import cvcc.practicas.ad.funciones.practicas.PracticasLN;
import cvcc.practicas.ad.funciones.usuario.UsuarioLN;
import cvcc.practicas.ad.funciones.actividadplanificada.ActividadPlanificadaLN;
import cvcc.practicas.ad.funciones.convenio.ConvenioLN;
import cvcc.practicas.ad.funciones.entidad.EntidadLN;
import cvcc.practicas.ad.funciones.evaluacionCualitativa.EvaluacionCualitativaLN;
import cvcc.practicas.ad.funciones.facultad.FacultadLN;
import cvcc.practicas.ad.funciones.funcionario.FuncionarioLN;
import cvcc.practicas.ad.funciones.institucionesEjecutoras.InstitucionesEjecutoraLN;
import cvcc.practicas.ad.funciones.materia.MateriaLN;
import cvcc.practicas.ad.funciones.nivelSatisfaccion.NivelSatisfaccionLN;
import cvcc.practicas.ad.funciones.parametroCualitativo.ParametroCualitativoLN;
import cvcc.practicas.ad.funciones.proyectos.ProyectoLN;
import cvcc.practicas.ad.funciones.unidadAministrativa.UnidadAdministrativaLN;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceRef;

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
    public String loadConvenios(@WebParam(name = "codigo_unidad_academica") String codigo_unidad_academica) {
        String result = "{}";
        ConvenioLN convenioLn = new ConvenioLN();
        result = convenioLn.loadConvenio(codigo_unidad_academica);
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
    public String loadListadoDocentesPorCarrera(@WebParam(name = "codigoEntidad") String codigoEntidad) {
        UsuarioLN uLN = new UsuarioLN();
        String result = uLN.loadListadoDocentesPorCarrera(codigoEntidad);
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

    //
    @WebMethod(operationName = "codigoEntidad")
    public String codigoEntidad(@WebParam(name = "cedula") String cedula) {
        UsuarioLN uLN = new UsuarioLN();
        String result = uLN.codigoEntidad(cedula);
        return result;
    }

    //proyectos  
    @WebMethod(operationName = "loadListaProyectosPorEntidad")
    public String loadListaProyectosPorEntidad(@WebParam(name = "codidoEntidad") String codidoEntidad) {
        ProyectoLN uLN = new ProyectoLN();
        String result = uLN.loadListaProyectosPorEntidad(codidoEntidad);
        return result;
    }

    @WebMethod(operationName = "guardarProyectoEstudiante")
    public String guardarProyectoEstudiante(@WebParam(name = "idPractica") int idPractica, @WebParam(name = "idProyecto") int idProyecto) {
        ProyectoLN uLN = new ProyectoLN();
        String result = uLN.guardarProyectoEstudiante(idPractica, idProyecto);
        return result;
    }

    @WebMethod(operationName = "codigoProyectoAsignado")
    public int codigoProyectoAsignado(@WebParam(name = "idPractica") int idPractica) {
        ProyectoLN uLN = new ProyectoLN();
        int result = uLN.codigoProyectoAsignado(idPractica);
        return result;
    }

    @WebMethod(operationName = "loadInstitucionesEjecutorasPorPractica")
    public String loadInstitucionesEjecutorasPorPractica(@WebParam(name = "idPractica") int idPractica) {
        InstitucionesEjecutoraLN uLN = new InstitucionesEjecutoraLN();
        String result = uLN.loadInstitucionesEjecutorasPorPractica(idPractica);
        return result;
    }

    @WebMethod(operationName = "loadFuncionariosPorPractica")
    public String loadFuncionariosPorPractica(@WebParam(name = "idPractica") int idPractica) {
        FuncionarioLN uLN = new FuncionarioLN();
        String result = uLN.loadFuncionariosPorPractica(idPractica);
        return result;
    }

    @WebMethod(operationName = "idFuncionarioExistentePracticaConvenio")
    public int idFuncionarioExistentePracticaConvenio(@WebParam(name = "idPractica") int idPractica) {
        FuncionarioLN uLN = new FuncionarioLN();
        int result = uLN.idFuncionarioExistentePracticaConvenio(idPractica);
        return result;
    }

    @WebMethod(operationName = "actualizarAsignarFuncionario")
    public boolean actualizarAsignarFuncionario(@WebParam(name = "idPractica") int idPractica, @WebParam(name = "idFuncionario") int idFuncionario) {
        FuncionarioLN uLN = new FuncionarioLN();
        boolean result = uLN.actualizarAsignarFuncionario(idPractica, idFuncionario);
        return result;
    }

    @WebMethod(operationName = "guardarUnidadAdministrativa_practicaConvenio")
    public String guardarUnidadAdministrativa_practicaConvenio(@WebParam(name = "idPractica") int idPractica, @WebParam(name = "idFuncionario") int idUnidadAdministrativa) {
        UnidadAdministrativaLN uLN = new UnidadAdministrativaLN();
        String result = uLN.actualizarUnidadAdministrativa(idPractica, idUnidadAdministrativa);
        return result;
    }

    //servicio web de convenios UNIDADES ADMINISTRATIVAS
    @WebMethod(operationName = "idUnidadAdministrativaExistente_practicaConvenio")
    public int idUnidadAdministrativaExistente_practicaConvenio(@WebParam(name = "idPractica") int idPractica) {
        UnidadAdministrativaLN uLN = new UnidadAdministrativaLN();
        int result = uLN.idUnidadAdministrativaExistente_practicaConvenio(idPractica);
        return result;
    }

    @WebMethod(operationName = "loadUnidadAdministrativaPorPractica")
    public String loadUnidadAdministrativaPorPractica(@WebParam(name = "idPractica") int idPractica) {
        UnidadAdministrativaLN uLN = new UnidadAdministrativaLN();
        String result = uLN.loadUnidadAdministrativaPorPractica(idPractica);
        return result;
    }

    @WebMethod(operationName = "loadListarDocentesTutores")
    public String loadListarDocentesTutores(@WebParam(name = "idPractica") int idPractica) {
        UsuarioLN uLN = new UsuarioLN();
        String result = uLN.loadListarDocentesTutores(idPractica);
        return result;
    }
    //**************EMPRESA********************************
    //*****************************************************

    @WebMethod(operationName = "loadEmpresaPorConvenio")
    public String loadEmpresaPorConvenio(@WebParam(name = "idPractica") int idPractica) {
        EmpresaLN uLN = new EmpresaLN();
        String result = uLN.loadEmpresaPorPractica(idPractica);
        return result;
    }

    @WebMethod(operationName = "loadMateriasCarrera")
    public String loadMateriasCarrera(@WebParam(name = "CodigoCarrera") String CodigoCarrera) {
        MateriaLN uLN = new MateriaLN();
        String result = uLN.loadMateriasCarrera(CodigoCarrera);
        return result;
    }

    @WebMethod(operationName = "loadDocentesMateria")
    public String loadDocentesMateria(@WebParam(name = "CodigoCarrera") String CodigoCarrera, @WebParam(name = "CodigoMateria") String CodigoMateria) {
        MateriaLN uLN = new MateriaLN();
        String result = uLN.loadDocentesMateria(CodigoCarrera, CodigoMateria);
        return result;
    }
//**************PARAMETROS CUALITATIVOS********************************
//*****************************************************

    @WebMethod(operationName = "loadParametrosCualitativos")
    public String loadParametrosCualitativos() {
        ParametroCualitativoLN parametroCualitativoLN = new ParametroCualitativoLN();
        String result = parametroCualitativoLN.loadParametrosCualitativos();
        return result;
    }

    @WebMethod(operationName = "loadNivelesSatisfaccion")
    public String loadNivelesSatisfaccion() {
        NivelSatisfaccionLN oLN = new NivelSatisfaccionLN();
        String result = oLN.loadNivelesSatisfaccion();
        return result;
    }

    @WebMethod(operationName = "actualizarEvaluacionCualitativaPractica")
    public boolean actualizarEvaluacionCualitativaPractica(@WebParam(name = "idPractica") int idPractica, @WebParam(name = "strJSON") String strJSON) {
        boolean result = false;
        EvaluacionCualitativaLN oLN = new EvaluacionCualitativaLN();
        result = oLN.actualizarEvaluacionCualitativaPractica(idPractica, strJSON);
        return result;
    }

    @WebMethod(operationName = "loadEvaluacionCualitativaPractica")
    public String loadEvaluacionCualitativaPractica(@WebParam(name = "idPractica") int idPractica) {
        String strResult;
        EvaluacionCualitativaLN oLN = new EvaluacionCualitativaLN();
        strResult = oLN.loadEvaluacionCualitativaPractica(idPractica);
        return strResult;
    }
}
