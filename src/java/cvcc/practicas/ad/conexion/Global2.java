/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.conexion;

import java.util.Locale;


/**
 *
 * @author Javier Romero
 * 
 */
public final class Global2 {

    Locale  lt = new Locale("es_ES");
    //java.util.ResourceBundle Configuracion = java.util.ResourceBundle.getBundle("ec.edu.espoch.accesodatos.database",lt);
    
    private String DRIVER = "org.postgresql.Driver";
    //cadena de conexion a la centralizada
    private String URL = "jdbc:postgresql://localhost:5432/practicas";
    private String USER=  "postgres";
    private String PASS= "123456";

    public Global2() {
    }

    public final static int CANTIDADNOTICIASPAGINACION = 10;

    public String getDRIVER() {
        return DRIVER;
    }

    public void setDRIVER(String DRIVER) {
        this.DRIVER = DRIVER;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUSER() {
        return USER;
    }

    public void setUSER(String USER) {
        this.USER = USER;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

}
