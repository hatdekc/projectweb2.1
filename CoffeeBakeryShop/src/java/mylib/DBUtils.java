/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mylib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DBUtils {
    public static Connection makeConnection() throws Exception{
        Connection cn=null;
        String IP="localhost";
        String instanceName="LAPTOP-L0CNBBM0";
        String port="1433";
        String uid="sa";
        String pwd="12345";
        String db="BAKERY_COFFEE_SHOP_v1";
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://" +IP+"\\"+ instanceName+":"+port
                 +";databasename="+db+";user="+uid+";password="+pwd;
        Class.forName(driver);
        cn=DriverManager.getConnection(url);
        return cn;
    }
    
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=BAKERY_COFFEE_SHOP_v1";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //tải trình điều khiển JDBC của SQL Server
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //trả về đối tượng kết nối
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
