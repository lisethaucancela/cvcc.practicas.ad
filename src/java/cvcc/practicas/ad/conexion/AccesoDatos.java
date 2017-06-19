/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcc.practicas.ad.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class AccesoDatos {

    private Connection con;
    private Exception error;
    private ResultSet rs;

    public AccesoDatos() {
        this.con = null;
        this.error = null;
    }

    /**
     * @return the error
     */
    public Exception getError() {
        return error;
    }
    /*0 signifiuca falso y cualquier otro valor es verdadero*/

    public ResultSet getRs() {
        return rs;
    }

    public Byte Connectar() {
        Byte result = 0;
        try {
            //Class.forName(ec.edu.espoch.estafeta.modelo.Global.driverClass);
            Class.forName(cvcc.practicas.ad.conexion.Global.driverClass);
            result = 1;
            this.con = DriverManager.getConnection(cvcc.practicas.ad.conexion.Global.databaseURL, cvcc.practicas.ad.conexion.Global.usuarioDB, cvcc.practicas.ad.conexion.Global.claveDB);
            result = 2;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }

    }

    public Byte Desconectar() {
        Byte result = 0;
        try {
            this.getCon().close();
            /*este null es el destructor porque en java no esxiste destructor*/
            this.con = null;
            result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: " + e.getClass().getName() + "***" + e.getMessage());
            this.error = e;
        } finally {
            return result;
        }
    }

    public Byte EjecutarSQL(String SQL) {
        Byte result = 0;
        try {
            Statement smt = this.getCon().createStatement();
            this.rs = smt.executeQuery(SQL);
            result = 1;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());

            this.error = e;
        } finally {
            return result;
        }
    }
    /*itra forma de utilizar los sql */

    public Byte EjecutarUpdate(String SQL) {
        Byte result = 0;
        try {
            PreparedStatement smt = this.getCon().prepareStatement(SQL);
            smt.executeUpdate();
            this.rs = smt.executeQuery(SQL);
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public Byte EjecutarUpdate_id(String SQL) {
        Byte result = 0;
        try {
            Statement smt = this.getCon().createStatement();
            smt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
            this.rs = smt.getGeneratedKeys();
            result = 1;
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getClass().getName() + " *** " + e.getMessage());
            this.error = e;
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    public static boolean ejecutaComando(String comando) throws Exception {
        boolean respuesta = false;
        PreparedStatement prts = null;
        Connection con = null;
        try {
            Global2 global = new Global2();
            Class.forName(global.getDRIVER());
            try {
                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
                prts = con.prepareStatement(comando); //preparo el comando 
                //ejecutando la sentencia
                int i = prts.executeUpdate();
                if (i > 0) {
                    respuesta = true;
                }
                prts.close();
                prts = null;
            } catch (SQLException exCon) {
                throw exCon;
            } finally {
                try {
                    if (con != null) {//verifico si la conexion no nesta cerrada                        
                        if (!(con.isClosed())) {
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    throw ex;
                }
            }
        } catch (ClassNotFoundException e) {
            throw e;
        }
        return respuesta;
    }

    public static ResultSet ejecutaQuery(String query) throws Exception {

        //ConjuntoResultado conj = new ConjuntoResultado();
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = null;
        try {
            Global2 global = new Global2();
            //global.getProperties();
            //registro el driver
            Class.forName(global.getDRIVER());
            try {
                con = DriverManager.getConnection(global.getURL(), global.getUSER(), global.getPASS());
                pst = con.prepareStatement(query); //Clase que representa una sentencia precompilada de SQL 
                rs = pst.executeQuery();
                //conj.Fill(rs);
                //rs.close();
                //pst.close();
                //rs = null;
                pst = null;
            } catch (SQLDataException exSQL) {
                throw exSQL;
            } finally {
                try {
                    if (con != null) {
                        if (!(con.isClosed())) { //verifico si la conexion no nesta cerrada
                            con.close();
                        }
                        con = null;
                    }
                } catch (Exception ex) {
                    System.err.println("e: " + ex.getMessage());
                    throw ex;
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("e: " + e.getMessage());
            throw e;
        }

        return rs;

    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    public void BeginTran() {
        try {
            if (this.con != null) {
                con.setAutoCommit(false);
            }
        } catch (SQLException e) {
            Logger log = Logger.getLogger(this.getClass().getName());
            log.severe(e.getMessage());
        }
    }

    public void CommitTran() {
        try {
            if (this.con != null) {
                this.con.commit();
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            Logger log = Logger.getLogger(this.getClass().getName());
            log.severe(e.getMessage());
        }
    }

    public void RollbackTran() {
        try {
            if (this.con != null) {
                this.con.rollback();
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            Logger log = Logger.getLogger(this.getClass().getName());
            log.severe(e.getMessage());
        }
    }

}
