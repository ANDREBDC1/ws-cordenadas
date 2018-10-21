/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author Andre Rian
 */
public class ConexaoDb {
    
    private static String connectionString;
    private static String dbName = "DBWebService";
    private static Connection con;
    private static final String driver = "com.mysql.jdbc.Driver";//"com.microsoft.sqlserver.jdbc.SQLServerDriver";//"com.microsoft.sqlserver.jdbc.SqlServerDriver";
    private static String usuario = "root";
    private static String senha = "";
    
    
    public static void setConexaoDb(){ 
        /*
        //Conexão sql server
        connectionString = "jdbc:sqlserver://localhost:1433;databaseName=" + dbName; //+";user="+usuario+";password="+senha; 
        */
        // conexão mysql
        connectionString = "jdbc:mysql://localhost:3306/" + dbName;
        try{
            if(con == null){
                Class.forName(driver).newInstance();
                con = DriverManager.getConnection(connectionString, usuario, senha);  
            }
  
        }catch(Exception e){
            printStackTrace(e.getMessage());
        }
    }
    
    public static Connection getConnection(){
        setConexaoDb();
        return con != null? con : null;
    }
    
    public static boolean sqlExecute(String sql){
        try{
        setConexaoDb();
         if(con != null){
             Statement stmt = con.createStatement();
             return stmt.execute(sql);
         }         
        }catch(Exception e){
            closeConnection();
            return false;
        }
        return false;
    }
    
    public static void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            printStackTrace(ex.getMessage());
        }
    }
    
    public static PreparedStatement getPreparedStatement(String sql){
        // testo se a conexão já foi criada
        if (con == null){
            // cria a conexão
            con = getConnection();
        }
        try {
            // retorna um objeto java.sql.PreparedStatement
            return con.prepareStatement(sql);
        } catch (SQLException e){
            System.out.println("Erro de sql: "+
                    e.getMessage());
        }
        return null;
    }
    
    public static ResultSet getResultSet(String sql){
        
         ResultSet res = null;

        try {
           res = getPreparedStatement(sql).executeQuery();
//           closeConnection();
        } catch (SQLException ex) {
            closeConnection();
            printStackTrace(ex.getMessage());
        }
        
        return res;
    }
 
}
