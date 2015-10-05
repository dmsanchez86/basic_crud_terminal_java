/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author desarrollador
 */
public class Conection {
    
    public Connection conection = null;
    public ResultSet data = null;
    public PreparedStatement query = null;
    public String[] data_book;
    
    public boolean conectar(){
        try {
            // Driver for connect 
            Class.forName("com.mysql.jdbc.Driver");
            this.conection = DriverManager.getConnection("jdbc:mysql://localhost/library", "root", "");
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
    public void desconectar() throws SQLException{
        this.conection.close();
    }
    
}
