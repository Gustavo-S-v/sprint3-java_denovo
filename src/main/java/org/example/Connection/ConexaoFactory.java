package org.example.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
        String usuario = "rm572560";
        String senha = "061107";

        return DriverManager.getConnection(url, usuario, senha);
    }
}