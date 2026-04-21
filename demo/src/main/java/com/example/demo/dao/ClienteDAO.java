package com.example.demo.dao;

import com.example.demo.model.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() throws SQLException{
        connection = ConexionMySql.getInstancia().getConnection();
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "select * from Cliente where activo = true";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("telefono"), rs.getString("email"), rs.getString("direccion"), rs.getBoolean("activo"));
                clientes.add(cliente);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }
}
