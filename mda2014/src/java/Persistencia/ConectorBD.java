/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author rustu
 */
public class ConectorBD {
    /** La conexion con la base de datos */
    private static Connection conexion = null;
    private static String user = "root";
    private static String password = "";
    private static String puerto = "3306";
    private static String catalogo = "mda2014";
    private static String host = "localhost";
    
    public static Connection estableceConexion()
    {
        if (conexion != null)
            return conexion;

        try
        {
            // Se registra el Driver de MySQL
            Class.forName("com.mysql.jdbc.Driver");
            
            String urlConexion = "jdbc:mysql://" + host + ":" + puerto + "/"+catalogo;
           
            conexion = DriverManager.getConnection(urlConexion,user,password);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return conexion;
    }
}
