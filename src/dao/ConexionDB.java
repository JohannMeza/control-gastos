/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ConexionDB {
    private static final String URL = "jdbc:sqlserver://utp-sql-2026.database.windows.net:1433;databaseName=ControlGastos;encrypt=false";
    private static final String USER = "utp-admin";
    private static final String PASSWORD = "IGu1Z6BYkI";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
