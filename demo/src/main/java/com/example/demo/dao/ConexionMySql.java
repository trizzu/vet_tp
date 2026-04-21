package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySql {
    private static ConexionMySql instancia;
    private Connection connection;
    private static final String URL = "jdbc:mysql://172.16.1.64/vet_tp_tizi";
    private static final String USER = "bdd1";
    private static final String PASSWORD = "bdd1";

    private void ConexionMysql() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static ConexionMySql getInstancia() throws SQLException {
        if (instancia == null || instancia.connection.isClosed()) {
            instancia = new ConexionMySql();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }
}
