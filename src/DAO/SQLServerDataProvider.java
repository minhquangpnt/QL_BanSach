/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import POJO.Const;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class SQLServerDataProvider {

    public Connection connection;

    public void open() {
        String strServer = "DESKTOP-VDMI65C";
        String strDatabase = "QLNS_11_4_23";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://" + strServer
                    + ":1433; databaseName = " + strDatabase
                    + "; user = " + Const.strUser + "; password = " + Const.strPassword;
            //+";integratedSecurity = true";
            try {
                connection = DriverManager.getConnection(connectionURL);
            } catch (SQLException ex) {
                Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        Statement statement;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        int n = -1;
        try {
            Statement statement = connection.createStatement();
            n = statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SQLServerDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
}
