/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  oracle.jdbc.pool.OracleDataSource
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnexionOracleFactory {


    public static final String dbURL = "127.0.0.1";
    public static final String dbName = "gptl";
    public static final String user = "root";
    public static final String password = "root";
	

    private ConnexionOracleFactory() {
    }
	
    public static Connection creerConnexionMysql() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection("jdbc:mysql://" + dbURL + '/' + dbName, user, password);
        } catch (SQLException ex) {
            Logger.getLogger("AA").log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger("AA").log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger("AA").log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger("AA").log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public static Connection creerConnexion() {
        try {
            Properties props = new Properties();
            FileInputStream fichier = new FileInputStream(new File("connexion.properties"));
            props.load(fichier);
            OracleDataSource ods = new OracleDataSource();
            ods.setDriverType(props.getProperty("pilote"));
            ods.setPortNumber(new Integer(props.getProperty("port")).intValue());
            ods.setServiceName(props.getProperty("service"));
            ods.setUser(props.getProperty("user"));
            ods.setPassword(props.getProperty("pwd"));
            ods.setServerName(props.getProperty("server"));
            return ods.getConnection();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

