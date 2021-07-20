/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author GUSTAVO
 */
public class ConnectionFactory {
    
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Mercadojsf";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    
    public static Connection getConnection(){
        
        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexao :"+ex);
        }
    }
    
    public static void CloseConnection(Connection con, PreparedStatement stmt){
        if (con != null && stmt != null){
            try {
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("erro ao fechar con e stmt: "+ex);
            }
        } 
    }
    
    public static void CloseRS(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("erro ao fechar rs: "+ex);
            }
        }
    }
}
