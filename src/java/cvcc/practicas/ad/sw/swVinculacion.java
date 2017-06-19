/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.sw;

import cvcc.practicas.ad.sw.WSVinculacion.Convenio;
import cvcc.practicas.ad.sw.WSVinculacion.Entidad;

/**
 *
 * @author Paola_Cajilema
 */
public class swVinculacion {

    public Entidad loadConvenios(java.lang.String codigoUnidadAcademica) {
        cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion_Service service = new cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion_Service();
        cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion port = service.getWSVinculacionPort();
        return port.loadConvenios(codigoUnidadAcademica);
    }

    public Convenio loadFuncionariosPorConvenio(int idConvenio) {
        cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion_Service service = new cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion_Service();
        cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion port = service.getWSVinculacionPort();
        return port.loadFuncionariosPorConvenio(idConvenio);
    }

    public Convenio loadUnidadesAdministrativasPorConvenio(int idConvenio) {
        cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion_Service service = new cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion_Service();
        cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion port = service.getWSVinculacionPort();
        return port.loadUnidadesAdministrativasPorConvenio(idConvenio);
    }

    public Convenio loadEmpresasPorConvenio(int idConvenio) {
        cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion_Service service = new cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion_Service();
        cvcc.practicas.ad.sw.WSVinculacion.WSVinculacion port = service.getWSVinculacionPort();
        return port.loadEmpresasPorConvenio(idConvenio);
    }

}
