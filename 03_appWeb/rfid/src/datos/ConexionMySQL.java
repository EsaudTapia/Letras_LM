/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;


/**
 *
 * @author PC
 */
public class ConexionMySQL {
  private Connection conn;
    private String userName;
    private String password;
    private String url;
    
    public ConexionMySQL()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://127.0.0.1:3306/topicos";
            userName = "root";
            password = "root";
        } 
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Connection getConn()
    {
        return conn;
    }
    public Connection abrir() throws Exception
    {
        conn = DriverManager.getConnection(url, userName, password);        
        return conn;
    }
    public void cerrar()
    {
        try
        {
            if (conn != null)
            {
                conn.close();
                conn = null;
            }
        } 
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}


